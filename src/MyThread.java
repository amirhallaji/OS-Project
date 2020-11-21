import java.util.concurrent.Semaphore;

public class MyThread extends Thread{
    private Patient patient;

    public MyThread(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void run() {
        try {
            
            //Allocating the maximum number of semaphores
            Semaphore semaphore = new Semaphore(Main.numDocs);

//            System.out.println("Patient: " + );
        }
        finally {

        }

    }
}
