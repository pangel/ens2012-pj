
/** SegmentTrajectory represents a trajectory as a 
 * set of adjacent segments.
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SegmentTrajectory implements Trajectory {
	ArrayList<Point3D> data;
	Date startDate;
    
   
    
    
    public boolean terminated() {
    	return this.data.size() == 1;
    }
    
    public static void update ( Date date, Date last_update, ArrayList<Point3D> trajectory,double speed)
    { 	  
	double dt = date.getTime() - last_update.getTime();
	int i = 0;
	Point3D dernier = null;

	try {
		double distance1 = 0;
		while (distance1 < dt*speed) {
		i++;
		distance1 = distance1 + (Point3D.distance(trajectory.get(i) ,trajectory.get(i-1)));
	}
	distance1  = distance1 - (Point3D.distance(trajectory.get(i) ,trajectory.get(i-1)));
	
	for (int j = 2; j <= i; j++)
	     {trajectory.remove(0);}
		Point3D x = Point3D.div ((Point3D.moins (trajectory.get(1), trajectory.get(0))), (Point3D.distance (trajectory.get(1), trajectory.get(0))));
		Point3D y = Point3D.fois(x, (speed*(dt - (distance1/(speed)))));
		System.out.println(y.x);
	    trajectory.set(0, (Point3D.plus (trajectory.get(0),y)));
				  
    }
	 
	catch (IndexOutOfBoundsException e) {
		for (int j = 1; j <= trajectory.size(); j++)
	    	{trajectory.remove(0);}
		dernier = trajectory.get(0);
		trajectory.add(dernier);
		}
    }	
    public static void change_trajectory ( ArrayList<Point3D> trajectory, Date date, Date last_update, ArrayList<Point3D> trajectory0, double speed)
    {	
    	update(date, last_update, trajectory0, speed);
    	trajectory.set(0, trajectory.get(0));

    }
 
    
    

    SegmentTrajectory(Point3D start, Point3D dest) {
		this.data = new ArrayList<Point3D>();
		this.data.add(start);
		this.data.add(dest);
    }
    
	
    SegmentTrajectory() {
    		
    }
}
