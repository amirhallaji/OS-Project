import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private Patient patient;

    public MyThread(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void run() {
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
            System.out.println("Patient Name: " + patient.getName() + " left the hospital");
            Hospital.currentCapacity--;
            semaphore.release();
        }

    }
}
