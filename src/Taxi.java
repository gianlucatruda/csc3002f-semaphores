import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Taxi {

    private int M;
    private int N;
    private Semaphore hailerPhore;
    private Semaphore goToPhore;
    public enum State { IDLE, MOVING, WAITING };
    public State state;
    private Deque<Person> passengers;

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        passengers = new ArrayDeque<>();
    }

    public void hail(int pid, int start, int dest) {
        System.out.println("Taxi("+this.state+"): Person "+pid+" hailed me from " + start+" to "+dest );
    }

    public void setState(State s) {
        this.state = s;
    }

}
