
public class Airport extends AirportInformation {
	public Airport(AirportCharacteristics ac){
		super(ac);
	}
	public void setStatus(AirportStatus s){
		status = s;
		
	}
	public void addWaitingPlane(FlightID id){
		waitingPlanes.add(id);
	}
	public void removeWaitingPlane(FlightID id){
		waitingPlanes.remove(id);
	}
}
