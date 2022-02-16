package obj;

/**
 *
 * @author JuanMier
 */
public class Settings {
    
    private int sizex;
    private int sizey;
    private boolean experimentalMode;
    
    public Settings(int x, int y, boolean m) {
        sizex = x;
        sizey = y;
        experimentalMode = m;
    }

    public int getX() {
        return sizex;
    }

    public int getY() {
        return sizey;
    }

    public boolean isExperimental() {
        return experimentalMode;
    }  
}
