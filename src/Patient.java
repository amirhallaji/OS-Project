public class Patient {
    private String name;
    private int entryTime;

    public Patient(String name, int entryTime){
        this.name = name;
        this.entryTime = entryTime;
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
}
