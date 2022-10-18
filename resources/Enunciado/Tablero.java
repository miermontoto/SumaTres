import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel  {

	//Aquí irían los atributos necesarios

	//Constructores
	Tablero() {
		//El constructor en realidad debe tener parámetros
		//para inicializar por ejemplo el tamaño del tablero

		// Añadimos el 'escuchador' de ratón
		addMouseListener(new MouseHandler());
	}

	//Métodos de la clase que implementan el juego: básicamente poner piezas
	//nuevas en el tablero, hacer jugadas, saber si la partida acabó o
	//imprimir el tablero

	//Método paint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Aquí iría el código para pintar el estado del tablero

	}

	//Clase privada para capturar los eventos del ratón
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked (MouseEvent e) {
			//Mostramos un diálogo con la posición del ratón
			//para ver un ejemplo de cómo se obtienen las coordenadas
			//donde se produjo el click
			JOptionPane.showMessageDialog(null, String.format("Ratón %d,%d \n",e.getX(),e.getY()));

			//Aquí irían las instrucciones para comprobar dónde pincho
			//el usuario y hacer la jugada oportuna

			//Se pueden llamar a los métodos públicos de la clase Tablero

			//Seguramente habrá que repintar el tablero si se realizó
			//una jugada válida
			repaint();
		}
	}
}

