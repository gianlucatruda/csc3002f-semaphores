import java.util.ArrayList;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class Person {

    private int ID;
    private ArrayList<Voyage> voyages;

    public Person(int id, ArrayList<Voyage> trips) {
        this.ID = id;
        this.voyages = trips;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Voyage> getVoyages() {
        return voyages;
    }
}
