
/** SegmentTrajectory represents a trajectory as a 
 * set of adjacent segments.
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author hp
 */
public class SegmentTrajectory implements Trajectory {
	ArrayList<Point3D> data;
	Date startDate;
    
   public void insert3 (Point3D a, Point3D b, Point3D c) {
       data.add(1,a);
       data.add(2,b);
       data.add(3,c);
   }

    public boolean terminated() {
    	return this.data.size() == 1;
    }
    

    public Point3D current() {
        if (this.data.isEmpty()) {
            return null;
        } else {
            return this.data.get(0);
        }
}
    public Point3D second () {
        return this.data.get(1);
    }

    public void update ( Date date, Date last_update, double speed)

    { 	ArrayList<Point3D> trajectory = data;
//            System.out.println(data);    
	double dt = date.getTime() - last_update.getTime();
	int i = 0;
	Point3D dernier = null;

	try {
		double distance1 = 0;
		while (distance1 < dt*speed) {
		i++;
		distance1 = distance1 + (trajectory.get(i).distance(trajectory.get(i-1)));
	}
	distance1  = distance1 - (trajectory.get(i).distance(trajectory.get(i-1)));
	
	for (int j = 2; j <= i; j++)
	     {trajectory.remove(0);}
            Point3D a = trajectory.get(0);
            Point3D b = trajectory.get(1).convertFor(a);
            
            Point3D x = Point3D.div ((Point3D.moins (b, a)), (b.distance(a)));
            Point3D y = Point3D.fois(x, (speed*(dt - (distance1/(speed)))));
            
            Point3D newA = Point3D.plus(trajectory.get(0), y);
            newA.normalize();

	    trajectory.set(0, newA);
				  
    }
	 
	catch (IndexOutOfBoundsException e) {
		for (int j = 1; j <= trajectory.size(); j++)
	    	{trajectory.remove(0);}
//		dernier = trajectory.get(0);
//		trajectory.add(dernier);
		}
    }	
//    public static void change_trajectory ( ArrayList<Point3D> trajectory, Date date, Date last_update, ArrayList<Point3D> trajectory0, double speed)
//    {	
//    	update(date, last_update, trajectory0, speed);
//    	trajectory.set(0, trajectory.get(0));
//
//    }
 
    
    

    SegmentTrajectory(Point3D start, Point3D dest) {
		this.data = new ArrayList<Point3D>();
		this.data.add(start);
		this.data.add(dest);
    }
    
	
    SegmentTrajectory() {
    		
    }

    /**
     *
     * @param plane
     * @param date
     */
    public static void update(Plane plane, Date date) {}
//    SegmentTrajectory(Point3D start, Point3D dest) {
//		this.data = new ArrayList<Point3D>();
//		this.data.add(start);
//		this.data.add(dest);
//	}
}
