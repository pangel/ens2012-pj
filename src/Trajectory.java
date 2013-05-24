
import java.util.Date;


/**
 *
 * @author hp
 */
public interface Trajectory {
    /**
     *
     * @param date
     * @param last_update
     * @param speed
     */
    public void update ( Date date, Date last_update, double speed);
    /**
     *
     * @return
     */
    public Point3D current();
    /**
     *
     * @return
     */
    public boolean terminated();
    public Point3D second ();
    public void insert3 (Point3D a, Point3D b, Point3D c);
    public void modify1 (Point3D a);
}
