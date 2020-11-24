import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private final Patient patient;

    //define a static semaphore, because it must be independent to object
    //Allocating the maximum number of semaphores
    static Semaphore semaphore = new Semaphore(Main.numDocs);

    public MyThread(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void run() {
        int doctorNumber = 0;
        long timeStarted = System.currentTimeMillis();
        long timeFinish;
        try {
            for (int i = 0; i < Main.isDoctorBusy.length; i++) {
                if(!Main.isDoctorBusy[i]){
                    Main.isDoctorBusy[i] = true;
                    doctorNumber = i;
                    break;
                }
            }
            //acquiring lock for a semaphore (doctor is busy)
            semaphore.acquire();
            System.out.println("Patient Name: " + patient.getName() + " just visited Doctor " + (doctorNumber+1));
            Hospital.currentCapacity++; //whenever a patient enters a doctor room, number of patients waiting in the hall must be reduced by 1.
            Thread.sleep(2000); //time needed for treatment
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeFinish = System.currentTimeMillis();
            long duration = timeFinish - timeStarted + patient.getEntryTime();
            System.out.println("Patient Name: " + patient.getName() + " left the hospital. Time is : " + (duration / 1000));
            Main.isDoctorBusy[doctorNumber] = false;
            //releasing lock for a semaphore (doctor is free now)
            semaphore.release();
        }

    }
}
