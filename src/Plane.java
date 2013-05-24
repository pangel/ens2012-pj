
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author hp
 */
public class Plane {

    Plane(FlightID id, Airport source, Airport dest) {
        id.setPlane(this);
        this.id = id;
        this.source = source;
        this.dest = dest;

        this.speed = (double)1000 /* km/h */ / (1000*3600); /* km/ms */
        this.fuel = 1.5*(source.position.distance(dest.position))/this.speed;
        this.initialFuel = this.fuel;
        this.listError = new LinkedList();
    }

    /**
     *
     * @return
     */
    public Trajectory getTrajectory() {
        return trajectory;
    }

    /**
     *
     * @return
     */
    public Point3D getPosition() {
        return this.trajectory.current();
    }

    /**
     *
     * @return
     */
    public FlightStatus getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Airport getInitialSourceAirport() {
        return source;
    }

    /**
     *
     * @return
     */
    public Airport getInitialDestinationAirport() {
        return dest;
    }

    /**
     *
     * @return
     */
    public Date getTakeoffDate() {
        return takeoffDate;
    }

    /**
     *
     * @return
     */
    public FlightID getID() {
        return id;
    }

    /**
     *
     * @return
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @return
     */
    public Date getLandingDate() {
        return landingDate;
    }

    /**
     *
     * @param t
     */
    public void setTrajectory(Trajectory t) {
        trajectory = t;
    }

    /**
     *
     * @param s
     */
    public void setStatus(FlightStatus s) {
        status = s;
    }

    /**
     *
     * @param d
     */
    public void setTakeoffDate(Date d) {
        takeoffDate = d;
    }
    
    public void setFuel (double f) {
        fuel = f;
    }
    
    public boolean isNear (Plane p1, Plane p2) {
        Point3D a = p1.getPosition ();
        
        Point3D b = p2.getPosition ();
       
        return (a.distance(b) < 10 && p1 != p2);
        
    } 
    
    public boolean isTooNear (Plane p1, Plane p2) {
        Point3D a = p1.getPosition ();
        Point3D b = p2.getPosition ();
        return (a.distance(b) < 1 && p1 != p2);
    }
    
    /**
     *
     * @param d
     */
    public void setLandingDate(Date d) {
        landingDate = d;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    private Trajectory trajectory;
    private FlightStatus status;
    private String name;
    public double speed;
    private Airport source, dest;
    private Date takeoffDate;
    private Date landingDate;
    private Date lastUpdate;
    private FlightID id;
    public double fuel;
    public double initialFuel;
    public Collection<TrajectoryError> listError; 
    void setDestination(Airport destination) {
        this.dest = destination;
    }
    public boolean critical (Collection<Plane> hash, Plane p) {
        boolean acc = false;
        Iterator<Plane> it = hash.iterator ();
    while (it.hasNext ()) {
            Plane a = it.next ();            
            acc = acc || isNear (p,a);
            
    }
    return acc;
    }
    public boolean collision (Collection<Plane> hash, Plane p) {
        boolean acc = false;
        Iterator<Plane> it = hash.iterator ();
    while (it.hasNext ()) {
            Plane a = it.next ();
            acc = acc || (isTooNear (p,a) && p != a);
    }
    return acc;
    }
    
   
    public Plane isCritical (Collection<Plane> hash, Plane p) {
        boolean acc = false;
        Iterator<Plane> it = hash.iterator ();
        Plane a = it.next ();
    while (it.hasNext () && (!isNear (p,a) || p == a) ) {
            a = it.next ();
               }
    return a;
    //   Throw illegalUseOfisCritical;
    }
    public static boolean inerror (Date d,Plane p) {
        boolean acc = false;
        Iterator<TrajectoryError> it = p.listError.iterator ();
        if (p.listError.size() != 0) {
        TrajectoryError a = it.next();
        while (it.hasNext()) {
             acc = acc || (d.getTime() > a.getStartTime().getTime() && d.getTime() < a.getEndTime().getTime());
             a = it.next();
        }
        }
        return acc;
        
        
    }
    
    public static TrajectoryError isinerror (Date d,Plane p) {
        Iterator<TrajectoryError> it = p.listError.iterator ();
        TrajectoryError a = null;
        if (p.listError.size() != 0) {
        a = it.next();
        while (it.hasNext() && !(d.getTime() > a.getStartTime().getTime() && d.getTime() < a.getEndTime().getTime())) {
            a = it.next();
        }
        }
        return a;
        
        
    }
//    public void trajectoryError (Collection<TrajectoryError> l) {
//        Iterator<TrajectoryError> it = l.iterator ();
//        while (it.hasNext()) {
//            TrajectoryError error = it.next ();
//            Date starttime = error.getStartTime();
//            Date endtime = error.getEndTime();
//            double dx = error.getdx();
//            double dy = error.getdy();
//            e = new Point3D(position.x+dx*(endtime.getTime() - starttime.getTime())*speed)
//            trajectory.insert1(position.plus(e));
//            
//            }
 //   }
}   

