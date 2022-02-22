package obj;

import java.io.IOException;

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
    private boolean exitOnEndEnabled;
    private boolean paintArrowsEnabled;
    private boolean darkModeEnabled;
    
    public Settings(int x, int y, boolean m) {
        sizex = x;
        sizey = y;
        experimentalMode = m;
        consoleEnabled = !m;
        diagonalMovementEnabled = m;
        possibleCheats = m;
        hudEnabled = true;
        moreNextValuesEnabled = m;
        balancedStartEnabled = m;
        exitOnEndEnabled = true;
        paintArrowsEnabled = true;
        darkModeEnabled = false;
    }
    
    public Settings(String s) throws IOException {
        String[] data = s.split(" ");
        sizex = Integer.parseInt(data[0]);
        sizey = Integer.parseInt(data[1]);
        experimentalMode = Boolean.parseBoolean(data[2]);
        consoleEnabled = Boolean.parseBoolean(data[3]);
        diagonalMovementEnabled = Boolean.parseBoolean(data[4]);
        possibleCheats = Boolean.parseBoolean(data[5]);
        hudEnabled = Boolean.parseBoolean(data[6]);
        moreNextValuesEnabled = Boolean.parseBoolean(data[7]);
        balancedStartEnabled = Boolean.parseBoolean(data[8]);
        exitOnEndEnabled = Boolean.parseBoolean(data[9]);
        paintArrowsEnabled = Boolean.parseBoolean(data[10]);
        darkModeEnabled = Boolean.parseBoolean(data[11]);
        
        if(!experimentalMode)
            if(diagonalMovementEnabled || possibleCheats || !hudEnabled ||
                    moreNextValuesEnabled || balancedStartEnabled || !paintArrowsEnabled) 
                throw new IOException("Leídos valores inválidos desde archivo de opciones.");
            
        
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
    
    public boolean isExitOnEndEnabled() {
        return exitOnEndEnabled;
    }
    
    public boolean isPaintArrowsEnabled() {
        return paintArrowsEnabled;
    }
    
    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }
    
    public void toggleHud() {hudEnabled = !hudEnabled;}
    public void togglePossibleCheats() {possibleCheats = !possibleCheats;}
    public void toggleDiagonalMovement() {diagonalMovementEnabled = !diagonalMovementEnabled;}
    public void toggleConsole() {consoleEnabled = !consoleEnabled;}
    public void toggleMoreNextValues() {moreNextValuesEnabled = !moreNextValuesEnabled;}
    public void toggleBalancedStart() {balancedStartEnabled = !balancedStartEnabled;}
    public void toggleExitOnEnd() {exitOnEndEnabled = !exitOnEndEnabled;}
    public void togglePaintArrows() {paintArrowsEnabled = !paintArrowsEnabled;}
    public void toggleDarkMode() {darkModeEnabled = !darkModeEnabled;}
    
    @Override
    public String toString() {
        return String.format("%d %d %s %s %s %s %s %s %s %s %s %s", 
                sizex, sizey, experimentalMode, consoleEnabled, 
                diagonalMovementEnabled, possibleCheats, hudEnabled, 
                moreNextValuesEnabled, balancedStartEnabled, exitOnEndEnabled,
                paintArrowsEnabled, darkModeEnabled);
    }
}
