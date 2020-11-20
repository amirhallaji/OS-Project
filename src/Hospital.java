import java.util.Queue;

public class Hospital {
    public static int capacity;
    public static int currentCapacity = 0;
    private Queue queue;

    public Hospital(int capacity, Queue queue) {
        Hospital.capacity = capacity;
        this.queue = queue;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public int getCapacity() {
        return capacity;
    }

    public Queue getQueue() {
        return queue;
    }
}
