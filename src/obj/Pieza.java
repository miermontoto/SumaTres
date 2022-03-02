package obj;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;
import util.Random;

/**
 * Clase que genera objetos tipo 'Pieza' para que sean utilizados en un tablero.
 * Cada objeto tiene dos atributos: un tipo entero que contiene el valor y un
 * objeto de clase 'Color' que contiene el color de la pieza correspondiente a
 * su valor.
 * <p>
 * Contiene un constructor que inicializa la pieza con el valor sobrecargado y
 * establece su color.
 * <p>
 * Cada vez que se cambia el valor de una pieza, se actualiza su color mediante
 * el método 'updateColor()'.
 * <p>
 * Contiene un atributo estático {@link #COLORES}, que define los colores
 * predeterminados de las piezas hasta un determinado valor. A partir de ahí,
 * se generan colores aleatoriamente y se guardan en el HashMap para que todas
 * las piezas del mismo valor tengan siempre el mismo color.
 */
public class Pieza {
    private int valor;
    private Color color;
    private boolean brillante;
    
    /**
    * HashMap que contiene un valor con un color para cada clave asignada a los
    * posibles valores de las fichas. Más información en la documentación de la
    * clase. Es estático puesto que {@link #COLORES} es igual para todas las
    * piezas.
    * <p>
    * Se popula en el constructor mediante {@link #inicializarColores()}
    */
    public final static Map<Integer, Color> COLORES = new TreeMap<>();
    public final static Map<Integer, Boolean> BRILLOS = new TreeMap<>();

    /**
     * Método que inicializa los colores para las fichas por defecto.
     * Para el resto de valores, el color se genera aleatoriamente.
     * Es estático puesto que {@link #COLORES} es igual para todas las
     * piezas.
     */
    public static void inicializarColores() {
        COLORES.put(-2, Color.black   );
        COLORES.put(-1, Color.darkGray);
        COLORES.put(0 , Color.white   );
        COLORES.put(1 , Color.red     );
        COLORES.put(2 , Color.orange  );
        COLORES.put(3 , Color.cyan    );
        COLORES.put(6 , Color.blue    );
        COLORES.put(12, Color.green   );
        COLORES.put(24, Color.magenta );
        COLORES.put(48, Color.pink    );
        
        for(Integer i : COLORES.keySet()) BRILLOS.put(i, Boolean.FALSE);
    }

    public static Map<Integer, Color> getColores() {return COLORES;}
    public static Map<Integer, Boolean> getBrillos() {return BRILLOS;}

    /**
     * Constructor que inicializa la pieza con valor 0. <p>
     * Las piezas con valor 0 no se representan.
     */
    public Pieza() {
        this.valor = 0;
    }

    /**
     * Método que devuelve el valor de la pieza.
     * 
     * @return Tipo entero con el valor.
     */
    public int getValor() {
        return valor;
    }
    
    public boolean isEmpty() {
        return valor == 0;
    }

    /**
     * Establece el valor de la pieza y actualiza el color de la pieza y si es
     * brillante o no. El valor introducido debe de ser válido para que la pieza 
     * se establezca correctamente.
     * <p>
     * Para comprobar que el valor introducido sea válido, se comprueba que sea 1,
     * 2, 3, algún valor que se corresponda a la serie 6*2n o 0(se utiliza para
     * inicializar el tablero con fichas vacías).
     * 
     * @param valor que desea asignarle a la pieza.
     */
    public void setValor(int valor) {
        if (validValue(valor)) {
            this.valor = valor;
            if (!COLORES.containsKey(valor)) generateColorForValue(valor);
            this.color = COLORES.get(valor);
            this.brillante = BRILLOS.get(valor);
        }
    }
    
    public static void generateColorForValue(int value) {
        Color c1 = Color.white; // Es necesario inicializar el color.
        boolean check = true;
        int c = 0;
        while(check && c < 25) { // solo se busca 10 veces un color nuevo.
            check = false;
            c1 = new Color(Random.newRandom(256), Random.newRandom(256), Random.newRandom(256));
            for(Color c2 : COLORES.values()) {
                double aR, aG, aB, rR, aC;
                rR = (c1.getRed() + c2.getRed()) / 2.0;
                aR = Math.pow(c1.getRed() - c2.getRed(), 2);
                aG = Math.pow(c1.getGreen() - c2.getGreen(), 2);
                aB = Math.pow(c1.getBlue() - c2.getBlue(), 2);

                // fórmula "redmean"
                aC = Math.sqrt((2 + rR / 256) * aR + 4 * aG + (2 + (255 - rR) / 256) * aB);

                if(aC < 200) check = true;
                //System.out.println(aC); // Imprime la distancia entre el color a comparar.
                c++;
            }
        }
        COLORES.put(value, c1);
        updateBrightnessForValue(value);
    }
    
    public static void updateBrightnessForValue(int value) {
        Color c1 = COLORES.get(value);
        
        // 'Y' es la luminosidad del color de la ficha.
        double Y = (0.2126*c1.getRed() + 0.7152*c1.getGreen() + 0.0722*c1.getBlue());
        BRILLOS.put(value, Y>=211);
        //System.out.println(Y); // Imprimir brillo de la pieza.
    }

    /**
     * Método que devuelve el color de la pieza actual.
     * 
     * @return Objeto tipo 'Color'
     */
    public Color getColor() {
        return color;
    }
    
    public boolean isBrillante() {
        return this.brillante;
    }
    
    public void setColor(Color val) {
        color = val;
    }
    
    public void setBrillo(boolean val) {
        brillante = val;
    }

    /**
     * Método que determina la validez de un valor dado. <p>
     * Para ello, se define si el número es 1, 2, 3 o un número perteneciente a la serie
     * 6*2n.
     * 
     * @param x Valor a examinar
     * @return Valor booleano que determina la validez
     */
    public static boolean validValue(int x) {
        boolean check = false;
        if (x<=3 && x>=-2) check = true;
        else {
            int i = 0;
            while (x > 6 * Math.pow(2, i)) i++;
            if (6 * Math.pow(2, i) == x) check = true;
        }
        return check;
    }
}