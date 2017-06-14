import java.util.ArrayList;

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

        for(Voyage v:voyages) {
            boolean success = false;
            success = taxi.hail(location);
            System.out.println("Branch "+location+": person "+ID+" hail");
            while(location != taxi.getLocation()) {
                holdUp(0.5);
            }
            success = taxi.request(v.getBranch(), this);
            System.out.println("Branch "+location+": person "+ID+" request "+v.getBranch());
            while(taxi.getLocation() != v.getBranch()) {
                holdUp(0.5);
            }
            taxi.unboard(this);
                holdUp(v.getDuration());
        }


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

    private void holdUp(double x) {
        try {
            sleep((int)(TEMPO*x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
