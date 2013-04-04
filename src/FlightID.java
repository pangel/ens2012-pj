public class FlightID {

	private Plane plane;
	
	FlightID() {
	}
	
	public Plane getPlane() {
		return plane;
	}
	
	public void setPlane(Plane plane) {
		if (this.plane != null) {
			throw new IllegalArgumentException("Plane already set for Flight!"); 
		} else {
			this.plane = plane;
		}
	}
}
