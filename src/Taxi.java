import java.util.*;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Taxi extends Thread {

    private final int TEMPO = Simulator.TEMPO;
    private int M;
    private int N;
    private int location;
    public enum State { IDLE, OUTBOUND, INBOUND };
    private State state;
    private ArrayDeque<Integer> loci;
    private ArrayList<Person> passengers;
    private Semaphore sem;

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        this.location = 0;
        loci = new ArrayDeque<>();
        passengers = new ArrayList<>();
        sem = new Semaphore(M, true);
    }

	public boolean hail(int branch) {
        sem.acquireUninterruptibly();
        if(!loci.contains(branch)) {
            loci.addLast(branch);
        }
        return true;
    }

    public boolean request(int branch, Person p) {
        sem.release();
        passengers.add(p);
        loci.addLast(branch);
        sem.acquireUninterruptibly();
        if(!loci.contains(branch)) {
            loci.addLast(branch);
        }
        return true;
    }

    public void run() {
        state = State.OUTBOUND;
        while(true) {
            System.out.println(location+" *"+passengers.toString()+"* --> "+loci.toString());
            if(loci.size() > 0) {
                if(loci.contains(location)) {
                    holdUp(1);
                    loci.remove(location);
                } else {
                    int next = loci.removeFirst();
                    if(state == State.OUTBOUND && location < N) {
                        location++;
                        syncLengths();
                        holdUp(2);
                    } else {
                        location--;
                        state = State.INBOUND;
                        syncLengths();
                        holdUp(2);
                    }
                }

            } else {
                state = State.IDLE;
                holdUp(1);
            }
        }


    }

    public void depart(Person p) {
        sem.release();
        passengers.remove(p);
    }

    public int getLocation() {
        return location;
    }

    private void holdUp(int x) {
        try {
            sleep(TEMPO*x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syncLengths() {
        for(Person p:passengers) {
            p.setLocation(this.location);
        }
    }
}
