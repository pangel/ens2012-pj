import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;


public class GlobalData {
<<<<<<< HEAD
    public GlobalData(){
	    
	}
    
    /** List of all airports */ 
    private java.util.List<Airport> airports;
	private Wheather wheather;
    public LinkedList airportsCharacteristics
    public Collection getWaitingPlanes (id AirportID) {
	int i = 0;
	while(AirportsCharacteristics.get(i).id != id){
		    i++ ;
=======
	public Simulator simulator;
	public GlobalData(){
		
	}
	
	/** List of all airports */ 
	//private java.util.List<Airport> airports;
	private Wheather wheather;
	
//	public ConcurrentLinkedQueue<Carrier> controllerRequestQueue;
	public ConcurrentSkipListSet<Airport> airports;
	
	public Collection<AirportCharacteristics>AirportCharacteristics() {
		ArrayList<AirportCharacteristics> characteristics = new ArrayList<AirportCharacteristics>();
		Iterator<Airport> it = this.airports.iterator();
		while (it.hasNext()) {
			characteristics.add(it.next());
		}
		return characteristics;
	}
	
	public Airport getAirportByID(AirportID id) {
		Iterator<Airport> it = this.airports.iterator();
		Airport airport;
		while (it.hasNext()) {
			airport = it.next();
			if (airport.id == id) { return airport; }
		}
		throw new IllegalArgumentException("unknown airport id");
	}

	Collection<FlightID> getWaitingPlanes (AirportID id) {
		int i = 0;
		while(AirportCharacteristics.get(i).id != id){
			i++ 
			}
		return AirportCharacteristics.get(i).getWaitingsPlanes()
		}

	AirportStatus getStatus(AirportId id){	
		int i = 0;
		while(AirportCharacteristics.get(i).id != id){
			i++ 
			}
		return AirportCharacteristics.get(i).getStatus()
>>>>>>> 9004d94fc3cf6967fdcb6bc8fed71c69c5254094
		}
		return AirportsCharacteristics.get(i).getWaitingsPlanes();
		i = 0;
    }
    AirportStatus getStatus(id AirportId){	
	 int i = 0;
	while(AirportsCharacteristics.get(i).id != id){
	    i++ ;
	}
	i = 0;
	return AirportsCharacteristics.get(i).getStatus();
    }
}

	
	
}
//
//abstract class Carrier {
//	protected Simulator simulator;
//	void execute() {}
//}



//class StatusCarrier extends Carrier {
//	private FlightID id;
//	private FlightStatus status;
//	
//	StatusCarrier(Simulator simulator, FlightID id) {
//		this.simulator = simulator;
//		this.id = id;
//	}
//	
//	void execute() {
//		FlightStatus status = this.simulator.getPlaneByID(this.id).getStatus();
//		CONTROLLER.respondStatus(id, status);
//	}
//}
//
//class TrajectoryCarrier extends Carrier {
//	private FlightID id;
//	private FlightStatus status;
//	
//	StatusCarrier(Simulator simulator, FlightID id) {
//		this.simulator = simulator;
//		this.id = id;
//	}
//	
//	void execute() {
//		FlightStatus status = this.simulator.getPlaneByID(this.id).getStatus();
//		CONTROLLER.respondStatus(id, status);
//	}
//}
//
//class StatusCarrier extends Carrier {
//	private FlightID id;
//	private FlightStatus status;
//	
//	StatusCarrier(Simulator simulator, FlightID id) {
//		this.simulator = simulator;
//		this.id = id;
//	}
//	
//	void execute() {
//		FlightStatus status = this.simulator.getPlaneByID(this.id).getStatus();
//		CONTROLLER.respondStatus(id, status);
//	}
//}
//
//class StatusCarrier extends Carrier {
//	private FlightID id;
//	private FlightStatus status;
//	
//	StatusCarrier(Simulator simulator, FlightID id) {
//		this.simulator = simulator;
//		this.id = id;
//	}
//	
//	void execute() {
//		FlightStatus status = this.simulator.getPlaneByID(this.id).getStatus();
//		CONTROLLER.respondStatus(id, status);
//	}
//}
//
//class StatusCarrier extends Carrier {
//	private FlightID id;
//	private FlightStatus status;
//	
//	StatusCarrier(Simulator simulator, FlightID id) {
//		this.simulator = simulator;
//		this.id = id;
//	}
//	
//	void execute() {
//		FlightStatus status = this.simulator.getPlaneByID(this.id).getStatus();
//		CONTROLLER.respondStatus(id, status);
//	}
//}
