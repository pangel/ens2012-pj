
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author hp
 */
public class Controller implements ControllerCommandInterface {

    private ControllerDataInterface globalData;
    private Simulator simulator;

    protected Set<TaskType> autoAccepts;
    protected LinkedBlockingQueue<Task> transmissions;

    Controller(ControllerDataInterface globalData) {
        this.globalData = globalData;
        this.transmissions = new LinkedBlockingQueue<Task>();
        this.autoAccepts = Collections.synchronizedSet(EnumSet.allOf(TaskType.class));
    }

    /**
     *
     */
    public void control() {
        while (true) {
            try {
                Task task = this.transmissions.take();
                if (this.autoAccepts.contains(task.type())) {
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param id
     * @param t
     */
    @Override
    public void respondTrajectory(final FlightID id, final Trajectory t) {
        final Controller self = this;
        if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                // ???
            }

            public TaskType type() {
                return TaskType.DEFAULT;
            }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     * @param s
     */
    @Override
    public void respondStatus(final FlightID id, final FlightStatus s) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                // ???
            }

            public TaskType type() {
                return TaskType.DEFAULT;
            }
        };
        this.transmissions.add(r);

    }

    /**
     *
     * @param id
     * @param source
     * @param dest
     */
    @Override
    public void respondInitialSourceDestination(final FlightID id, final Airport source,
            final Airport dest) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                // ???
            }

            public TaskType type() {
                return TaskType.DEFAULT;
            }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     * @param airportId
     */
    public void respondDestinationAirport(FlightID id, AirportID airportId) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                // ???
            }

            public TaskType type() {
                return TaskType.DEFAULT;
            }
        };
        this.transmissions.add(r);

    }

    /**
     *
     * @param id
     * @param speed
     */
    @Override
    public void respondSpeed(FlightID id, double speed) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                // ???
            }

            public TaskType type() {
                return TaskType.DEFAULT;
            }
        };
        this.transmissions.add(r);

    }

    /**
     *
     * @param s
     * @param d
     */
    @Override
    public void requestNewFlight(final AirportID s, final AirportID d) {
//        System.out.println("requestNewFlight sync" + s + " " + d);
        final Controller self = this;
        final Airport source = self.globalData.getAirportByID(s);
        final Airport dest = self.globalData.getAirportByID(d);

        Task r = new Task() {
            public void run() {
//                System.out.println("requestNewFlight async" + s + " " + d);
                FlightID planeID = new FlightID();
                Trajectory trajectory = new SegmentTrajectory(source.position, dest.position);
                self.getSimulator().respondNewFlight(planeID, s, d, trajectory);
            }

            public String toString() {
                return "Éclore dragon à " + source.name + " pour détruire " + dest.name + " ?";
            }

            public TaskType type() {
                return TaskType.REQUEST_NEWFLIGHT;
            }
        };
            
        this.transmissions.add(r);
    }
    
    public void requestNewFlightCustom(final FlightID planeID) {
//        System.out.println("requestNewFlight sync" + s + " " + d);
        final Controller self = this;
        final Airport source = planeID.getPlane().getInitialSourceAirport();
        final Airport dest = planeID.getPlane().getInitialDestinationAirport();

        Task r = new Task() {
            public void run() {
                Trajectory trajectory = new SegmentTrajectory(source.position, dest.position);
                self.getSimulator().respondNewFlight(planeID, source.id, dest.id, trajectory);
            }

            public String toString() {
                return "Éclore dragon à " + source.name + " pour détruire " + dest.name + " ?";
            }

            public TaskType type() {
                return TaskType.REQUEST_NEWFLIGHT;
            }
        };
            
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     */
    @Override
    public void requestLanding(final FlightID id) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        final Airport initialDestination = id.getPlane().getInitialDestinationAirport();
        Task r = new Task() {
            public void run() {
                Airport destination = null;

                if (!initialDestination.isOpen() || !initialDestination.acceptWaiting()) {
                     destination = self.nearbyAvailableAirport(initialDestination);
                }
                if (destination == null) {
                    destination = initialDestination;
                }
                Trajectory newTrajectory = null;
                if (destination != initialDestination) {
                    newTrajectory = new SegmentTrajectory(id.getPlane().getInitialDestinationAirport().position, destination.position);
                    self.getSimulator().requestChangeCourse(id, destination, newTrajectory);
                } else {
                    long now = new Date().getTime();
                    long delay = (long) (Math.random() * 10000);
                    Date landingDate = new Date((now + delay));
                    self.getSimulator().respondLanding(id, landingDate);
                }
            }

            public String toString() {
                return "Détruire " + initialDestination.name + " ?";
            }

            public TaskType type() {
                return TaskType.REQUEST_LANDING;
            }

        };
        this.transmissions.add(r);
    }
    
    private Airport nearestAirport(final Point3D position) {
        if (position == null) { return null; }
        Airport closest = null;
        for (AirportCharacteristics charac : this.globalData.AirportCharacteristics()) {
            Airport airport = this.globalData.getAirportByID(charac.id);
            double distance = position.distance(airport.position);
            if (closest == null || position.distance(closest.position) > position.distance(airport.position)) {
                closest = airport;
            }
        }
        return closest;
    }
    
    private Airport nearbyAvailableAirport(Airport source) {    
        for (AirportCharacteristics charac : this.globalData.AirportCharacteristics()) {
            Airport airport = this.globalData.getAirportByID(charac.id);
            double distance = source.position.distance(airport.position);
            //System.out.println("Distance: " + distance);
            if (source != airport && distance < 100) {
                return airport;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     */
    @Override
    public void requestTakeoff(final FlightID id) {
        final Controller self = this;
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Task r = new Task() {
            public void run() {
                self.getSimulator().respondTakeoff(id);
            }

            public String toString() {
                return "Un dragon veut s'envoler !";
            }

            public TaskType type() {
                return TaskType.REQUEST_TAKEOFF;
            }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @return
     */
    public Simulator getSimulator() {
        return simulator;
    }

    /**
     *
     * @param simulator
     */
    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }
    
    public void autoAccept(TaskType taskType) {
        this.autoAccepts.add(taskType);
    }

    public void UnAutoAccept(TaskType taskType) {
        this.autoAccepts.remove(taskType);
    }    

    public boolean doesAutoAccept(TaskType taskType) {
      return this.autoAccepts.contains(taskType);
    }
    public void requestEmergencyLanding(FlightID id, double fuel) {
         if (id.getPlane().insilence((new Date() ).getTime())) {return;}
        Plane plane = id.getPlane ();
        Point3D pos = plane.getPosition();
        if (pos != null) { 
          Airport newDest = nearestAirport(pos);
          plane.setDestination(newDest);
        }
    }
}
