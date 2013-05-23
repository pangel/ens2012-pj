
import java.util.Date;

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
    private double speed;
    private Airport source, dest;
    private Date takeoffDate;
    private Date landingDate;
    private Date lastUpdate;
    private FlightID id;

    void setDestination(Airport destination) {
        this.dest = destination;
    }
}
