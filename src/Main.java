import java.util.Scanner;

public class Main {
    static int numDocs;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        Hospital.capacity = scan.nextInt();
        Hospital.currentCapacity = Hospital.capacity;
        System.out.println("Please Enter the number of doctors (n)");
        numDocs = scan.nextInt();

        MyThread[] myThreads = new MyThread[4];
        //Suppose that 5 patients come to the hospital at different moments
        Patient[] patients = new Patient[4];
        patients[0] = new Patient("p0", 15000);
        patients[1] = new Patient("p1", 15000);
        patients[2] = new Patient("p2", 2000);
        patients[3] = new Patient("p3", 4000);

        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(patients[i]);
        }

        for (int i = 0; i < patients.length; i++) {
            int finalI = i;
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (Hospital.currentCapacity > 0) {
                                System.out.println("Patient " + patients[finalI].getName() + " entered the hospital and is waiting for a doctor.");
                                Hospital.currentCapacity--;
                                myThreads[finalI].start();
                            } else {
                                System.out.println("Patient " + patients[finalI] + " left the hospital, because there was no place to wait.");
                            }
                        }
                    },
                    patients[i].getEntryTime() //delay
            );
        }
        scan.close();
    }
}

