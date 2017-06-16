/**
 * Created by gianlucatruda on 14/06/2017.
 */
public class SimTimer extends Thread {
    final static int TEMPO = 17; // Set to 17 for final demo
    private int hours;
    private int mins;
    private boolean completed;

    public SimTimer() {
        this.hours = 9;
        this.mins = 0;
        this.completed = false;
    }

    public String getTime() {
        String h = String.valueOf(hours);
        String m = String.valueOf(mins);

        if(h.length() == 1) h="0"+h;
        if(m.length() == 1) m="0"+m;

        return h+":"+m;
    }

    public void run() {
        try {
            while(!completed){
                sleep(TEMPO);
                inc();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private void inc() {
        if(mins == 59 ){
            mins = 0;
            hours++;
        } else mins++;
    }
}
