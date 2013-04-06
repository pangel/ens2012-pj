/** This interface defines the part of the communication protocol
 * between a Controller and a PlaneSimulator objects. This interface
 * is the set of requests that can be <b>sent</b> to Controller.
 * These are the set of messages that PlaneSimulator can asynchronously
 * send to the controller. 
 */

public interface ControllerCommandInterface {
	/**
     *
     * @param id
     * @param t
     */
    public void respondTrajectory(FlightID id, Trajectory t);
	/**
     *
     * @param id
     * @param s
     */
    public void respondStatus(FlightID id, FlightStatus s);
	/**
     *
     * @param id
     * @param source
     * @param dest
     */
    public void respondInitialSourceDestination(FlightID id, Airport source, Airport dest);
	/**
     *
     * @param id
     * @param speed
     */
    public void respondSpeed(FlightID id, double speed);
	/**
     *
     * @param source
     * @param dest
     */
    public void requestNewFlight(AirportID source, AirportID dest);
	/**
     *
     * @param id
     */
    public void requestLanding(FlightID id);
	/**
     *
     * @param id
     */
    public void requestTakeoff(FlightID id);
}
