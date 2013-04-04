import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller implements ControllerCommandInterface{
	private LinkedBlockingQueue<Runnable> transmissions;
	private ControllerDataInterface globalData;
	private Simulator simulator;
	
	Controller(ControllerDataInterface globalData) {
		this.globalData = globalData;
		this.transmissions = new LinkedBlockingQueue<Runnable>();
	}
	
	public void control() {
		Runnable task;
		while (true) {
			try {
				task = this.transmissions.take();
				task.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	@Override
	public void respondTrajectory(final FlightID id, final Trajectory t) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	// ???
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void respondStatus(final FlightID id, final FlightStatus s) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	// ???
	        }
	    };
		this.transmissions.add(r);
		
	}

	@Override 
	public void respondInitialSourceDestination(final FlightID id, final Airport source,
			final Airport dest) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	// ???
	        }
	    };
		this.transmissions.add(r);
	}
	
	
	public void respondDestinationAirport(FlightID id, AirportID airportId) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	// ???
	        }
	    };
		this.transmissions.add(r);
		
	}

	@Override
	public void respondSpeed(FlightID id, double speed) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	// ???
	        }
	    };
		this.transmissions.add(r);
		
	}
	
	@Override
	public void requestNewFlight(final AirportID s, final AirportID d) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	FlightID planeID = new FlightID();
	        	Airport source = self.globalData.getAirportByID(s);
	        	Airport dest = self.globalData.getAirportByID(d);
	        	Trajectory trajectory = new SegmentTrajectory(source.position, dest.position);
	        	self.getSimulator().respondNewFlight(planeID,s,d,trajectory);
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void requestLanding(final FlightID id) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	long now = new Date().getTime();
	        	long delay = (long)(Math.random() * 10000);
	        	Date landingDate = new Date((now+delay));
	        	self.getSimulator().respondLanding(id, landingDate);
	        }
	    };
		this.transmissions.add(r);
	}

	@Override
	public void requestTakeoff(final FlightID id) {
		final Controller self = this;
	    Runnable r = new Runnable() {
	        public void run() {
	        	self.getSimulator().respondTakeoff(id);
	        }
	    };
		this.transmissions.add(r);
	}
	public Simulator getSimulator() {
		return simulator;
	}
	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}
}
