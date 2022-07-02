package util;

/**
 * Clase que permite el log de acciones del usuario incluyendo una estampa de tiempo
 * cuando se ha activado la verbosidad (lvl >= 1). <p>
 * Al utilizar el constructor el nivel de verbosidad, no es necesario almacenarlo
 * para comparar en cada parte del código que quiera imprimir logs por consola. <p>
 * Debido a la ineficiencia de Java a la hora de leer estampas y de imprmir información
 * por consola, activar la verbosidad (sobre todo el segundo nivel) puede afectar al
 * rendimiento y por lo tanto a la experiencia de juego.
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
    
    private String getTime() {
        return Timer.getTime(startTime);
    }
    
    public static String getTime(long start) {
        return String.valueOf((System.currentTimeMillis() - start) / 1000.0);
    }
    
    public void log(int i, String s) {
        if(i <= currentLevel) System.out.printf("[%s] %s%n", getTime(), s);
    }
   
}
