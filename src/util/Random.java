package util;

import org.uncommons.maths.random.XORShiftRNG;

/**
 * Clase que se encarga de generar números aleatorios.
 * No se puede inicializar, solo acepta llamadas estáticas al método newRandom(int).
 * 
 * <p> Utiliza un randomizador importado de la librería uncommons-math optimizado
 * para rendimiento máximo. Es mucho más rápido que <code>SecureRandom</code>, el
 * randomizador que se usaba previamente.
 * 
 * @author under
 */
public class Random {
    
    private static final java.util.Random RAND = new XORShiftRNG();
    
    /**
     * Constrctor generado para cumplir con SonarLint:S1118.
     * 
     * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
     * 		Regla SonarLint:S1118 </a>
     */
    private Random() {
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
}
