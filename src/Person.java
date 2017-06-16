import java.util.ArrayList;

/**
 * This class is to represent people who would be passengers on the taxi.
 * Created by gianlucatruda on 14/06/2017.
 */
class Person extends Thread {

    private final int ID;
    private final ArrayList<Voyage> voyages;
    private final Taxi taxi;
    private int location;
    private final SimTimer time;

    public Person(int id, Taxi t, SimTimer timer, ArrayList<Voyage> trips) {
        this.ID = id;
        this.voyages = trips;
        this.taxi = t;
        this.location = 0;
        this.time = timer;
    }

    @Override
    public void run() {

        for(Voyage v:voyages) {
            boolean success = false;
            success = taxi.hail(location);
            System.out.println(time.getTime()+" branch "+location+": person "+ID+" hail");
            while(location != taxi.getLocation()) {
                holdUp(0.5);
            }
            success = taxi.request(v.getBranch(), this);
            holdUp(1);
            System.out.println(time.getTime()+" branch "+location+": person "+ID+" request "+v.getBranch());
            while(taxi.getLocation() != v.getBranch()) {
                holdUp(0.5);
            }
            taxi.unboard(this);
                holdUp(v.getDuration());
        }
        taxi.notifyComplete();

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
            int TEMPO = SimTimer.TEMPO;
            sleep((int)(TEMPO *x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
