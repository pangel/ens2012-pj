
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mbeunard
 */
public class TrajectoryError {
    private double startTime;
    private double endTime;
    private double dx;
    private double dy;
    
    public double getStartTime () {
    return startTime;    
}
    public double getEndTime () {
    return endTime;    
}
    public double getdx () {
    return dx;    
}   public double getdy () {
    return dy;    
}
    public void setStartTime (double d) {
        startTime = d;
    }
     public void setEndTime (double d) {
        endTime = d;
    }
      public void setdx (double d) {
        dx = d;
    }
       public void setdy (double d) {
        dy = d;
    }
  
    
}
