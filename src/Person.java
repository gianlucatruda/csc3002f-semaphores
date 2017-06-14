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

    @Override
    public void run() {

        Voyage v = voyages.get(0);

        boolean success = false;
        success = taxi.hail(location);
        System.out.println("Branch "+location+": "+ID+" hails");
        while(location != taxi.getLocation()) {
            try {
                sleep(TEMPO/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        success = taxi.request(v.getBranch(), this);
        System.out.println("Branch "+location+": "+ID+" gets on");
        while(taxi.getLocation() != v.getBranch()) {
            try {
                sleep(TEMPO/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        taxi.depart(this);

    }

    public int getID() {
        return ID;
    }

    public int getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.valueOf(ID);
    }

    public void setLocation(int x) {
        location = x;
    }
}
