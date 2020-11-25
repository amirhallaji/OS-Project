import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private final Patient patient;
    boolean hasWaited = false;

    //define a static semaphore, because it must be independent to object
    //Allocating the maximum number of semaphores
    static Semaphore doctorSemaphores = new Semaphore(Main.numDocs, true);
    static Semaphore patientSemaphore = new Semaphore(1);
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
            if (doctorSemaphores.availablePermits() == 0 && Hospital.currentCapacity != 0) {
                hasWaited = true;
                Hospital.currentCapacity--;
                System.out.println("Patient: " + patient.getName() + " is waiting for a doctor in time " + (double) (patient.getEntryTime()) / 1000);
            }
            //acquiring lock for a semaphore (doctor is busy)
            doctorSemaphores.acquire();
            Main.patientToDoctor.put(patient, doctorNumber++);
            timeEntered = System.currentTimeMillis();
            double enteringDuration = timeEntered - timeStarted + patient.getEntryTime();
            System.out.println("Patient: " + patient.getName() + " visited Doctor " + Main.patientToDoctor.get(patient) + " in time: " + (enteringDuration / 1000));
            if(hasWaited) {
                Hospital.currentCapacity++; // whenever a patient enters a doctor room who has waited before, number of patients waiting in the hall must be reduced by 1.
            }
            Thread.sleep(2000); // time needed for treatment
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeFinish = System.currentTimeMillis();
            double duration = timeFinish - timeStarted + patient.getEntryTime();
            System.out.println("Patient: " + patient.getName() + " is done with the hospital. Time is : " + (duration / 1000));
            //doctor is getting free now and patient is done.
            Main.patientToDoctor.remove(patient);
            doctorNumber--;
            //releasing lock for a semaphore (doctor is free now).
            doctorSemaphores.release();
        }

    }
}
