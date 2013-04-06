/**
 * Constant properties of airports 
 */
public class AirportCharacteristics {
	/**
     *
     * @param name
     * @param position
     * @param numberOfRunways
     */
    public AirportCharacteristics(String name, Point3D position, int numberOfRunways){
		this.name = name;
		this.position = position;
		this.numberOfRunways = numberOfRunways;
		id = new AirportID();
	}
	/**
     *
     * @param ac
     */
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
