/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mbeunard
 */
public class Statistics {
    private static int nb_crash_fuel = 0;
    private static int nb_crash_collision = 0;
    private static int nb_crash_speed = 0;
    private static int nb_landing = 0;    
    private static double sum_time = 0;
    private static double average_time = 0;
    public static void incrnb_crash_fuel () {
        nb_crash_fuel = nb_crash_fuel + 1;
    }
    public static void incrnb_crash_speed () {
        nb_crash_speed = nb_crash_speed + 1;
    }
    public static void incrnb_crash_collision () {
        nb_crash_collision = nb_crash_collision + 1;
    }
    public static void incrnb_landing () {
        nb_landing = nb_landing + 1;
    }
    public static void addsum_time (double dt) {
        sum_time = sum_time + dt;
    }
    public static int getNbCrashFuel (){
    return nb_crash_fuel;
}
    public static int getNbCrashollision (){
    return nb_crash_collision;
}
    public static int getNbCrashSpeed (){
    return nb_crash_speed;
}
    public static int getNbLanding (){
    return nb_landing;
}
    public static double getAverageTime (){
    return sum_time/nb_landing;
        }
    
    
    
}
