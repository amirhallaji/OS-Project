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
        long timeStarted = System.currentTimeMillis();
        long timeFinish;
        try {
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
