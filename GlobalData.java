
public class GlobalData {
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

