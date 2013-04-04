/**
 * Constant properties of airports 
 */
public class AirportCharacteristics {
	public AirportCharacteristics(String name, Point3D position, int numberOfRunways){
		this.name = name;
		this.position = position;
		this.numberOfRunways = numberOfRunways;
		id = new AirportID();
	}
	public AirportCharacteristics(AirportCharacteristics ac){
		name = ac.name;
		position = ac.position;
		numberOfRunways = ac.numberOfRunways;
		id = ac.id;
	}
	final Point3D position;
	final String name;
	final AirportID id;
	final int numberOfRunways;
}
