package obj;

import java.awt.Color;
import java.util.HashMap;

import game.SumaTres;

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
 * Contiene un atributo estático {@link #colores}, que define los colores
 * predeterminados de las piezas hasta un determinado valor. A partir de ahí,
 * se generan colores aleatoriamente y se guardan en el HashMap para que todas
 * las piezas del mismo valor tengan siempre el mismo color.
 */
public class Pieza {
	private int valor;
	private Color color;
	/**
	* HashMap que contiene un valor con un color para cada clave asignada a los
	* posibles valores de las fichas. Más información en la documentación de la
	* clase. Es estático puesto que {@link #colores} es igual para todas las
	* piezas.
	* <p>
	* Se popula en el constructor mediante {@link #inicializarColores()}
	*/
	private static HashMap<Integer, Color> colores = new HashMap<>();
	
	
	
	
	/**
	 * Método que inicializa los colores para las fichas por defecto.
	 * Para el resto de valores, el color se genera aleatoriamente.
	 * Es estático puesto que {@link #colores} es igual para todas las
	 * piezas.
	 */
	public static void inicializarColores() {
		colores.put(-2, Color.black   );
		colores.put(-1, Color.darkGray);
		colores.put(0 , Color.white   );
		colores.put(1 , Color.red     );
		colores.put(2 , Color.orange  );
		colores.put(3 , Color.cyan    );
		colores.put(6 , Color.blue    );
		colores.put(12, Color.green   );
		colores.put(24, Color.magenta );
		colores.put(48, Color.pink    );
	}
	
	public static HashMap<Integer, Color> getColores() {return colores;}

	/**
	 * Constructor que inicializa la pieza con valor 0. <p>
	 * Las piezas con valor 0 no se representan.
	 */
	public Pieza() {
		this.setValor(0);
	}

	/**
	 * Método que devuelve el valor de la pieza.
	 * 
	 * @return Tipo entero con el valor.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Establece el valor de la pieza y llama a {@link #updateColor()} para que
	 * actualice el color de la pieza en base a este nuevo color. El valor
	 * sobrecargado debe de ser válido para que la pieza se establezca
	 * correctamente.
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
			updateColor();
		}
	}

	/**
	 * Método que devuelve el color de la pieza actual.
	 * 
	 * @return Objeto tipo 'Color'
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Método que actualiza el color de la pieza en base a su valor.
	 * <p>
	 * Se utiliza automáticamente en {@link #setValor(int)} para que cada vez que se
	 * cambie el valor de la pieza, también se cambie su color. De esta forma, no es
	 * necesario actualizar manualmente el color de la pieza.
	 * <p>
	 * Si no existe un color predeterminado para el valor de la pieza, se genera y
	 * se guarda un color en el HashMap para que todas las futuras piezas con el
	 * mismo valor tengan el mismo color.
	 * <p>
	 * Para más información sobre la distancia entre colores, leer la documentación
	 * de la clase principal.
	 */
	public void updateColor() {
		if (!colores.containsKey(this.getValor())) {
			Color c1 = Color.white; // Es necesario inicializar el color.
			boolean check = true;
			while(check) {
				check = false;
				c1 = new Color(SumaTres.newRandom(256), SumaTres.newRandom(256), SumaTres.newRandom(256));
				for(Color c2 : colores.values()) {
					double aR, aG, aB, rR, aC;
					rR = (c1.getRed() + c2.getRed()) / 2.0;
					aR = Math.pow(c1.getRed() - c2.getRed(), 2);
					aG = Math.pow(c1.getGreen() - c2.getGreen(), 2);
					aB = Math.pow(c1.getBlue() - c2.getBlue(), 2);
					
					aC = Math.sqrt((2 + rR / 256) * aR + 4 * aG + (2 + (255 - rR) / 256) * aB);
					// fórmula "redmean"
					if(aC < 200) check = true;
					//System.out.printf("%f%n", aC); // Imprime la distancia entre el color a comparar.
				}
			}
			colores.put(getValor(), c1);
			
		}
		this.color = colores.get(getValor());
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
		if (x<=3 && x>=0)  check = true;
		else {
			int i = 0;
			while (x > 6 * Math.pow(2, i)) i++;
			if (6 * Math.pow(2, i) == x) check = true;
		}
		return check;
	}
}