
public class GlobalData {
	public GlobalData(){
		
	}
	
	/** List of all airports */ 
	private java.util.List<Airport> airports;
	private Wheather wheather;
	Collection<FlightID> getWaitingPlanes (id:AirportID) {
		int i = 0;
		while(AirportCharacteristics.get(i).id != id){
			i++ 
			}
		return AirportCharacteristics.get(i).getWaitingsPlanes()
		}
	AirportStatus getStatus(id:AirportId){	
		int i = 0;
		while(AirportCharacteristics.get(i).id != id){
			i++ 
			}
		return AirportCharacteristics.get(i).getStatus()
		}
}

