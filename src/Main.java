import java.util.Scanner;

public class Main {
    static int numDocs;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        Hospital.capacity = scan.nextInt();

        System.out.println("Please Enter the number of doctors (n)");
        numDocs = scan.nextInt();

        MyThread[] myThreads = new MyThread[5];
        //Suppose that 5 patients come to the hospital at different moments
        Patient[] patients = new Patient[5];
        patients[0] = new Patient("p1", 1000);
        patients[1] = new Patient("p2", 1000);
        patients[2] = new Patient("p3", 2000);
        patients[3] = new Patient("p4", 3000);
        patients[4] = new Patient("p5", 4000);

        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(patients[i]);
        }

        for (int i = 0; i < patients.length; i++) {
            int finalI = i;
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (Hospital.currentCapacity < Hospital.capacity) {
                                myThreads[finalI].start();
                            }
                        }
                    },
                    patients[i].getEntryTime() //delay
            );

        }
        scan.close();
    }
}

