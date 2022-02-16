package game;
import javax.swing.JFrame; // Necesario para crear la ventana gráfica en sí.
import javax.swing.WindowConstants; // Necesario para obtener la acción de cerrar la ventana.

import util.Graphic; // Se utiliza para definir dimensiones, escala, etc.
import util.Input; // Se utiliza para preguntarle al usuario por el tamaño del tablero.
import util.Dialog; // Se utiliza para preguntarle al usuario otra información.
import obj.Settings;

/**
 * El main se encarga de introducir y comprobar las
 * dimensiones del tablero, el tipo de jugada y la pantalla en sí.
 */
public class Launcher {
	
	private static final int min = 3; // Dimensión mínima del tablero.
	private static final int max = 33; // Dimensión máxima del tablero.

	public static void main(String[] args) {

		int sizex = Input.input("Introduzca la cantidad de filas:", min, max, true);
		int sizey = Input.input("Introduzca la cantidad de columnas:", min, max, true);

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
			sizex = Input.input("Introduzca la cantidad de filas:", min, max, true);
			sizey = Input.input("Introduzca la cantidad de columnas:", min, max, true);
		}

		var modos = new String[] { "Clásico", "Experimental", "Cancelar" };
		int type = Dialog.choices("¿Qué modo desea jugar?", modos);
		var Juego = new SumaTres(new Settings(sizex, sizey, type==1));
		var app = new JFrame(Dialog.title);

		// Todos estos comandos son, en esencia, los incluídos en el enunciado del trabajo.
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Se utiliza WindowCosntants en vez de JFrame para evitar advertencias.
		app.setBounds(0, 0, Graphic.defineX(Juego) + 15, Graphic.defineY(Juego) + 39);
		/*
		 *  Por algún extraño motivo, setBounds() establece una ventana 15 píxeles más pequeña
		 *  horizontalmente y 39 píxeles más pequeña verticalmente de lo que le devuelven
		 *  defineX() y defineY() respectivamente.
		 */
		app.add(Juego);
		app.setVisible(true);
		app.setResizable(false); // <-- lo siento! no tengo fuerzas para hacer eso true.
		app.setFocusable(true);
		app.setIconImage(Graphic.ICON.getImage());
	}
}
