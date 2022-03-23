package util;

/**
 *
 * @author Juan Mier
 */
public class Timer {
    
    private int currentLevel;
    private long startTime;
    
    public Timer(int lvl) {
        if(lvl < 0 || lvl > 2) throw new UnsupportedOperationException();
        startTime = System.currentTimeMillis();
        currentLevel = lvl;
    }
    
    public String getTime() {
        return Timer.getTime(startTime);
    }
    
    public static String getTime(long start) {
        return String.valueOf((System.currentTimeMillis() - start) / 1000.0);
    }
    
    public void log(int i, String s) {
        if(i <= currentLevel) System.out.printf("[%s] %s%n", getTime(), s);
    }
   
}
