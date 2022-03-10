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
    private boolean drawArrowsEnabled;
    private boolean darkModeEnabled;
    private boolean enhancedDiffMultEnabled;
    private boolean saveResultsToFileEnabled;
    private boolean drawZonesEnabled;
    private boolean drawGridEnabled;
    private boolean drawCoordsEnabled;
    private int verbosityLevel;
    
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
        drawArrowsEnabled = true;
        darkModeEnabled = false;
        enhancedDiffMultEnabled = m;
        saveResultsToFileEnabled = m;
        drawGridEnabled = false;
        drawZonesEnabled = false;
        drawCoordsEnabled = false;
        verbosityLevel = 0;
    }
    
    public Settings(String s) throws IOException {
        String[] data = s.split(" ");
        if(data.length != 18) throw new IOException("Tamaño de información incorrecto.");
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
        drawArrowsEnabled = Boolean.parseBoolean(data[10]);
        darkModeEnabled = Boolean.parseBoolean(data[11]);
        enhancedDiffMultEnabled = Boolean.parseBoolean(data[12]);
        saveResultsToFileEnabled = Boolean.parseBoolean(data[13]);
        drawZonesEnabled = Boolean.parseBoolean(data[14]);
        drawGridEnabled = Boolean.parseBoolean(data[15]);
        drawCoordsEnabled = Boolean.parseBoolean(data[16]);
        verbosityLevel = Integer.parseInt(data[17]);

        
        if(!experimentalMode && (diagonalMovementEnabled || possibleCheats || !hudEnabled ||
                    moreNextValuesEnabled || balancedStartEnabled || !drawArrowsEnabled ||
                        enhancedDiffMultEnabled || saveResultsToFileEnabled || drawZonesEnabled ||
                            drawGridEnabled || drawCoordsEnabled)) 
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

    public void setExperimentalMode(boolean exp) {
        this.experimentalMode = exp;
        this.consoleEnabled = exp;
        this.diagonalMovementEnabled = exp;
        this.possibleCheats = exp;
        this.moreNextValuesEnabled = exp;
        this.enhancedDiffMultEnabled = exp;
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
        return drawArrowsEnabled;
    }
    
    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }
    
    public boolean isEnhancedDiffMultEnabled() {
        return enhancedDiffMultEnabled;
    }
    
    public boolean isSaveResultsToFileEnabled() {
        return saveResultsToFileEnabled;
    }
    
    public boolean isDrawZonesEnabled() {
        return drawZonesEnabled;
    }
    
    public boolean isDrawGridEnabled() {
        return drawGridEnabled;
    }
    
    public boolean isDrawCoordsEnabled() {
        return drawCoordsEnabled;
    }
    
    public int verbosity() {
        return verbosityLevel;
    }
    
    public void toggleHud() {hudEnabled = !hudEnabled;}
    public void togglePossibleCheats() {possibleCheats = !possibleCheats;}
    public void toggleDiagonalMovement() {diagonalMovementEnabled = !diagonalMovementEnabled;}
    public void toggleConsole() {consoleEnabled = !consoleEnabled;}
    public void toggleMoreNextValues() {moreNextValuesEnabled = !moreNextValuesEnabled;}
    public void toggleBalancedStart() {balancedStartEnabled = !balancedStartEnabled;}
    public void toggleExitOnEnd() {exitOnEndEnabled = !exitOnEndEnabled;}
    public void togglePaintArrows() {drawArrowsEnabled = !drawArrowsEnabled;}
    public void toggleDarkMode() {darkModeEnabled = !darkModeEnabled;}
    public void toggleEnhancedDiffMult() {enhancedDiffMultEnabled = !enhancedDiffMultEnabled;}
    public void toggleSaveResultsToFile() {saveResultsToFileEnabled = !saveResultsToFileEnabled;}
    public void toggleDrawGrid() {drawGridEnabled = !drawGridEnabled;}
    public void toggleDrawZones() {drawZonesEnabled = !drawZonesEnabled;}
    public void toggleDrawCoords() {drawCoordsEnabled = !drawCoordsEnabled;}
    public void setVerbosityLevel(int l) {verbosityLevel = l;}
    
    @Override
    public String toString() {
        return String.format("%d %d %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %d", 
                sizex, sizey, experimentalMode, consoleEnabled, 
                diagonalMovementEnabled, possibleCheats, hudEnabled, 
                moreNextValuesEnabled, balancedStartEnabled, exitOnEndEnabled,
                drawArrowsEnabled, darkModeEnabled, enhancedDiffMultEnabled,
                saveResultsToFileEnabled, drawZonesEnabled, drawGridEnabled,
                drawCoordsEnabled, verbosityLevel);
    }
}
