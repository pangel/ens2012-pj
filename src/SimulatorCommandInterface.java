/** This interface defines the part of the communication protocol
 * between a Controller and a PlaneSimulator objects. This interface
 * is the set of requests that can be <b>sent</b> to PlaneSimulator.
 * These are the set of messages that Controller can asynchronously
 * send to the planes. 
 *
 */
public interface SimulatorCommandInterface {
	/**
     *
     * @param id
     */
    public void requestTrajectory(FlightID id);
	/**
     *
     * @param id
     */
    public void requestSpeed(FlightID id);
	/**
     *
     * @param id
     */
    public void requestStatus(FlightID id);
	/**
     *
     * @param id
     */
    public void requestInitialSourceDestination(FlightID id);
	// FIXME: Modified: removed 2nd argument of type Trajectory. Add it back if it seems useful.
	/**
     *
     * @param id
     */
    public void respondTakeoff(FlightID id);
	/**
     *
     * @param id
     * @param date
     */
    public void respondLanding(FlightID id, java.util.Date date);
	/**
     *
     * @param id
     * @param source
     * @param dest
     * @param traj
     */
    public void respondNewFlight(FlightID id, AirportID source, AirportID dest, Trajectory traj);
}
