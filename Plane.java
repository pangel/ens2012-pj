import java.util.Date;

public class Plane {
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
	
	public void setTrajectory(Trajectory t){
		trajectory = t;
	}
	public void setStatus(FlightStatus s){
		status = s;
	}
	public void setTakeoffDate(Date d){
		takeoffDate = d;
	}

	private Trajectory trajectory;
	private FlightStatus status;
	private String name;
	private double speed;
	private Airport source, dest;
	private Date takeoffDate;
	private FlightID id;
}
