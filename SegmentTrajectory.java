
/** SegmentTrajectory represents a trajectory as a 
 * set of adjacent segments.
 *
 */
import java.util.Date;
import java.util.List;

public class SegmentTrajectory implements Trajectory {
	List<Point3D> data;
	Date startDate;
}
