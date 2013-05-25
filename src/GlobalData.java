
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;


/**
 *
 * @author hp
 */
public class GlobalData implements ControllerDataInterface {
    /**
     *
     */

 
    public GlobalData(){
	    this.airports = new ArrayList<Airport>();
	}
    
    /** List of all airports */ 
	private Wheather wheather;
	/**
     *
     */
    public Simulator simulator;
	
	
	/** List of all airports */ 
	//private java.util.List<Airport> airports;
	
//	public ConcurrentLinkedQueue<Carrier> controllerRequestQueue;
	public ArrayList<Airport> airports;
	
	/**
     *
     * @return
     */
    public Collection<AirportCharacteristics> AirportCharacteristics() {
		ArrayList<AirportCharacteristics> characteristics = new ArrayList<AirportCharacteristics>();
		Iterator<Airport> it = this.airports.iterator();
		while (it.hasNext()) {
			characteristics.add(it.next());
		}
		return characteristics;
	}
	
	/**
     *
     * @param id
     * @return airport
     * @throws IllegalArgumentException
     */
    public Airport getAirportByID(AirportID id) {
		for (Airport airport : this.airports) {
			if (airport.id == id) { return airport; }
		}
		throw new IllegalArgumentException("unknown airport id");
	}
    /**
    * @param name
    * @return airport
    * @return null
    */
    public Airport getAirportByName(String name) {
        for (Airport airport : this.airports) {
            if (airport.name == null ? name == null : airport.name.equals(name)) { return airport; }
        }
        return null;
    }

	/**
     *
     * @param id
     * @return
     */
    public Collection<FlightID> getWaitingPlanes (AirportID id) {
		return null;
		}
//
	/**
     *
     * @param id
     * @return
     */
    public AirportStatus getStatus(AirportID id){
		return null;	
    }
}
