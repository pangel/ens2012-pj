import java.util.*;

/**
 *
 * @author hp
 */
public interface ControllerDataInterface {
	/**
     *
     * @return
     */
    public Collection<AirportCharacteristics>AirportCharacteristics();
	/**
     *
     * @param id
     * @return
     */
    public AirportStatus getStatus(AirportID id);
	/**
     *
     * @param id
     * @return
     */
    public Collection<FlightID> getWaitingPlanes(AirportID id);
	/**
     *
     * @param id
     * @return
     */
    public Airport getAirportByID(AirportID id);
}
