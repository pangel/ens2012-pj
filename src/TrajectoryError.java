
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
    private Date startTime;
    private Date endTime;
    private double dx;
    private double dy;
    
    public Date getStartTime () {
    return startTime;    
}
    public Date getEndTime () {
    return endTime;    
}
    public double getdx () {
    return dx;    
}   public double getdy () {
    return dy;    
}
    public void setStartTime (Date d) {
        startTime = d;
    }
     public void setEndTime (Date d) {
        endTime = d;
    }
      public void setdx (double d) {
        dx = d;
    }
       public void setdy (double d) {
        dy = d;
    }
  
    
}
