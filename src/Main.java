import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        Hospital.capacity = scan.nextInt();

        System.out.println("Please Enter the number of doctors (n)");
        int numDocs = scan.nextInt();

        //Allocating the maximum number of semaphores
        Semaphore semaphore = new Semaphore(numDocs);
        MyThread[] myThreads = new MyThread[5];
        //Suppose that 5 patients come to the hospital at different moments
        Patient[] patients = new Patient[5];
        patients[0] = new Patient("p1", 1000, myThreads[0]);
        patients[1] = new Patient("p2", 1000, myThreads[1]);
        patients[2] = new Patient("p3", 2000, myThreads[2]);
        patients[3] = new Patient("p4", 3000, myThreads[3]);
        patients[4] = new Patient("p5", 4000, myThreads[4]);

        scan.close();
    }
}

