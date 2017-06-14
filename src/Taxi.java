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

    public void run() {
        state = State.OUTBOUND;

        while(true) {
            System.out.println(location+"("+state+") *"+passengers.toString()+"* --> "+loci.toString());
            if(loci.size() > 0) {
                if(loci.contains(location)) {
                    holdUp(1);
                    loci.remove(location);
                } else {
                    if(state == State.OUTBOUND && location < N-1) {
                        drive(1);
                    } else if (state == State.INBOUND && location > 0) {
                        drive(-1);
                    } else {
                        if(state == State.IDLE) {
                            int next = loci.peek();
                            if(next > location) {
                                state = State.OUTBOUND;
                                drive(1);
                            } else {
                                state = State.INBOUND;
                                drive(-1);
                            }
                        }
                        if(location == N-1) {
                            state = State.INBOUND;
                            drive(-1);
                        } else if(location == 0) {
                            state = State.OUTBOUND;
                            drive(1);
                        }
                    }
                }

            } else {
                state = State.IDLE;
                holdUp(1);
            }
        }


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

    public void unboard(Person p) {
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

    private void drive(int incr) {
        location+=incr;
        syncLengths();
        holdUp(2);
    }

    private synchronized void syncLengths() {
        for(Person p:passengers) {
            p.setLocation(this.location);
        }
    }
}
