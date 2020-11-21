import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private Patient patient;

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
            System.out.println("Patient Name: " + patient.getName() + " is waiting for a doctor to visit.");
            Hospital.currentCapacity++;
            semaphore.acquire();
            System.out.println("Patient Name: " + patient.getName() + " just visited doctor");
            Thread.sleep(2000); //time for visiting doctor
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeFinish = System.currentTimeMillis();
            long duration = timeFinish - timeStarted;
            System.out.println("Patient Name: " + patient.getName() + " left the hospital. Time is : " + (duration));
            Hospital.currentCapacity--;
            semaphore.release();
        }

    }
}
