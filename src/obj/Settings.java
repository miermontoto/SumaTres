package obj;

/**
 *
 * @author JuanMier
 */
public class Settings {
    
    private int sizex;
    private int sizey;
    private boolean experimentalMode;
    private boolean consoleEnabled;
    private boolean diagonalMovementEnabled;
    private boolean possibleCheats;
    private boolean hudEnabled;
    private boolean moreNextValuesEnabled;
    private boolean balancedStartEnabled;
    
    public Settings(int x, int y, boolean m) {
        sizex = x;
        sizey = y;
        experimentalMode = m;
        consoleEnabled = !m;
        diagonalMovementEnabled = m;
        possibleCheats = m;
        hudEnabled = true;
        moreNextValuesEnabled = m;
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

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }

    public void setExperimentalMode(boolean experimentalMode) {
        this.experimentalMode = experimentalMode;
        this.consoleEnabled = experimentalMode;
        this.diagonalMovementEnabled = experimentalMode;
        this.possibleCheats = experimentalMode;
        this.moreNextValuesEnabled = experimentalMode;
    }

    public boolean isConsoleEnabled() {
        return consoleEnabled;
    }

    public boolean isDiagonalMovementEnabled() {
        return diagonalMovementEnabled;
    }

    public boolean isPossibleCheats() {
        return possibleCheats;
    }

    public boolean isHudEnabled() {
        return hudEnabled;
    }
    
    public boolean isMoreNextValuesEnabled() {
        return moreNextValuesEnabled;
    }
    
    public boolean isBalancedStartEnabled() {
        return balancedStartEnabled;
    }
    
    public void toggleHud() {hudEnabled = !hudEnabled;}
    public void togglePossibleCheats() {possibleCheats = !possibleCheats;}
    public void toggleDiagonalMovement() {diagonalMovementEnabled = !diagonalMovementEnabled;}
    public void toggleConsole() {consoleEnabled = !consoleEnabled;}
    public void toggleMoreNextValues() {moreNextValuesEnabled = !moreNextValuesEnabled;}
    public void toggleBalancedStart() {balancedStartEnabled = !balancedStartEnabled;}
    
}
