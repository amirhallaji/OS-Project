import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private final Patient patient;
    boolean hasWaited = false;

    //define a static semaphore, because it must be independent to object
    //Allocating the maximum number of semaphores
    static Semaphore doctorSemaphores = new Semaphore(Main.numDocs, true);
    static Semaphore patientMutex = new Semaphore(1, true);
    int doctorIndex = 1;
    //doctor nth
    static int doctorNumber = 1;

    public MyThread(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void run() {
        long timeStarted = System.currentTimeMillis();
        long timeEntered;
        long timeFinish;
        try {
//            patientMutex.acquire();

//            patientMutex.release();
            if (doctorSemaphores.availablePermits() == 0 && Hospital.currentCapacity != 0) {
                hasWaited = true;
                Hospital.currentCapacity--;
                System.out.println("Patient: " + patient.getName() + " is waiting for a doctor in time " + (double) (patient.getEntryTime()) / 1000);
            }

            //acquiring lock for a semaphore (doctor is busy)
            doctorSemaphores.acquire();
            for (int i = 0; i < Main.isDoctorBusy.length; i++) {
                if (!Main.isDoctorBusy[i]) {
                    Main.isDoctorBusy[i] = true;
                    Main.patientToDoctor.put(patient, i);
                    break;
                }
            }
            timeEntered = System.currentTimeMillis();
            double enteringDuration = timeEntered - timeStarted + patient.getEntryTime();
            System.out.println("Patient: " + patient.getName() + " visited Doctor " + (Main.patientToDoctor.get(patient) + 1) + " in time: " + (enteringDuration / 1000));
            if (hasWaited) {
                Hospital.currentCapacity++; // whenever a patient enters a doctor room who has waited before, number of patients waiting in the hall must be reduced by 1.
            }
            Thread.sleep(2000); // time needed for treatment
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeFinish = System.currentTimeMillis();
            double duration = timeFinish - timeStarted + patient.getEntryTime();
            System.out.println("Patient: " + patient.getName() + " is done with the hospital. Time is : " + (duration / 1000));
            Main.isDoctorBusy[Main.patientToDoctor.get(patient)] = false;
            Main.patientToDoctor.remove(patient);
            //releasing lock for a semaphore (doctor is free now).
            doctorSemaphores.release();
        }

    }
}
