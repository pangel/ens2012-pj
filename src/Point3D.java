/**
 * 
 * x,y,z are the Cartesian coordinates in space, where z stands for the height.
 */
/**
 * 
 * x,y,z are the Cartesian coordinates in space, where z stands for the height.
 */
public class Point3D {
	double x, y, z;
    public static double distance(Point3D a, Point3D b) {
    	double i =2;
    	return (Math.sqrt((Math.pow((a.x -b.x), i)) + (Math.pow((a.y -b.y), i)) + (Math.pow((a.z -b.z), i))));
	    }
    
    Point3D(double x,double y,double z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	
    }
    
    public static Point3D moins(Point3D a, Point3D b) {
	Point3D c = new Point3D((a.x - b.x), (a.y - b.y), (a.z - b.z));
	c.x = a.x - b.x;
	c.y = a.y - b.y;
	c.z = a.z - b.z;
	return c;
	    }	
    public static Point3D plus(Point3D a, Point3D b) {
	Point3D c = new Point3D(a.x,a.x,a.x);
	c.x = a.x + b.x;
	c.y = a.y + b.y;
	c.z = a.z + b.z;
	return c; 
    	}
    public static Point3D div (Point3D a, double b)
    {Point3D c = new Point3D(a.x,a.x,a.x);
    c.x = a.x/b;
    c.y = a.y/b;
    c.z = a.z/b;
    return c;
    }

    public static Point3D fois (Point3D a, double b)
    {Point3D c = new Point3D(a.x,a.x,a.x);
    c.x = a.x*b;
    c.y = a.y*b;
    c.z = a.z*b;
    return c;
    }
}
