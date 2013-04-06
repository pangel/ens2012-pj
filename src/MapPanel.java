
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel {

    private ArrayList<Airport> airports;
    private Graphics2D g2d;
    private Collection<Plane> planes;
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
                    this.drawDot(plane.getPosition().x, plane.getPosition().y, Color.blue);
                }
            }
        }
    }

    public void drawPlanes(Collection<Plane> planes) {
        this.planes = planes;
        this.repaint();
    }

    public void drawDot(double x, double y, Color color) {
        Color lastColor = g2d.getColor();
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        g2d.setColor(color);
        g2d.fill(circle);
        g2d.setColor(lastColor);
    }

    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }
}