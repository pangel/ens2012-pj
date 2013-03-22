public class Controller implements ControllerCommandInterface{

	@Override
	public void respondTrajectory(FlightID id, Trajectory t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondStatus(FlightID id, FlightStatus s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondInitialSourceDestination(FlightID id, Airport source,
			Airport dest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respondSpeed(FlightID id, double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestNewFlight(AirportID source, AirportID dest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestLanding(FlightID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestTakeoff(FlightID id) {
		// TODO Auto-generated method stub
		
	}

	private ControllerDataInterface globalData;
}
