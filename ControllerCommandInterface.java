/** This interface defines the part of the communication protocol
 * between a Controller and a PlaneSimulator objects. This interface
 * is the set of requests that can be <b>sent</b> to Controller.
 * These are the set of messages that PlaneSimulator can asynchronously
 * send to the controller. 
 */

public interface ControllerCommandInterface {
	public void respondTrajectory(FlightID id, Trajectory t);
	public void respondStatus(FlightID id, FlightStatus s);
	public void respondInitialSourceDestination(FlightID id, Airport source, Airport dest);
	public void respondSpeed(FlightID id, double speed);
	public void requestNewFlight(AirportID source, AirportID dest);
	public void requestLanding(FlightID id);
	public void requestTakeoff(FlightID id);
}
