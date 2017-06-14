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

    public Taxi(int m, int n) {
        this.M = m;
        this.N = n;
        this.location = 0;

    }

    public void run() {

    }

    public int getLocation() {
        return location;
    }
}
