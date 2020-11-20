public class Patient {
    private String name;
    private int duration;

    public Patient(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public String getName(){
        return this.name;
    }

    public int getDuration() {
        return duration;
    }
}
