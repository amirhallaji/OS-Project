import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private final Patient patient;

    public MyThread(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void run() {
        long timeStarted = System.currentTimeMillis();
        long timeFinish;
        //Allocating the maximum number of semaphores
        Semaphore semaphore = new Semaphore(Main.numDocs);
        try {
//            System.out.println("Patient Name: " + patient.getName() + " is waiting for a doctor to visit.");
            //acquiring lock for a semaphore (doctor is busy)
            semaphore.acquire();
            System.out.println("Patient Name: " + patient.getName() + " just visited doctor");
            Hospital.currentCapacity++; //when a patient enters a doctor room, number of patients waiting must be reduced by 1.
            Thread.sleep(2000); //time for visiting doctor
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeFinish = System.currentTimeMillis();
            long duration = timeFinish - timeStarted + patient.getEntryTime();
            System.out.println("Patient Name: " + patient.getName() + " left the hospital. Time is : " + (duration / 1000));
            //releasing lock for a semaphore (doctor is free now)
            semaphore.release();
        }

    }
}
