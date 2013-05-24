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
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double euclideanDistance(Point3D a, Point3D b) {
    	double i =2;
    	return (Math.sqrt((Math.pow((a.x -b.x), i)) + (Math.pow((a.y -b.y), i)) + (Math.pow((a.z -b.z), i))));
	    }
    
    Point3D(double x,double y,double z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	
    }
   
     
    
    public String toString() {
        return "("+this.x+","+this.y+","+this.z+")";
    }
    
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D moins(Point3D a, Point3D b) {
	Point3D c = new Point3D((a.x - b.x), (a.y - b.y), (a.z - b.z));
	c.x = a.x - b.x;
	c.y = a.y - b.y;
	c.z = a.z - b.z;
	return c;
	    }	
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D plus(Point3D a, Point3D b) {
	Point3D c = new Point3D(a.x,a.x,a.x);
	c.x = a.x + b.x;
	c.y = a.y + b.y;
	c.z = a.z + b.z;
	return c; 
    	}
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D div (Point3D a, double b)
    {Point3D c = new Point3D(a.x,a.x,a.x);
    c.x = a.x/b;
    c.y = a.y/b;
    c.z = a.z/b;
    return c;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Point3D fois (Point3D a, double b)
    {Point3D c = new Point3D(a.x,a.x,a.x);
    c.x = a.x*b;
    c.y = a.y*b;
    c.z = a.z*b;
    return c;
    }
    
        
    void normalize() {
        if (this.x < 0) { this.x = this.x + World.mapWidth(); }
        if (this.x > World.mapWidth()) { this.x = this.x - World.mapWidth(); }
    }    
    
    Point3D convertFor(Point3D other) {
        Point3D alt0 = new Point3D(this.x,this.y,this.z);
        Point3D alt1 = new Point3D(this.x+World.mapWidth(),this.y,this.z);
        Point3D alt2 = new Point3D(this.x-World.mapWidth(),this.y,this.z);
        
        double d0 = Point3D.euclideanDistance(alt0,other);
        double d1 = Point3D.euclideanDistance(alt1, other);
        double d2 = Point3D.euclideanDistance(alt2, other);
        
        if (d0 <= d1 && d0 <= d2) {
            return alt0;
        } else if (d1 <= d0 && d1 <= d2) {
            return alt1;
        } else {
            return alt2;
        }
    }
    
    public double distance(Point3D other) {
        Point3D converted = other.convertFor(this);
        return Point3D.euclideanDistance(this,converted);
    }
}
