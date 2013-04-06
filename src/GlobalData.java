
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;


public class GlobalData implements ControllerDataInterface {
    public GlobalData(){
	    this.airports = new ArrayList<Airport>();
	}
    
    /** List of all airports */ 
	private Wheather wheather;
	public Simulator simulator;
	
	
	/** List of all airports */ 
	//private java.util.List<Airport> airports;
	
//	public ConcurrentLinkedQueue<Carrier> controllerRequestQueue;
	public ArrayList<Airport> airports;
	
	public Collection<AirportCharacteristics>AirportCharacteristics() {
		ArrayList<AirportCharacteristics> characteristics = new ArrayList<AirportCharacteristics>();
		Iterator<Airport> it = this.airports.iterator();
		while (it.hasNext()) {
			characteristics.add(it.next());
		}
		return characteristics;
	}
	
	public Airport getAirportByID(AirportID id) {
		for (Airport airport : this.airports) {
			if (airport.id == id) { return airport; }
		}
		throw new IllegalArgumentException("unknown airport id");
	}

	public Collection<FlightID> getWaitingPlanes (AirportID id) {
		return null;
//		int i = 0;
//		while(AirportCharacteristics.get(i).id != id){
//			i++ 
//			}
//		return AirportCharacteristics.get(i).getWaitingsPlanes()
		}
//
	public AirportStatus getStatus(AirportID id){
		return null;	
//		int i = 0;
//		while(AirportCharacteristics.get(i).id != id){
//			i++ 
//			}
//		return AirportCharacteristics.get(i).getStatus()
//
//		}
//		return AirportsCharacteristics.get(i).getWaitingsPlanes();
//		i = 0;
    }
//    AirportStatus getStatus(id AirportId){	
//	 int i = 0;
//	while(AirportsCharacteristics.get(i).id != id){
//	    i++ ;
//	}
//	i = 0;
//	return AirportsCharacteristics.get(i).getStatus();
//    }

//	Collection<FlightID> getWaitingPlanes (AirportID id) {
//		int i = 0;
//		while(AirportCharacteristics.get(i).id != id){
//			i++ 
//			}
//		return AirportCharacteristics.get(i).getWaitingsPlanes()
//		}
//
//	AirportStatus getStatus(AirportId id){	
//		int i = 0;
//		while(AirportCharacteristics.get(i).id != id){
//			i++ 
//			}
//		return AirportCharacteristics.get(i).getStatus()
//		}

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