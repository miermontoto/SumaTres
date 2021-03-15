package game;
import javax.swing.JFrame; // Necesario para crear la ventana gráfica en sí.
import javax.swing.WindowConstants; // Necesario para obtener la acción de cerrar la ventana.

import util.Graphic;
import util.Input;
import util.Dialog;

/**
 * El main se encarga de introducir y comprobar las
 * dimensiones del tablero, el tipo de jugada y la pantalla en sí.
 */
public class Launcher {
	
	private static final int min = 3;
	private static final int max = 25;

	public static void main(String[] args) {

		int sizex = Input.input("Introduzca la cantidad de filas:", min, max);
		if(sizex == -1) System.exit(0);
		int sizey = Input.input("Introduzca la cantidad de columnas:", min, max);
		if(sizey == -1) System.exit(0);


		/**
		 * Utilizando {@link #Graphic.validSize()}, se comprueba que el tablero
		 * contenga dimensiones que se puedan pintar en pantalla sin salirse de la
		 * misma. De lo contrario, se ejecuta un mensaje de error y se le vuelve a
		 * preguntar al usuario por unas dimensiones válidas. Hasta que el usuario no
		 * introduzca unas dimensiones que entren en la resolución actual, el programa
		 * no continúa.
		 */
		while (!Graphic.validSize(sizex, sizey)) {
			Dialog.showError("Dimensiones inválidas de tablero.");
			sizex = Input.input("Introduzca la cantidad de filas:", min, max);
			if(sizex == -1) System.exit(0);
			sizey = Input.input("Introduzca la cantidad de columnas:", min, max);
			if(sizey == -1) System.exit(0);
		}

		String[] modos = new String[] { "Clásico", "Experimental", "Cancelar" };
		int type = Dialog.choices("¿Qué modo desea jugar?", modos);
		SumaTres Juego = new SumaTres(sizex, sizey, type);

		JFrame app = new JFrame(Dialog.title);

		// Todos estos comandos son, en esencia, los incluídos en el enunciado del trabajo.
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Eclipse prefiere WindowConstants a JFrame para la salida porque este último está desfasado.
		app.setBounds(0, 0, Graphic.defineX(Juego) + 15, Graphic.defineY(Juego) + 39);
		// Por algún extraño motivo, setBounds() establece una ventana 15 píxeles más pequeña
		// horizontalmente y 39 píxeles más pequeña verticalmente de lo que le devuelven
		// defineX() y defineY() respectivamente.
		app.add(Juego);
		app.setVisible(true);
		app.setResizable(false);
		app.setFocusable(true);
		app.setIconImage(Graphic.ICON.getImage());
	}
}
