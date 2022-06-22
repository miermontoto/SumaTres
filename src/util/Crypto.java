package util;

import java.util.Base64;
import org.uncommons.maths.random.XORShiftRNG;

/**
 * Clase que se encarga de generar números aleatorios y encriptar/desencriptar en Base64.
 * No se puede inicializar.
 * 
 * <p> Utiliza un randomizador importado de la librería uncommons-math optimizado
 * para rendimiento máximo. Es mucho más rápido que <code>SecureRandom</code>, el
 * randomizador que se usaba previamente.
 * 
 * @author Juan Mier
 */
public class Crypto {
    
    private static final java.util.Random RAND = new XORShiftRNG();
    
    /**
     * Constrctor generado para cumplir con SonarLint:S1118.
     * 
     * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
     * 		Regla SonarLint:S1118 </a>
     */
    private Crypto() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Genera un número nuevo aleatorio.
     * 
     * @param val un entero cualquiera.
     * @return Un entero aleatorio [0, val)
     */
    public static int newRandom(int val) {
        return RAND.nextInt(val);
    }
    
    /**
     * Método que codifica una cadena a Base64.
     * @param s Cadena a codificar.
     * @return Cadena codificada.
     */
    public static String encode(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
    
    /**
     * Método que descodifica una cadena en Base64.
     * @param s Cadena a descodificar.
     * @return Cadena descodificada.
     */
    public static String decode(String s) {
        return new String(Base64.getDecoder().decode(s));
    }
}
