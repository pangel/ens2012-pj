
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author hp
 */
public class Simulator extends Thread implements SimulatorCommandInterface {

    private GlobalData globalData;
    private Collection<Plane> planes;
    private LinkedBlockingQueue<Runnable> transmissions;
    private Controller controller;
    private Airport airportA;
    private Airport airportB;

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {



        GlobalData globalData = new GlobalData();
        final NewJFrame gui = new NewJFrame();
        Simulator simulator = new Simulator(globalData, gui);
        GUIController controller = new GUIController((ControllerDataInterface) globalData, gui);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });


        simulator.setController(controller);
        simulator.setup();
        simulator.start();


//        InterfaceGUI gui = new InterfaceGUI(simulator.airportsHash, simulator.planesHash);

//        gui.run();
//        gui.start();
//        (new Thread(gui)).start();

        while (!Simulator.interrupted()) {
            controller.control();
        }
    }
    private final NewJFrame gui;

    private void setup() {


        this.controller.setSimulator(this);
//
        this.makeAirport("Winterfell", GlobalData.toKm(300), GlobalData.toKm(280), 0, 2);
        this.makeAirport("Dothraki Sea", GlobalData.toKm(700), GlobalData.toKm(600), 10, 2);
        this.makeAirport("Quarth", GlobalData.toKm(900), GlobalData.toKm(900), 0, 2);
        this.makeAirport("King's landing", GlobalData.toKm(385), GlobalData.toKm(530), 0, 2);
        this.makeAirport("Lannisport", GlobalData.toKm(120), GlobalData.toKm(700), 0, 2);
//        this.makeAirport("Volantis", 20, 20, 0, 2);
        this.makeAirport("The Wall", GlobalData.toKm(380), GlobalData.toKm(90), 0, 2);
        this.makeAirport("Pyke Castle", GlobalData.toKm(130), GlobalData.toKm(580), 0, 2);
//        
//        this.makeAirport("East", 10,300,0,2);
//        this.makeAirport("West", 700,300,0,2);
        
        this.gui.setAirports(this.globalData.airports);
        this.gui.setPlanes(this.planes);
    }

    public Airport makeAirport(String name, double x, double y, double z, int runways) {
        Airport airport = new Airport(new AirportCharacteristics(name, new Point3D(x, y, z), runways));
        this.globalData.airports.add(airport);
        return airport;
    }

    /**
     *
     * @param globalData
     * @param gui
     */
    public Simulator(GlobalData globalData, NewJFrame gui) {
        this.gui = gui;
        this.globalData = globalData;
        this.planes = Collections.synchronizedSet(new HashSet<Plane>());
        this.transmissions = new LinkedBlockingQueue<Runnable>();
    }

    /**
     * This function runs the whole simulation updating the positions of the
     * planes, answering to the queries..
     */
    private void simulate() {
        while (true) {
            synchronized (this.planes) {
                Iterator<Plane> it = this.planes.iterator();
                while (it.hasNext()) {
                    Plane plane = it.next();
                    if (plane.getStatus() == FlightStatus.STATUS_INFLIGHT) {
//                        SegmentTrajectory.update(plane, new Date());
                        Date now = new Date();
                        Date last = plane.getLastUpdate();
                        plane.getTrajectory().update(now, last, plane.getSpeed());
                        plane.setLastUpdate(now);
//                        System.out.println(plane.getPosition());
                        if (plane.getTrajectory().terminated()) {
                            plane.setStatus(FlightStatus.STATUS_WAITING_LANDING);
                            this.controller.requestLanding(plane.getID());
                        }
                    }
                }
            }
            this.gui.repaintMap();
            
            Runnable task;
            task = this.transmissions.poll();
            if (task != null) {
                task.run();
            }
            
            for (Airport airport : this.globalData.airports) {
                //System.out.println("Airport " + airport + " : " + airport.acceptLanding() + ","+airport.acceptWaiting());
                while (!airport.waitingPlanes.isEmpty() && airport.acceptLanding()) {
                    System.out.println("Landing a plane at " + airport);
                    airport.landNextPlane();
                }
            }
            
            try {
                Thread.sleep(30);
                
                if (Math.random() < 0.10*90/500) { 
                    Pair<Airport, Airport> trip = this.getRandomTrip();
                    
                    this.controller.requestNewFlight(trip.fst.id, trip.snd.id);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Plane getPlaneByID(FlightID id) {
        Iterator<Plane> it = this.planes.iterator();
        Plane plane;
        while (it.hasNext()) {
            plane = it.next();
            if (plane.getID() == id) {
                return plane;
            }
        }
        throw new IllegalArgumentException("unknown flight id");
    }
    
    /**
     *
     * @return
     */
    public Pair<Airport, Airport> getRandomTrip() {
        Random random = new Random();
        int max = this.globalData.airports.size();
        int sourceIndex = random.nextInt(max);
        int destIndex = random.nextInt(max);
        while (sourceIndex == destIndex) { destIndex = random.nextInt(max); }
        Airport source = this.globalData.airports.get(sourceIndex);        
        Airport dest = this.globalData.airports.get(destIndex);
        return new Pair<Airport, Airport>(source, dest);
    }
        
    /**
     * The thread's main loop function
     */
    public void run() {
        simulate();
    }

    /* Implementation of PlaneSimulatorInterface interface.
     * The functions below are used for asynchronous communication
     * between PlaneSimulator and Controller. To make a request or answer 
     * an early request, Controller will call these functions, then wait
     * for the answer.
     */
    /**
     *
     * @param id
     */
    public void requestTrajectory(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                Trajectory trajectory = id.getPlane().getTrajectory();
                self.controller.respondTrajectory(id, trajectory);
            }
            
            public TaskType type() { return TaskType.REQUEST_TRAJECTORY; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     */
    public void requestStatus(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                FlightStatus status = id.getPlane().getStatus();
                self.controller.respondStatus(id, status);
            }
            public TaskType type() { return TaskType.DEFAULT; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     */
    public void requestSpeed(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                double speed = id.getPlane().getSpeed();
                self.controller.respondSpeed(id, speed);
            }
            public TaskType type() { return TaskType.DEFAULT; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     */
    public void requestInitialSourceDestination(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                Airport sourceAirport = id.getPlane().getInitialSourceAirport();
                Airport destinationAirport = id.getPlane().getInitialDestinationAirport();
                self.controller.respondInitialSourceDestination(id, sourceAirport, destinationAirport);
            }
            public TaskType type() { return TaskType.DEFAULT; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     */
    public void requestDestinationAirport(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                AirportID airportId = id.getPlane().getInitialDestinationAirport().id;
                self.controller.respondDestinationAirport(id, airportId);
            }
            public TaskType type() { return TaskType.DEFAULT; }
        };
        this.transmissions.add(r);
    }

    /* Private attributes */
    /**
     *
     * @param id
     */
    @Override
    public void respondTakeoff(final FlightID id) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                Plane plane = id.getPlane();
                plane.setStatus(FlightStatus.STATUS_INFLIGHT);
                plane.setLastUpdate(new Date());
            }
            public TaskType type() { return TaskType.RESPONSE_TAKEOFF; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     * @param date
     */
   
    public void respondLanding(final FlightID id, final Date date) {
        final Simulator self = this;
        final Airport destination = id.getPlane().getInitialDestinationAirport();
        final long diff = date.getTime() - (new Date()).getTime();
        Task r = new Task() {
            public void run() {
                Plane plane = id.getPlane();
                plane.setLandingDate(date);
                destination.addWaitingPlane(id);
                self.planes.remove(plane);
            }
            public String toString() {
                return "" + destination.name + "sera détruit dans" + (diff/1000) + " secondes !";
            }
            public TaskType type() { return TaskType.RESPONSE_LANDING; }
        };
        this.transmissions.add(r);
    }
    
    public void requestChangeCourse(final FlightID id, final Airport newDestination, final Trajectory newTrajectory) {
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
                Plane plane = id.getPlane();
                plane.setStatus(FlightStatus.STATUS_INFLIGHT);
                
                System.out.println("Oh hey, you should be changing course!!! " + id);
                System.out.println("The current trajectory is :" + plane.getTrajectory());
                plane.setDestination(newDestination);
                plane.setTrajectory(newTrajectory);
            }
            
            public String toString() {
                return "Nouvelle destination : " + newDestination.name + ".";
            }
            public TaskType type() { return TaskType.REQUEST_CHANGE_COURSE; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @param id
     * @param s
     * @param d
     * @param traj
     */
    @Override
    public void respondNewFlight(final FlightID id, final AirportID s, final AirportID d, final Trajectory traj) {
//        System.out.println("respondNewFlight sync" + id + " " + s + " " + d);
        final Simulator self = this;
        Task r = new Task() {
            public void run() {
//                System.out.println("respondNewFlight async" + id + " " + s + " " + d);
                Plane plane = new Plane(id, self.globalData.getAirportByID(s), self.globalData.getAirportByID(d));
                self.planes.add(plane);
                plane.setTrajectory(traj);
                plane.setStatus(FlightStatus.STATUS_WAITING_TAKEOFF);
                self.controller.requestTakeoff(plane.getID());
            }
            public TaskType type() { return TaskType.RESPONSE_NEWFLIGHT; }
        };
        this.transmissions.add(r);
    }

    /**
     *
     * @return
     */
    public Controller getController() {
        return controller;
    }

    /**
     *
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
