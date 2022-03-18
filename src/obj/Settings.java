package obj;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
//import javax.naming.OperationNotSupportedException;

/**
 *
 * @author Juan Mier
 */
public class Settings {
    
    private int sizex;
    private int sizey;
    private Map<String, Boolean> settingsMap;
    private int verbosityLevel;
    
    public Settings(int x, int y, boolean m) {
        sizex = x;
        sizey = y;
        settingsMap = new TreeMap<>();
        
        settingsMap.put("experimental", m);
        settingsMap.put("consoleOutput", !m);
        settingsMap.put("diagonalMovement", m);
        settingsMap.put("possibleCheats", m);
        settingsMap.put("moreNextValues", m);
        settingsMap.put("newDiffMult", m);
        settingsMap.put("balancedStart", m);
        settingsMap.put("exitOnEnd", true);
        settingsMap.put("drawArrows", true);
        settingsMap.put("drawHud", true);
        settingsMap.put("darkMode", false);
        settingsMap.put("saveResults", true);
        settingsMap.put("drawGrid", false);
        settingsMap.put("drawZones", false);
        settingsMap.put("drawCoords", false);
        settingsMap.put("newNextValues", false);
        verbosityLevel = 0;
    }
    
    public Settings(String s) throws IOException {
        String[] data = s.split("'");
        //if(data.length != 19) throw new IOException("Tamaño de información incorrecto.");
        settingsMap = new TreeMap<>();
        
        sizex = Integer.parseInt(data[0]);
        sizey = Integer.parseInt(data[1]);
        verbosityLevel = Integer.parseInt(data[2]);
        for(int i = 3; i < data.length; i++) {
            var settingName = data[i].split("=")[0];
            var settingStatus = Boolean.parseBoolean(data[i].split("=")[1]);
            settingsMap.put(settingName, settingStatus);
        }

        /*
        if(!settingsMap.get("experimental") && (diagonalMovementEnabled || possibleCheats || !hudEnabled ||
                    moreNextValuesEnabled || balancedStartEnabled || !drawArrowsEnabled ||
                        enhancedDiffMultEnabled || saveResultsToFileEnabled || drawZonesEnabled ||
                            drawGridEnabled || drawCoordsEnabled)) 
                throw new IOException("Leídos valores inválidos desde archivo de opciones.");*/
    }
    
    /**
     * Devuelve el valor de una opción que esté dentro del diccionario de opciones.
     * @param s Nombre de la opción del que se desea conocer el valor.
     * @return Valor de la opción.
     */
    public boolean getStatus(String s) {
        //if(!settingsMap.containsKey(s)) throw new OperationNotSupportedException("Opción no existente.");
        return settingsMap.get(s);
    }
    
    /**
     * Método que establece el valor de una opción.
     * @param s Opción del que se quiere cambiar el valor.
     * @param b Valor a establecer.
     * @return Devuelve el valor booleano que devuelve {@code put}.
     */
    public boolean setStatus(String s, boolean b) {
        //if(!settingsMap.containsKey(s)) throw new OperationNotSupportedException("Opción no existente.");
        return settingsMap.put(s, b);
    }
    
    /**
     * Método que conmuta el valor de una opción.
     * @param s Nombre de la opción a conmutar.
     * @return Devuelve el nuevo valor booleano de la opción.
     */
    public boolean toggleStatus(String s) {
        //if(!settingsMap.containsKey(s)) throw new OperationNotSupportedException("Opción no existente.");
        boolean newValue = !settingsMap.get(s);
        settingsMap.put(s, newValue);
        return newValue;
    }

    public int getX() {
        return sizex;
    }

    public int getY() {
        return sizey;
    }

    public void setSizex(int val) {
        if (val >= 2) sizex = val;
    }

    public void setSizey(int val) {
        if (val >= 2) sizey = val;
    }
    
    public void setVerbosity(int val) {
        if(val >= 0 && val <= 2) verbosityLevel = val;
    }

    public void setExperimentalMode(boolean exp) {
        settingsMap.put("experimental", exp);
        settingsMap.put("consoleOutput", !exp);
        settingsMap.put("diagonalMovement", exp);
        settingsMap.put("possibleCheats", exp);
        settingsMap.put("moreNextValues", exp);
        settingsMap.put("newDiffMult", exp);
        settingsMap.put("newNextValues", exp);
    }
    
    public int verbosity() {
        return verbosityLevel;
    }
    
    @Override
    public String toString() {
        String s = sizex + "'" + sizey + "'" + verbosityLevel;
        for(Map.Entry<String, Boolean> pair : settingsMap.entrySet()) s += "'" + pair.getKey() + "=" + pair.getValue();
        return s;
    }
}
