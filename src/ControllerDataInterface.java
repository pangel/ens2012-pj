import java.util.*;

public interface ControllerDataInterface {
	public Collection<AirportCharacteristics>AirportCharacteristics();
	public AirportStatus getStatus(AirportID id);
	public Collection<FlightID> getWaitingPlanes(AirportID id);
	public Airport getAirportByID(AirportID id);
}
