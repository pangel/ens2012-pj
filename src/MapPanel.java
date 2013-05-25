
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
public class MapPanel extends JPanel {

    private ArrayList<Airport> airports;
    private Graphics2D g2d;
    private Collection<Plane> planes;
    private Collection<Weather> weathers;
    private Collection<Point3D> crashes = new ArrayList<Point3D>();
    // fields, constructors, methods etc..

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.g2d = g2d;
        BufferedImage img;
        try {
            img = ImageIO.read(new File("map_middle.jpg"));
            g.drawImage(img, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Point3D crash: this.crashes) {
            this.drawDot(crash.x, crash.y, Color.black);
        }
                
        if (this.airports != null) {
            for (Airport airport : this.airports) {
                this.drawDot(airport.position.x, airport.position.y, Color.red);
            }
        }
                
        if (this.planes != null) {
            synchronized (this.planes) {
                Iterator<Plane> it = this.planes.iterator();
                while (it.hasNext()) {
                    
                    Plane plane = it.next();
                    Color color;
                    if (plane.getStatus() == FlightStatus.STATUS_CRASHED) {
                        it.remove();
                        this.crashes.add(plane.getPosition());
                    } else {
                        this.drawDot(plane.getPosition().x, plane.getPosition().y, Color.blue);
                    }
                }
            }
        }
        
        if (this.weathers != null) {
            synchronized (this.weathers) {
                Iterator<Weather> it = this.weathers.iterator();
                while (it.hasNext()) {
                    Weather weather = it.next();
                    if (weather.getActive()) {
                        Color col = new Color((float)(1-weather.speedRatio),(float)0.05,(float)weather.speedRatio,(float)0.4);
                        Point3D p1 = weather.p1, p2 = weather.p2;
                        if (p1.x > p2.x) {
                            this.drawRect(new Point3D(0,p1.y,p1.z), p2, col);
                            this.drawRect(p1, new Point3D(World.mapWidth(), p2.y,p2.z),col);
                        } else {
                            
                            this.drawRect(p1,p2,col);
                        }
                    }
                }
            }
        }

    }

    
    public void setPlanes(Collection<Plane> planes) {
        this.planes = planes;
    }
    
    public void setWeathers(Collection<Weather> weather) {
        this.weathers = weather;
    }

    /**
     *
     * @param x
     * @param y
     * @param color
     */
    public void drawDot(double x, double y, Color color) {
        double dotSize = 10;
        Color lastColor = g2d.getColor();
        Ellipse2D.Double circle = new Ellipse2D.Double(World.kmToPx(x) - dotSize/2, World.kmToPx(y) - dotSize/2, dotSize, dotSize);
        g2d.setColor(color);
        g2d.fill(circle);
        g2d.setColor(lastColor);
    }
    
    public void drawRect(Point3D orig, Point3D end, Color color) {
        Color lastColor = g2d.getColor();
        Rectangle2D.Double rect = new Rectangle2D.Double(World.kmToPx(orig.x),World.kmToPx(orig.y), World.kmToPx(end.x-orig.x), World.kmToPx(end.y-orig.y));
        g2d.setColor(color);
        g2d.fill(rect);
        g2d.setColor(lastColor);
    }

    /**
     *
     * @param airports
     */
    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }
    
    
}