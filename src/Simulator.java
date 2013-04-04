import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.swt.widgets.*;
public class Simulator extends Thread implements SimulatorCommandInterface{
	
	private GlobalData globalData;
	private Collection<Plane> planes;
    private LinkedBlockingQueue<Runnable> transmissions;
    private Controller controller;
	private Airport airportA;
	private Airport airportB;
    
    public static void main(String args[]) throws InterruptedException {


    	

    	
//    		Display display = new Display ();
//    		Shell shell = new Shell(display);
//    		shell.open ();
//    		while (!shell.isDisposed ()) {
//    			if (!display.readAndDispatch ()) display.sleep ();
//    		}
//    		display.dispose ();
//    	}
//    	
    	
        GlobalData globalData = new GlobalData();
        Simulator simulator = new Simulator(globalData);
        Controller controller = new Controller((ControllerDataInterface) globalData);
        
        simulator.setController(controller);
        simulator.setup();

        simulator.start();

        while(!Simulator.interrupted()) {
        	controller.control();
        }
    }
    
    private void setup() {

        this.controller.setSimulator(this);
        Airport airportA = new Airport(new AirportCharacteristics("Airport A", new Point3D(0,0,0), 2));
        Airport airportB = new Airport(new AirportCharacteristics("Airport B", new Point3D(10,10,0), 2));

        this.globalData.airports.add(airportA);
        this.globalData.airports.add(airportB);
        this.airportA = airportA;
        this.airportB = airportB;
		System.out.println(""+this.airportA.id);
		this.controller.requestNewFlight(this.airportA.id, this.airportB.id);

    }
    
	public Simulator(GlobalData globalData){
		this.globalData = globalData;
		this.planes = Collections.synchronizedSet(new HashSet());
		this.transmissions = new LinkedBlockingQueue<Runnable>();
		
	}
	/** This function runs the whole simulation updating the positions
	 * of the planes, answering to the queries..*/
	private void simulate(){
		while (true) {
			
//				System.out.println("go\n");

			for (Plane plane: this.planes) {
				if (plane.getStatus() == FlightStatus.STATUS_INFLIGHT) {
					SegmentTrajectory.update(plane, new Date());
					if (plane.getTrajectory().terminated()) {
			        	plane.setStatus(FlightStatus.STATUS_WAITING_LANDING);
						this.controller.requestLanding(plane.getID());
					}
				}
			}
			Runnable task;
			task = this.transmissions.poll();
			if (task != null) { task.run(); }
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
	    		self.getController().respondTrajectory(id, trajectory);
	        }
	    };
		this.transmissions.add(r);
	}
	
	public void requestStatus(final FlightID id){
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		FlightStatus status = id.getPlane().getStatus();
	    		self.getController().respondStatus(id, status);
	        }
	    };
		this.transmissions.add(r);
	}
	
	
	public void requestSpeed(final FlightID id) {
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	    		double speed= id.getPlane().getSpeed();
	    		self.getController().respondSpeed(id, speed);
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
	    		self.getController().respondInitialSourceDestination(id, sourceAirport, destinationAirport);
	        }
	    };
		this.transmissions.add(r);
	}
	
	public void requestDestinationAirport(final FlightID id){
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	AirportID airportId = id.getPlane().getInitialDestinationAirport().id;
	    		self.getController().respondDestinationAirport(id, airportId);
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
	        	plane.setLandingDate(date);
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void respondNewFlight(final FlightID id, final AirportID s, final AirportID d, final Trajectory traj) {
		System.out.println("respondNewFlight sync"+id+" "+s+" "+d);
		final Simulator self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	System.out.println("respondNewFlight async"+id+" "+s+" "+d);
        		Plane plane = new Plane(id,self.globalData.getAirportByID(s),self.globalData.getAirportByID(d));
        		self.planes.add(plane);
        		plane.setTrajectory(traj);
        		plane.setStatus(FlightStatus.STATUS_WAITING_TAKEOFF);
        		self.getController().requestTakeoff(plane.getID());
	        }
	    };
		this.transmissions.add(r);
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
