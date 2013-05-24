
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author hp
 */
public class Airport extends AirportInformation {

    private final long[] runways;

    /**
     *
     * @param ac
     */
    public Airport(AirportCharacteristics ac) {
        super(ac);
        this.runways = new long[this.numberOfRunways];
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

    public void landNextPlane() {
        synchronized (waitingPlanes) {
            Iterator<FlightID> it = waitingPlanes.iterator();
            if (it.hasNext()) {
                it.next();
                it.remove();               
            }
        }
        for (int i = 0; i < this.runways.length; i++) {
            if (this.runways[i] == 0 || this.runways[i] <= new Date().getTime() - 100 * 1000) {        
                this.runways[i] = new Date().getTime();
            }
        }
    }

    public String toString() {
        return this.name;
    }

    public boolean acceptLanding() {
        for (int i = 0; i < this.runways.length; i++) {
            if (this.runways[i] == 0 || this.runways[i] <= new Date().getTime() - 100 * 1000) {
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