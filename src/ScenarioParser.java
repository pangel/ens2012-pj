
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class ScenarioParser {
    ScenarioParser(Simulator sim) {

        InputStream inputStream;
        try {
            inputStream = new FileInputStream("scenario.sc");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScenarioParser.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        Scanner s = new Scanner(r);
        s.useLocale(Locale.US);
        
        s.useDelimiter("(,|\\p{javaWhitespace})\\p{javaWhitespace}*");
   
        double simTime = s.nextDouble();
        s.nextLine();
        s.next("AIRPORTS");
        s.nextLine();
        while (!s.hasNext("WEATHER")) {
            double lon = s.nextDouble();
            double lat = s.nextDouble();
            int runways = s.nextInt();
            int maxWaitingPlanes = s.nextInt();
            String name = s.next();
            
            // AIRPORT
            sim.makeAirport(name, World.lonToKm(lon), World.latToKm(lat), 0, runways);
            
            s.nextLine();
        }
        s.next("WEATHER"); 
        s.nextLine();
        while (!s.hasNext("PLANES")) {
            // WEATHER
            double speedRatio = s.nextDouble(), 
                   lon1 = s.nextDouble(), 
                   lat1 = s.nextDouble(), 
                   lon2 = s.nextDouble(), 
                   lat2 = s.nextDouble(), 
                   dx = s.nextDouble(), 
                   dy = s.nextDouble(),
                   startTime = s.nextDouble(),
                   endTime = s.nextDouble();
            
            sim.makeWeather(speedRatio, 
                            World.lonToKm(lon1), 
                            World.latToKm(lat1), 
                            World.lonToKm(lon2), 
                            World.latToKm(lat2), 
                            World.hToMs(startTime), 
                            World.hToMs(endTime), 
                            World.speedHToMs(dx), 
                            World.speedHToMs(dy));
            s.nextLine();
        }
        s.next("PLANES");
        s.nextLine();
        while(s.hasNextLine()) {
            System.out.println("==PLANE==");
            s.next("PLANE");
            String source = s.next();
            String dest = s.next();
            double planeStartTime = s.nextDouble();
            s.nextLine();
            
            while(!s.hasNext("PLANE") && s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println("Line: " + line);
                String[] m = line.split(",");
                if (m.length == 2) {
                    System.out.println("Silence");
                } else if (m.length == 4) {
                    System.out.println("Trajectoire");
                } else {
                    System.out.println("ERREUR");
                }
            }

           
        }
    }
        
        
    public static void main(String[] argv) {
        ScenarioParser s = new ScenarioParser(null);
    }


}
