import static java.lang.System.out; //se importa de esta manera para acortar durante el resto del programa
import java.awt.Color; // Es necesario importar esto para cambiar el color del fondo de la aplicación.
import java.io.IOException; // Necesario porque el constructor de SumaTres devuelve IOException.
import javax.swing.JFrame; // Necesario para crear la ventana gráfica en sí.
import javax.swing.JOptionPane; // Necesario para preguntarle al usuario de manera gráfica en vez de por consola.

/**
 * SumaTres es un simple juego que trata de conseguir la mayor puntuación posible
 * sumando piezas. El valor de las fichas siguen un patrón, comenzando por el 1, 
 * el 2, y el 3. A partir de aquí, las fichas consecutivas se multiplican por dos.
 * es decir: 6, 12, 24, etc. Solo las fichas que sean iguales (excepto el 1 y el 2,
 * que solo se pueden sumar entre sí) se pueden sumar. La partida termina cuando el
 * tablero está lleno y el jugador no tiene la posibilidad de sumar ninguna ficha.
 * 
 * <p>
 * 
 * Aunque las reglas básicas son estas, existen dos modos ligeramente diferentes con
 * los que jugar a SumaTres: <ul>
 * 
 * <li> El modo clásico sigue con todas las reglas establecidas en el enunciado del
 * 		trabajo original. Existen cuatro sentido de movimientos para las jugadas, la
 * 		consola está activada por defecto, se generan tres fichas por defecto al principio
 * 		de la partida (1, 2 y 3) y solo se pueden generar esos tres valores como fichas
 * 		aleatorias. Por lo general, el modo clásico mantiene el estado del programa al
 * 		ser entregado para corrección. </li>
 * 
 * <li> El modo experimental incluye variaciones con respecto al sistema clásico del
 * 		enunciado. Al comenzar la partida, se generan en pantalla una cantidad de piezas
 * 		dependente al tamaño del tablero en sí. Además, las siguientes fichas generadas
 * 		dependen de la ficha máxima en pantalla, con lo que el juego se facilita con lo
 * 		que la partida sigue. En este modo, la consola está deshabilitada por defecto,
 * 		con lo que solo se puede observar la partida mediante la ventana gráfica. El
 * 		cambio más importante con respecto al modo clásico es la existencia de ocho
 * 		sentidos del movimiento frente a cuatro, añadiendo la posibilidad de mover las
 * 		fichas diagonalmente. Los resultados de las partidas en modo experimental se
 * 		guardan en el archivo 'resultados.txt' en el directorio actual. <p> El modo
 * 		experimental es eso, experimental, por lo que puede haber cambios más adelante y,
 * 		más importante, puede que haya funciones que no estén implementadas correctamente.
 * 		</li>
 * 
 * </ul> <p>
 * 
 * Con respecto al main, el programa se encarga de introducir y comprobar las dimensiones
 * del tablero, el tipo de jugada y la pantalla en sí.
 *
 */
public class SumaTresTexto {

	/**
	 * Método ideado para la entrada de valores del tablero. Se realiza
	 * mediante ventanas emergentes con JOptionPane. Si se cancela o se envía una
	 * cadena vacía, se termina el programa. En caso de detectar una cadena inválida,
	 * establece el valor por defecto '5'.
	 * 
	 * @param s Una cadena que se imprime al usuario para preguntarle por la
	 *          información.
	 * @return Devuelve un entero, ideado para que dicho entero sea el tamaño de una
	 *         dimensión del tablero.
	 */
	public static int inputSize(String s) {
		int value;
		try {
			String respuesta = JOptionPane.showInputDialog(null, s, "SumaTres", JOptionPane.QUESTION_MESSAGE);
			if(respuesta == null || respuesta.length() == 0) System.exit(0); // Si se cancela, simplemente se cierra el programa.
			value = Integer.parseInt(respuesta);
			
			while (value < 4) {
				JOptionPane.showMessageDialog(null, "Debe de ser mayor o igual a 4.", "SumaTres", JOptionPane.ERROR_MESSAGE);
				respuesta = JOptionPane.showInputDialog(null, s, "SumaTres", JOptionPane.QUESTION_MESSAGE);
				if(respuesta == null || respuesta.length() == 0) System.exit(0);
				value = Integer.parseInt(respuesta);
			}
		} catch (Exception ex) {
			out.println("Valor inválido. Establecido valor por defecto '5'.");
			value = 5;
		}
		return value;
	}

	public static void main(String[] args) throws IOException {

		int sizex = inputSize("Introduzca la cantidad de filas:");
		int sizey = inputSize("Introduzca la cantidad de columnas:");

		SumaTres Juego = new SumaTres(sizex, sizey, 0);
		// Se inicializa el tablero para comprobar que los valores introducidos sean válidos.
		
		
		/**
		 * Utilizando {@link #SumaTres.checkValidSize()}, se comprueba que el tablero
		 * contenga dimensiones que se puedan pintar en pantalla sin salirse de la
		 * misma. De lo contrario, se ejecuta un mensaje de error y se le vuelve a
		 * preguntar al usuario por unas dimensiones válidas. Hasta que el usuario no
		 * introduzca unas dimensiones que entren en la resolución actual, el programa
		 * no continúa.
		 */
		while(!(Juego.checkValidSize())) {
			JOptionPane.showMessageDialog(null, "Dimensiones inválidas de tablero.");
			sizex = inputSize("Introduzca la cantidad de filas:");
			sizey = inputSize("Introduzca la cantidad de columnas:");
			Juego = new SumaTres(sizex, sizey, 0);
		}
		
	    int type = JOptionPane.showOptionDialog(null, "¿Qué modo desea jugar?",
	    		"SumaTres", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
	    		null, new String[] {"Clásico", "Experimental", "Cancelar"}, "Clásico");
	    
	    Juego = new SumaTres(sizex, sizey, type);
		
		JFrame app = new JFrame("SumaTres");

		// Todos estos comandos son, en esencia, los incluídos en el enunciado del trabajo.
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setBounds(0, 0, Juego.defineX() + 15, Juego.defineY() + 39);
		// Por algún extraño motivo, setBounds() establece una ventana 15 píxeles más pequeña
		// horizontalmente y 39 píxeles más pequeña verticalmente de lo que le devuelven
		// defineX() y defineY() respectivamente.
		app.add(Juego);
		app.setVisible(true);
		app.setResizable(false);
		app.setFocusable(true);
		app.getContentPane().setBackground(Color.white); // Esto no funciona???
		app.setIconImage(SumaTres.icono.getImage());

		if(Juego.consoleStatus()) {
			out.print(Juego);
			out.print(Juego.printExtraInfo());
		}
	}
}
