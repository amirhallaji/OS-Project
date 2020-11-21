public class Patient {
    private String name;
    private int entryTime;
    private MyThread myThread;

    public Patient(String name, int entryTime, MyThread myThread){
        this.name = name;
        this.entryTime = entryTime;
        this.myThread = new MyThread();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEntryTime(int entryTime){
        this.entryTime = entryTime;
    }

    public String getName(){
        return name;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public MyThread getMyThread() {
        return myThread;
    }
}
