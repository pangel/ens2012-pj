
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller implements ControllerCommandInterface {

    private LinkedBlockingQueue<Runnable> transmissions;
    private ControllerDataInterface globalData;
    private Simulator simulator;
    private NewJFrame gui;

    Controller(ControllerDataInterface globalData, NewJFrame gui) {
        this.globalData = globalData;
        this.gui = gui;
        this.transmissions = new LinkedBlockingQueue<Runnable>();
    }

    public void control() {
        Runnable task;
        while (true) {
            try {
                task = this.transmissions.take();

                this.gui.addRequest(task);
//                task.run();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            Thread.wait(500);
        }
    }

    @Override
    public void respondTrajectory(final FlightID id, final Trajectory t) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                // ???
            }
        };
        this.transmissions.add(r);
    }

    @Override
    public void respondStatus(final FlightID id, final FlightStatus s) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                // ???
            }
        };
        this.transmissions.add(r);

    }

    @Override
    public void respondInitialSourceDestination(final FlightID id, final Airport source,
            final Airport dest) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                // ???
            }
        };
        this.transmissions.add(r);
    }

    public void respondDestinationAirport(FlightID id, AirportID airportId) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                // ???
            }
        };
        this.transmissions.add(r);

    }

    @Override
    public void respondSpeed(FlightID id, double speed) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                // ???
            }
        };
        this.transmissions.add(r);

    }

    @Override
    public void requestNewFlight(final AirportID s, final AirportID d) {
        System.out.println("requestNewFlight sync" + s + " " + d);
        final Controller self = this;
        final Airport source = self.globalData.getAirportByID(s);
        final Airport dest = self.globalData.getAirportByID(d);

        Runnable r = new Runnable() {
            public void run() {
                System.out.println("requestNewFlight async" + s + " " + d);
                FlightID planeID = new FlightID();
                Trajectory trajectory = new SegmentTrajectory(source.position, dest.position);
                self.getSimulator().respondNewFlight(planeID, s, d, trajectory);
            }

            public String toString() {
                return "Eclosion de dragon à " + source.name + " destiné à détruire " + dest.name;
            }
        };
        this.transmissions.add(r);
    }

    @Override
    public void requestLanding(final FlightID id) {
        final Controller self = this;
        final Airport destination = id.getPlane().getInitialDestinationAirport();
        Runnable r = new Runnable() {
            public void run() {
                long now = new Date().getTime();
                long delay = (long) (Math.random() * 10000);
                Date landingDate = new Date((now + delay));
                self.getSimulator().respondLanding(id, landingDate);
            }
        
            public String toString() {
                return "Détruire " + destination.name + " ?";
            }
        };
        this.transmissions.add(r);
    }

    @Override
    public void requestTakeoff(final FlightID id) {
        final Controller self = this;
        Runnable r = new Runnable() {
            public void run() {
                self.getSimulator().respondTakeoff(id);
            }
            
            public String toString() { return "Un dragon veut s'envoler !"; }
        };
        this.transmissions.add(r);
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }
}
