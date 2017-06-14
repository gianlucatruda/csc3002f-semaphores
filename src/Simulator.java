import java.io.*;

public class Simulator {
    public static void main(String[] args) throws FileNotFoundException {

        String filename = "defaultInstructions.txt";
        int M; // The number of people
        int N; // The number of branches

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

            for (int i = 0; i < M; i++) {
                line = r.readLine().trim();
                int pID = i;
                String sep = " \\(";
                line = line.substring(String.valueOf(pID).length()+2);
                String[] dataLines = line.split(sep);
                for(String s:dataLines) {
                    String pair = s.trim();
                    int branch = Integer.valueOf(pair.split(",")[0].trim());
                    int duration = Integer.valueOf(pair.substring(pair.indexOf(",")+2,pair.indexOf(")")));
                    //System.out.println(i+" b"+branch+"d"+duration);
                }
            }

            System.out.println("Successfully imported data!\n");

        } catch (FileNotFoundException expFile) {
            System.out.println("Could not find data file. Please ensure name and directory structure is correct.");
            expFile.printStackTrace();
        } catch (IOException expIO) {
            System.out.println("IO Error. Please try again.");
            expIO.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException expArr) {
            System.out.println("File format incorrect. Please try again.");
            expArr.printStackTrace();
        } catch (NumberFormatException expForm) {
            System.out.println("File format incorrect. Please try again.");
            expForm.printStackTrace();
        }


    }
}