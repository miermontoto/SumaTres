import static java.lang.System.out; //se importa de esta manera para acortar durante el resto del programa
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SumaTresTexto {

	/**
	 * Método ideado para la entrada de valores del tablero. Se realiza
	 * mediante ventanas emergentes con JOptionPane. Si se cancela o se envía una
	 * cadena vacía, lanza <code>java.lang.NumberFormatException</code>.
	 * 
	 * @param s Una cadena que se imprime al usuario para preguntarle por la
	 *          información.
	 * @return Devuelve un entero, ideado para que dicho entero sea el tamaño de una
	 *         dimensión del tablero.
	 */
	public static int inputSize(String s) throws java.lang.NumberFormatException{
		String errorSize = "Debe de ser mayor o igual a 4.";
		int value;
		try {
			value = Integer.parseInt(JOptionPane.showInputDialog(s));
			while (value < 4) {
				JOptionPane.showMessageDialog(null, errorSize);
				value = Integer.parseInt(JOptionPane.showInputDialog(s));
			}
		} catch (Exception ex) {
			out.println("Valor inválido. Establecido valor por defecto '5'");
			value = 5;
		}
		return value;
	}

	public static void main(String[] args) {
		SumaTres Juego;
		int sizex;
		int sizey;

		sizex = inputSize("Introduzca la cantidad de filas:");
		sizey = inputSize("Introduzca la cantidad de columnas:");

		Juego = new SumaTres(sizex, sizey);
		
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
			Juego = new SumaTres(sizex, sizey);
		}
		
		JFrame app = new JFrame("SumaTres");

		// Todos estos comandos son, en esencia, los incluídos en el enunciado del
		// trabajo.
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setBounds(0, 0, Juego.defineX() + 15, Juego.defineY() + 39);
		// Por algún extraño motivo, setBounds() establece una ventana 15 píxeles más pequeña horizontalmente
		// y 39 píxeles más pequeña verticalmente de lo que le devuelven defineX() y defineY().
		app.add(Juego);
		app.setVisible(true);
		app.setResizable(false);
		app.setFocusable(true);

		out.print(Juego);
	}

}
