
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class World {
    //    public static final double mapWidth = 300;
    public static final double earthRadius = 50;
    public static double speedup = 1000; // Contraction temporelle
    static List<Weather> weather = new ArrayList<Weather>();
    
    public static double mapWidth() {
//        return 2 * Math.PI * earthRadius;
        return 40000; // 40km de large, 64km par pixel, 625 pixels de large
    }
   
    public static double earthRadius() {
        return earthRadius;
    }
    
    public static double kmToPx(double i) {
        return i/64;
    }
    
    public static double pxToKm(double i) {
        return i*64;
    }
    
    public static double lonToKm(double lon) {
        return (lon+180) * (World.mapWidth()/360);
    }
    
    public static double latToKm(double lat) {
        return (-lat+90) * (40000/180);
    }
    
    public static double speedHToMs(double lengthPerH) {
        return lengthPerH / (1000*3600);
    }
    
    public static double hToMs(double h) {
        return h * 1000 * 3600;
    }
    
    public static double sToMs(double s) {
        return s * 1000;
    }
    
    public static double msToH(double ms) {
        return ms/(1000*3600);
    }
    
    // Elapsed time in milliseconds, with speedup factor
    public static double duration(double now, double before) {
        return (now - before)*speedup;
    }
    
}
