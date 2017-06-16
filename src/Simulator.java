
import java.io.*;
import java.util.ArrayList;

/**
 * Created by gianlucatruda on 14/06/2017.
 */
class Simulator {

    final static boolean DEBUG = false; // Turn to true to see more verbose output

    public static void main(String[] args) throws FileNotFoundException {
        SimTimer time = new SimTimer();
        String filename = "tests/standard.txt";
        int M; // The number of people
        int N; // The number of branches
        Person[] people;

        if(args.length == 1) {
            filename = args[0].trim();
        }

        System.out.println("=== Welcome to the Taxi Semaphore Sim ===\n");
        System.out.println("Importing data from file '"+filename+"'...");

        // TODO make this more robust
        // Data from file is read in and formatted
        try {
            final BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = r.readLine().trim();

            M = Integer.valueOf(line);
            line = r.readLine().trim();
            N = Integer.valueOf(line);

            // Instantiate a taxi
            Taxi taxi = new Taxi(M, N, time);

            people = new Person[M];
            for (int i = 0; i < M; i++) {
                line = r.readLine().trim();
                //TODO reassess this
                ArrayList<Voyage> trips = new ArrayList<>();
                String sep = " \\(";
                line = line.substring(String.valueOf(i).length()+2);
                String[] dataLines = line.split(sep);
                for(String s:dataLines) {
                    String pair = s.trim();
                    int branch = Integer.valueOf(pair.split(",")[0].trim());
                    int duration = Integer.valueOf(pair.substring(pair.indexOf(",")+2,pair.indexOf(")")));
                    //System.out.println(i+" b"+branch+"d"+duration);
                    trips.add(new Voyage(branch, duration));
                }
                people[i] = new Person(i, taxi, time, trips);
            }

            System.out.println("Successfully imported data!\n");


            // Start simulation

            for(Person p:people) {
                p.start();
            }
            time.start();
            taxi.start();

        } catch (FileNotFoundException expFile) {
            System.out.println("Could not find data file. Please ensure name and directory structure is correct.");
            expFile.printStackTrace();
        } catch (IOException expIO) {
            System.out.println("IO Error. Please try again.");
            expIO.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException expArr) {
            System.out.println("File format incorrect. Please try again.");
            expArr.printStackTrace();
        }


    }
}