
/** SegmentTrajectory represents a trajectory as a 
 * set of adjacent segments.
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SegmentTrajectory implements Trajectory {
	List<Point3D> data;
	Date startDate;
    public void update (Plane plane, Date date, Date last_update)
    { 
	trajectory trajectory = plane.trajectory;
	double dt = date - last_update;
	int i = 1;
	double distance = 0;
	while (Point3D.distance (trajectory.data.get.(0) trajectory.data.get(i)) < dt*plane.speed) {
	    i++;
	    distance = distance + (Point3D.distance (trajectory.data.get.(i) ,trajectory.data.get.(i-1)));
				   }
	for (int j = 0; j <= i; i++)
	    {trajectory.date.remove(0)}
	    trajectory.data.set(0, (Point3D.plus ((trajectory.data.get(0), (Point3D.moins (trajectory.data.get(1) - trajectory.data.get(0)))/(Point3D.distance (trajectory.data.get(1) - trajectory.data.get(0)))*plane.speed*(dt - (ditance/(plane.speed)))))));
				  
    }
    public void change_trajectory ( Trajectory trajectory,plane plane, Date date, Date last_update)
    {
	SegmentTrajectory.update (Plane plane, Date date, Date last_update);
	plane.trajectory = trajectory;
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
}
