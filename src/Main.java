import java.util.*;

public class Main {
    static int numDocs;
    //for mapping each patient to a specific doctor
    static Map<Patient, Integer> patientToDoctor = new LinkedHashMap<>();
    static int currentCapacity;
    //create a boolean array for doctors to see whether the doctor is busy or not
    static Boolean[] isDoctorBusy;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter the capacity of the hospital (m)");
        currentCapacity = scan.nextInt();
        System.out.println("Please Enter the number of doctors (n)");
        numDocs = scan.nextInt();
        isDoctorBusy = new Boolean[numDocs];
        Arrays.fill(isDoctorBusy, false);

        MyThread[] myThreads = new MyThread[8];
        //Suppose that 8 patients come to the hospital at different moments
        Patient[] patients = new Patient[8];
        patients[0] = new Patient("P1", 1000);
        patients[1] = new Patient("P2", 1000);
        patients[2] = new Patient("P3", 1000);
        patients[3] = new Patient("P4", 1000);
        patients[4] = new Patient("P5", 1100);
        patients[5] = new Patient("P6", 3000);
        patients[6] = new Patient("P7", 3000);
        patients[7] = new Patient("P8", 10000);


        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(patients[i]);
        }

        for (int i = 0; i < patients.length; i++) {
            int finalI = i;
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (currentCapacity > 0) {
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

