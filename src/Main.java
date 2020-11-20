import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        int capacity = scan.nextInt();

        System.out.println("Please Enter the number of doctors (n)");
        int numDocs = scan.nextInt();
        Semaphore semaphore = new Semaphore(numDocs);

    }
}

