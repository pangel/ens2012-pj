
/**
 *
 * @author hp
 */
public class Airport extends AirportInformation {
	/**
     *
     * @param ac
     */
    public Airport(AirportCharacteristics ac){
		super(ac);
	}
	/**
     *
     * @param s
     */
    public void setStatus(AirportStatus s){
		status = s;
		
	}
	/**
     *
     * @param id
     */
    public void addWaitingPlane(FlightID id){
		waitingPlanes.add(id);
	}
	/**
     *
     * @param id
     */
    public void removeWaitingPlane(FlightID id){
		waitingPlanes.remove(id);
	}
        public String toString() {
            return this.name;
        }
}
