import java.util.Date;

public class Plane {
	Plane(FlightID id, Airport source, Airport dest) {
		id.setPlane(this);
		this.id = id;
		this.source = source;
		this.dest = dest;
	}
	public Trajectory getTrajectory(){
		return trajectory;
	}
	public FlightStatus getStatus(){
		return status;
	}
	public String getName(){
		return name;
	}
	public Airport getInitialSourceAirport(){
		return source;
	}
	public Airport getInitialDestinationAirport(){
		return dest;
	}
	public Date getTakeoffDate(){
		return takeoffDate;
	}
	public FlightID getID(){
		return id;
	}
	public double getSpeed(){
		return speed;
	}
	public Date getLandingDate() {
		return landingDate;
	}
	
	public void setTrajectory(Trajectory t){
		trajectory = t;
	}
	public void setStatus(FlightStatus s){
		status = s;
	}
	public void setTakeoffDate(Date d){
		takeoffDate = d;
	}
	public void setLandingDate(Date d) {
		landingDate = d;
	}

	private Trajectory trajectory;
	private FlightStatus status;
	private String name;
	private double speed;
	private Airport source, dest;
	private Date takeoffDate;
	private Date landingDate;
	private FlightID id;
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    private Date lastUpdate;
}
