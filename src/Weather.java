/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
class Weather {
    public Point3D p1;
    public Point3D p2;
    public final double speedRatio;
    private final double startTime;
    private final double endTime;
    private final double dx;
    private final double dy;
    private boolean active;
    
    Weather(double speedRatio, double x1, double y1, double x2, double y2, double startTime, double endTime, double dx, double dy) {
        this.speedRatio = speedRatio;
        this.p1 = new Point3D(x1,y1,0);
        this.p2 = new Point3D(x2,y2,0);
        this.startTime = startTime;
        this.endTime = endTime;
        this.dx = dx;
        this.dy = dy;
        this.active = false;
    }
    
    public void update(double lapse) {
//        System.out.println("Update after " +time);
        double sx = dx * lapse;
        double sy = dy * lapse;
//                 System.out.println("dx: " + dx + " sx: " + sx + " lapse: " + lapse  + " p1.x:"+ p1.x);

        p1.x += sx;
        p1.y += sy;
        p2.x += sx;
        p2.y += sy;
        p1.normalize();
        p2.normalize();
    }
    
    public void setActive(double time) {
        active = (this.startTime <= time && time <= this.endTime);
    }
    
    public boolean getActive() {
        return active;
    }
    
    public boolean contains(Point3D pos) {
        
        boolean verticalInside = p1.y <= pos.y && pos.y <= p2.y;
        
        boolean horizontalInside;
        if (p1.x <= p2.x) {
            horizontalInside = p1.x <= pos.x && pos.x <= p2.x;
        } else {
            horizontalInside = p1.x <= pos.x || pos.x <= p2.x;
        }
        
        return verticalInside && horizontalInside;
    }
    
}
