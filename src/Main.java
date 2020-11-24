import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int numDocs;
    static Map<Patient, Integer> patientToDoctor = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        Hospital.capacity = scan.nextInt();
        Hospital.currentCapacity = Hospital.capacity;
        System.out.println("Please Enter the number of doctors (n)");
        numDocs = scan.nextInt();


        MyThread[] myThreads = new MyThread[21];
        //Suppose that 19 patients come to the hospital at different moments
        Patient[] patients = new Patient[21];
        patients[0] = new Patient("p0", 1000);
        patients[1] = new Patient("p1", 1000);
        patients[2] = new Patient("p2", 1000);
        patients[3] = new Patient("p3", 1000);
        patients[4] = new Patient("p4", 1000);
        patients[5] = new Patient("p5", 1000);
        patients[6] = new Patient("p6", 1000);
        patients[7] = new Patient("p7", 3000);
        patients[8] = new Patient("p8", 3000);
        patients[9] = new Patient("p9", 3000);
        patients[10] = new Patient("p10", 3000);
        patients[11] = new Patient("p11", 3000);
        patients[12] = new Patient("p12", 3000);
        patients[13] = new Patient("p13", 3000);
        patients[14] = new Patient("p14", 5000);
        patients[15] = new Patient("p15", 5000);
        patients[16] = new Patient("p16", 9000);
        patients[17] = new Patient("p17", 10000);
        patients[18] = new Patient("p18", 11000);
        patients[19] = new Patient("p19", 12000);
        patients[20] = new Patient("p20", 13000);

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
//                                System.out.println("Patient: " + patients[finalI].getName() + " entered the hospital and is waiting for a doctor.");
                                Hospital.currentCapacity--;
                                myThreads[finalI].start();
                            } else {
                                System.out.println("Patient: " + patients[finalI] + " left the hospital, because there was no place to wait.");
                            }
                        }
                    },
                    patients[i].getEntryTime() //delay
            );
        }
        scan.close();
    }
}

