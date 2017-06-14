import java.util.ArrayList;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Person extends Thread {

    private int ID;
    private ArrayList<Voyage> voyages;
    private Taxi taxi;

    public Person(int id, Taxi t, ArrayList<Voyage> trips) {
        this.ID = id;
        this.voyages = trips;
        this.taxi = t;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Voyage> getVoyages() {
        return voyages;
    }

    @Override
    public void run() {
        System.out.println("Person "+ID+" says hi.");
        try {
            sleep(10000);
            System.out.println(ID+" is done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
