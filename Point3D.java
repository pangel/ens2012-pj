/**
 * 
 * x,y,z are the Cartesian coordinates in space, where z stands for the height.
 */
public class Point3D {
	double x, y, z;
    public double distance(Point3D a, Point3D b) {
	return (Math.sqrt ((Math.pow (a.x -b.x) 2) + (Math.pow (a.y -b.y) 2) + (Math.pow (a.z -b.z) 2)))
	    }
    public Point3D moins(Point3D a, Point3D b) {
	Point3D c = new Point3D;
	c.x = a.x - b.x;
	c.y = a.y - b.y;
	c.z = a.z - b.z;
	return c
	    }
    public Point3D plus(Point3D a, Point3D b) {
	Point3D c = new Point3D;
	c.x = a.x + b.x;
	c.y = a.y + b.y;
	c.z = a.z + b.z;
	return c 