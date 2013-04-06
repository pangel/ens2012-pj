
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
}
