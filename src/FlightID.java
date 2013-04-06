/**
 *
 * @author hp
 */
public class FlightID {

	private Plane plane;
	
	FlightID() {
	}
	
	/**
     *
     * @return
     */
    public Plane getPlane() {
		return plane;
	}
	
	/**
     *
     * @param plane
     */
    public void setPlane(Plane plane) {
		if (this.plane != null) {
			throw new IllegalArgumentException("Plane already set for Flight!"); 
		} else {
			this.plane = plane;
		}
	}
}
