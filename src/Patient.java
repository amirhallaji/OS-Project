public class Patient {
    private String name;
    private int entryTime;

    public Patient(String name, int entryTime){
        this.name = name;
        this.entryTime = entryTime;
    }

    public String getName(){
        return name;
    }

    public int getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "  Entry time: " + this.entryTime;
    }
}
