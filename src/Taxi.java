import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Taxi extends Thread {

    private int M;
    private int N;
    private Semaphore hailerPhore;
    private Semaphore goToPhore;
    private enum State { IDLE, MOVING, WAITING };
    private Deque<Person> passengers;

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        passengers = new ArrayDeque<>();
    }

    public void run() {
        System.out.println("The taxi started.");
    }

}
