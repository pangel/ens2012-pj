import java.util.*;
/**
 *
 * @author hp
 */
public class AirportInformation extends AirportCharacteristics {
	/**
     *
     * @param ac
     */
    public AirportInformation(AirportCharacteristics ac)
	{
		super(ac);
                this.waitingPlanes = new LinkedList<>();
	}
	AirportStatus getStatus(){
		return status;
	}
	Collection<FlightID> getWaitingPlanes(){
		return waitingPlanes;
	}
	/**
     *
     */
    protected AirportStatus status;
	/**
     *
     */
    protected Queue<FlightID> waitingPlanes;
}
