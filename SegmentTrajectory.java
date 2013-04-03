
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
	SegmentTrajectory(Point3D start, Point3D dest) {
		this.data = new ArrayList<Point3D>();
		this.data.add(start);
		this.data.add(dest);
	}
}
