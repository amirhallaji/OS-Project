import java.util.Queue;

public class Hospital {
    private int capacity;
    private Queue queue;

    public Hospital(int capacity, Queue queue) {
        this.capacity = capacity;
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
