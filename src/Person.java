import java.util.ArrayList;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Person extends Thread {

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
        System.out.println("Person "+ID+" started.");

        for(Voyage v:voyages) {
            boolean success = false;
            try {
                taxi.hail(this, location);
                if(taxi.state == Taxi.State.WAITING) {
                    taxi.goTo(this, location, v.getBranch());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ID+" on board");
        }
    }
}
