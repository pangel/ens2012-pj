import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Simulator extends Thread implements SimulatorCommandInterface{
	
	private GlobalData globalData;
	private Collection<Plane> planes;
    private ConcurrentLinkedQueue<Runnable> transmissions;
    private Controller controller;

	public Simulator(){
		this.planes = Collections.synchronizedSet(new HashSet());
		this.transmissions = new ConcurrentLinkedQueue<Runnable>();
	}
	
	/** This function runs the whole simulation updating the positions
	 * of the planes, answering to the queries..*/
	private void simulate(){
		while (true) {
			// FIXME: stop loop when poll empty. restart it when thread is scheduled.
			Runnable transmission = this.transmissions.poll();
			transmission.run();
		}
	}
	
	public Plane getPlaneByID(FlightID id) {
		Iterator<Plane> it = this.planes.iterator();
		Plane plane;
		while (it.hasNext()) {
			plane = it.next();
			if (plane.getID() == id) { return plane; }
		}
		throw new IllegalArgumentException("unknown flight id");
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
	public void requestTrajectory(final FlightID id) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		Trajectory trajectory = id.getPlane().getTrajectory();
	    		self.controller.respondTrajectory(id, trajectory);
	        }
	    };
		this.transmissions.add(r);
	}
	
	public void requestStatus(final FlightID id){
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		FlightStatus status = id.getPlane().getStatus();
	    		self.controller.respondStatus(id, status);
	        }
	    };
		this.transmissions.add(r);
	}
	
	
	public void requestSpeed(final FlightID id) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		double speed= id.getPlane().getSpeed();
	    		self.controller.respondSpeed(id, speed);
	        }
	    };
		this.transmissions.add(r);
	}
	

	public void requestInitialSourceDestination(final FlightID id){
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		Airport sourceAirport = id.getPlane().getInitialSourceAirport();
	    		Airport destinationAirport = id.getPlane().getInitialDestinationAirport();
	    		self.controller.respondInitialSourceDestination(id, sourceAirport, destinationAirport);
	        }
	    };
		this.transmissions.add(r);
	}
	
	public void requestDestinationAirport(final FlightID id){
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	AirportID airportId = id.getPlane().getInitialDestinationAirport().id;
	    		self.controller.respondDestinationAirport(id, airportId);
	        }
	    };
		this.transmissions.add(r);
	}
	
	/* Private attributes */
    
	@Override
	public void respondTakeoff(final FlightID id) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		Plane plane = id.getPlane();
	    		plane.setStatus(FlightStatus.STATUS_INFLIGHT);
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void respondLanding(final FlightID id, final Date date) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	Plane plane = id.getPlane();
	        	plane.setStatus(FlightStatus.STATUS_WAITING_LANDING);
	        	plane.setLandingDate(date);
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void respondNewFlight(final FlightID id, final AirportID s, final AirportID d, final Trajectory traj) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
        		Plane plane = new Plane(id,self.globalData.getAirportByID(s),self.globalData.getAirportByID(d));
        		self.planes.add(plane);
        		plane.setTrajectory(traj);
        		plane.setStatus(FlightStatus.STATUS_WAITING_TAKEOFF);
        		self.controller.requestTakeoff(plane.getID());
	        }
	    };
		this.transmissions.add(r);
	}
}
