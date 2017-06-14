import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Person extends Thread {

    private final int TEMPO = Simulator.TEMPO;
    private int ID;
    private ArrayList<Voyage> voyages;
    private Taxi taxi;
    private int location;

    public Person(int id, Taxi t, ArrayList<Voyage> trips) {
        this.ID = id;
        this.voyages = trips;
        this.taxi = t;
        this.location = 0;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Voyage> getVoyages() {
        return voyages;
    }

    public int getLocation() {
        return location;
    }

    @Override
    public void run() {
        //System.out.println("Person "+ID+" started.");

        for(Voyage v:voyages) {
            Semaphore hailer = taxi.hail(location);
            hailer.acquireUninterruptibly();
            System.out.println("Branch "+location+": person "+ID+" hail");
            while(taxi.getLocation() != location) {
                try {
                    sleep(TEMPO/2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Semaphore destiner = taxi.goTo(v.getBranch(), hailer);
            destiner.acquireUninterruptibly();
            System.out.println("Branch "+location+": person "+ID+" request "+v.getBranch());
            while(taxi.getLocation() != v.getBranch()) {
                location = taxi.getLocation();
                try {
                    sleep(TEMPO/2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            location = taxi.getLocation();
            destiner.release();
            try {
                sleep(TEMPO*v.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
