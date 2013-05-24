
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author hp
 */
public class Airport extends AirportInformation {

    private final double[] runways;

    /**
     *
     * @param ac
     */
    public Airport(AirportCharacteristics ac) {
        super(ac);
        this.runways = new double[this.numberOfRunways];
    }

    /**
     *
     * @param s
     */
    public void setStatus(AirportStatus s) {
        status = s;

    }

    /**
     *
     * @param id
     */
    public void addWaitingPlane(FlightID id) {
        waitingPlanes.add(id);
    }

    /**
     *
     * @param id
     */
    public void removeWaitingPlane(FlightID id) {
        waitingPlanes.remove(id);
    }

    public Collection<Plane> landPlanes() {
        Collection<Plane> landed = new ArrayList<>();
        synchronized (waitingPlanes) {
            double now = new Date().getTime();
            for (int i = 0; i < this.runways.length; i++) {
                while(waitingPlanes.peek() != null && waitingPlanes.peek().getPlane().getStatus() != FlightStatus.STATUS_WAITING_LANDING) {
                    waitingPlanes.poll();
                }

                if (waitingPlanes.peek() != null && (this.runways[i] == 0 || World.duration(now, this.runways[i]) >= World.sToMs(100))) {
                    landed.add(waitingPlanes.poll().getPlane());
                    this.runways[i] = now;
                }
            }
        }
        return landed;
    }

    public String toString() {
        return this.name;
    }

    public boolean acceptLanding() {
        for (int i = 0; i < this.runways.length; i++) {
            if (this.runways[i] == 0 || World.duration(new Date().getTime(), this.runways[i]) <= World.sToMs(100)) {        
                return true;
            }
        }
        return false;
    }

    public boolean acceptWaiting() {
        return this.waitingPlanes.size() < this.maxWaitingPlanes;
    }

    public boolean isOpen() {
        return this.status == AirportStatus.OPEN;
    }
}