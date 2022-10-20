package obj;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que almacena todas las opciones referentes a una partida.
 * <p> Se utiliza una estructura de datos TreeMap<String, Boolean> para almacenar las opciones.
 * @author Juan Mier
 */
public class Settings {

    private int sizex;
    private int sizey;
    private Map<String, Boolean> settingsMap;
    private int verbosityLevel;

    private static final int DEFAULT_VERBOSITY_LEVEL = 0;

    public Settings(int x, int y, boolean experimental) {
        sizex = x;
        sizey = y;
        settingsMap = new TreeMap<>();

        settingsMap.put("experimental", experimental);
        settingsMap.put("consoleOutput", !experimental);
        settingsMap.put("diagonalMovement", experimental);
        settingsMap.put("possibleCheats", experimental);
        settingsMap.put("moreNextValues", experimental);
        settingsMap.put("newDiffMult", experimental);
        settingsMap.put("balancedStart", experimental);
        settingsMap.put("exitOnEnd", true);
        settingsMap.put("drawArrows", true);
        settingsMap.put("drawHud", true);
        settingsMap.put("darkMode", false);
        settingsMap.put("saveResults", true);
        settingsMap.put("drawGrid", false);
        settingsMap.put("drawZones", false);
        settingsMap.put("drawCoords", false);
        settingsMap.put("newNextValues", false);
        verbosityLevel = DEFAULT_VERBOSITY_LEVEL;
    }

    public Settings(String code) throws IOException {
        this(5, 5, true);
        String[] data = code.split("'");

        sizex = Integer.parseInt(data[0]);
        sizey = Integer.parseInt(data[1]);
        verbosityLevel = Integer.parseInt(data[2]);
        for(int i = 3; i < data.length; i++) {
            var settingName = data[i].split("=")[0];
            var settingStatus = Boolean.parseBoolean(data[i].split("=")[1]);
            settingsMap.put(settingName, settingStatus);
        }
    }

    /**
     * Devuelve el valor de una opción que esté dentro del diccionario de opciones.
     * @param settingName Nombre de la opción del que se desea conocer el valor.
     * @return Valor de la opción.
     */
    public boolean getStatus(String settingName) {
        //if(!settingsMap.containsKey(s)) throw new OperationNotSupportedException("Opción no existente.");
        return settingsMap.get(settingName);
    }

    /**
     * Método que establece el valor de una opción.
     * @param settingName Opción del que se quiere cambiar el valor.
     * @param bool Valor a establecer.
     * @return Devuelve el valor booleano que devuelve {@code put} o {@code false} si la opción no existe.
     */
    public boolean setStatus(String settingName, boolean bool) {
        if(!settingsMap.containsKey(settingName)) {
            System.err.println("Opción no existente: " + settingName);
            return false;
        }
        return settingsMap.put(settingName, bool);
    }

    /**
     * Método que conmuta el valor de una opción.
     * @param settingName Nombre de la opción a conmutar.
     * @return Devuelve el nuevo valor booleano de la opción.
     */
    public boolean toggleStatus(String settingName) {
        //if(!settingsMap.containsKey(s)) throw new OperationNotSupportedException("Opción no existente.");
        boolean newValue = !settingsMap.get(settingName);
        settingsMap.put(settingName, newValue);
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
        for(Map.Entry<String, Boolean> pair : settingsMap.entrySet()) {
            s += "'" + pair.getKey() + "=" + pair.getValue();
        }
        return s;
    }
}
