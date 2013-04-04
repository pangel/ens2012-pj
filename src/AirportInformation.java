import java.util.*;
public class AirportInformation extends AirportCharacteristics {
	public AirportInformation(AirportCharacteristics ac)
	{
		super(ac);
	}
	AirportStatus getStatus(){
		return status;
	}
	Collection<FlightID> getWaitingPlanes(){
		return waitingPlanes;
	}
	protected AirportStatus status;
	protected Collection<FlightID> waitingPlanes;
}
