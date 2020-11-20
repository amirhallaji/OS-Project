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

        //We allocate an exclusive thread for each patient
        //Suppose that 10 patients come to the hospital at different moments
        Patient [] patients = new Patient[5]{
                new Patient("p1",1000);
                new Patient("p1",1000);
                new Patient("p1",2000);
                new Patient("p1",3000);
                new Patient("p1",4000);
        }

        scan.close();
    }
}

