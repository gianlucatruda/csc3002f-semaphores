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
    private Semaphore hailerPhore;
    private Semaphore goToPhore;
    public enum State { IDLE, OUTBOUND, INBOUND };
    public State state;
    private ArrayList<Integer> pickups;
    private ArrayList<Integer> dropoffs;

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        this.location = 0;
        pickups = new ArrayList<>();
        dropoffs = new ArrayList<>();
        hailerPhore = new Semaphore(M);
        goToPhore = new Semaphore(M);
    }

    public void run() {
        state = State.OUTBOUND;
        while(true) {
            System.out.println(location+" >>> pick: "+pickups.toString()+" drop: "+dropoffs.toString());

            if(pickups.size() > 0) {
                moveToward(pickups);
            } else if(dropoffs.size() > 0) {
                moveToward(dropoffs);
            }
            try {
                sleep(TEMPO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized Semaphore hail(int branch) {
        if(!pickups.contains(branch)) {
            pickups.add(branch);
        }
        return hailerPhore;
    }

    public synchronized Semaphore goTo(int branch, Semaphore sem) {
        if(!dropoffs.contains(branch)) {
            dropoffs.add(branch);
        }
        sem.release();
        return goToPhore;
    }

    private synchronized void moveToward(ArrayList<Integer> loci) {
        if(location == M-1) {
            state = State.INBOUND;
        } else if (location == 0) {
            state = State.OUTBOUND;
        }

        int b = 0;
        if(state == State.OUTBOUND) {
            Collections.sort(loci);
            b = loci.get(loci.size()-1);
        } else if(state == State.INBOUND) {
            Collections.sort(loci);
            b = loci.get(0);
        }

        if(location == b) {
            System.out.println("Branch "+location+": taxi arrive");
            loci.remove(b);
            try {
                sleep(TEMPO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if(state == State.OUTBOUND) {
                if(location < b) {
                    try {
                        sleep(TEMPO*2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    location++;
                }
            }
        }

        //TODO fill this out if it works
    }

    public int getLocation() {
        return location;
    }
}
