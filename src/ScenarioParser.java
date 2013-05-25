
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
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
    public static Queue<Plane> parseWith(Simulator sim, GlobalData gb) {
        Queue<Plane> scenarioPlanes = new PriorityQueue<>(10, new Comparator<Plane>() {
            @Override
            public int compare(Plane p1, Plane p2) {
                double p1s = p1.getStartTime();
                double p2s = p2.getStartTime();
                if (p1s < p2s) { return -1; }
                if (p2s < p1s) { return 1; }
                return 0;
            }
        });
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("scenario.sc");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScenarioParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
         
            s.next("PLANE");
            String source = s.next();
            String dest = s.next();
            double planeStartTime = World.hToMs(s.nextDouble());
            FlightID id = new FlightID();
            Plane plane = new Plane(id, gb.getAirportByName(source),gb.getAirportByName(dest));
            plane.setStartTime(planeStartTime);
            s.nextLine();
            
            while(!s.hasNext("PLANE") && s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println("Line: " + line);
                String[] m = line.split(",");
                if (m.length == 2) {
                    plane.setSilence(new Silence(World.hToMs(Double.parseDouble(m[0])), World.hToMs(Double.parseDouble(m[1]))));
                } else if (m.length == 4) {
                     plane.setError(new TrajectoryError(World.hToMs(Double.parseDouble(m[0])), World.hToMs(Double.parseDouble(m[1])), Double.parseDouble(m[2]), Double.parseDouble(m[3])));
                } else {
                     throw new IllegalArgumentException("Ligne impossible pour caractéristique de PLANE");
                }
            }

          scenarioPlanes.add(plane);
        }
        return scenarioPlanes;
    }
        
        

}
