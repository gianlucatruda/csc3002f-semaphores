import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Taxi {

    private final int TEMPO = 2000;
    private int M;
    private int N;
    private int location;
    private Semaphore hailerPhore;
    private Semaphore goToPhore;
    public enum State { IDLE, WAITING, OUTBOUND, INBOUND };
    public State state;
    private Deque<Person> passengers;

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        this.location = 0;
        passengers = new ArrayDeque<>();
        hailerPhore = new Semaphore(1);
        goToPhore = new Semaphore(1);
    }

    public void hail(Person p, int start) throws InterruptedException {
        hailerPhore.acquire();
        System.out.println("Taxi("+this.state+"): Person "+p.getID()+" hailed me from "+start);
        while(location < start) {
            headOut();
        }
        boarding();
    }

    public void goTo(Person p, int start, int dest) throws InterruptedException {
        goToPhore.acquire();
        passengers.add(p);
        hailerPhore.release();
        System.out.println("Taxi("+this.state+"): Person "+p.getID()+" going to "+dest);
    }

    private void boarding() throws InterruptedException {
        this.state = State.WAITING;
        sleep(TEMPO);
    }

    private void headOut() throws InterruptedException {
        this.state = State.OUTBOUND;
        sleep(TEMPO*2);
        location++;
    }

    public void setState(State s) {
        this.state = s;
    }

}
