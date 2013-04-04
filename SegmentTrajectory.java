
/** SegmentTrajectory represents a trajectory as a 
 * set of adjacent segments.
 *
 */
import java.util.Date;
import java.util.List;

public class SegmentTrajectory implements Trajectory {
	List<Point3D> data;
	Date startDate;
    public static void update (Plane plane, Date date)
    { 
	Date last_update =plane.lastUpdate;
    Trajectory trajectory = plane.trajectory;
	double dt = date.getTime() - last_update.getTime();
	int i = 1;
	double distance = 0;
	try {
		while (Point3D.distance(trajectory.data.get(0), trajectory.data.get(i)) < dt*plane.getSpeed()) {
		i++;
		distance = distance + (Point3D.distance(trajectory.data.get(i) ,trajectory.data.get(i-1)));
	}
	
	
	for (int j = 0; j <= i; i++)
	    {trajectory.data.remove(0);}
		Point3D x = Point3D.div ((Point3D.moins (trajectory.data.get(1), trajectory.data.get(0))), (Point3D.distance (trajectory.data.get(1), trajectory.data.get(0))));
		Point3D y = Point3D.fois(x, (plane.getSpeed()*(dt - (distance/(plane.getSpeed())))));
	    trajectory.data.set(0, (Point3D.plus (trajectory.data.get(0),y)));
				  
    }
	 
	catch (IndexOutOfBoundsException) {
		for (int j = 0; j < i; i++)
			{trajectory.data.remove(0);}
		}
    public static void change_trajectory ( Trajectory trajectory,Plane plane, Date date)
    {

    }
 
    }
    

    SegmentTrajectory(Point3D start, Point3D dest) {
		this.data = new ArrayList<Point3D>();
		this.data.add(start);
		this.data.add(dest);
    }
    SegmentTrajectory(Point3D start, Point3D dest) {
		this.data = new ArrayList<Point3D>();
		this.data.add(start);
		this.data.add(dest);
	}
    SegmentTrajectory() {
    		
    }
}
