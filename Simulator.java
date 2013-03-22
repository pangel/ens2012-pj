import java.util.*;

public class Simulator extends Thread implements SimulatorCommandInterface{
	
	public Simulator(){
	}
	
	/** This function runs the whole simulation updating the positions
	 * of the planes, answering to the queries..*/
	private void simulate(){
		
	}
	/** The thread's main loop function */
	public void run(){
		simulate();
	}
	
	/* Implementation of PlaneSimulatorInterface interface.
	 * The functions below are used for asynchronous communication
	 * between PlaneSimulator and Controller. To make a request or answer 
	 * an early request, Controller will call these functions, then wait
	 * for the answer.
	 */
	public void requestTrajectory(FlightID id){
		
	}
	public void requestStatus(FlightID id){
		
	}
	public void requestInitialSourceDestination(FlightID id){
		
	}
	public void requesteTakeoff(FlightID id){
		
	}
	public void requestLanding(FlightID id){
		
	}
	
	/* Private attributes */
	
	private GlobalData globalData;
	private Collection<Plane> planes;
	@Override
	public void requestSpeed(FlightID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondTakeoff(FlightID id, Trajectory traj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondLanding(FlightID id, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondNewFlight(FlightID id, AirportID s, AirportID d,Trajectory traj) {
		// TODO Auto-generated method stub
		
	}
}
