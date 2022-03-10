package deprecated;
import game.SumaTres;
import handler.Keyboard;
import handler.Mouse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame; // Necesario para crear la ventana gráfica en sí.
import javax.swing.WindowConstants; // Necesario para obtener la acción de cerrar la ventana.
import obj.Settings;

import util.Graphic; // Se utiliza para definir dimensiones, escala, etc.
import util.Dialog; // Se utiliza para preguntarle al usuario otra información.

/**
 * El main se encarga de introducir y comprobar las
 * dimensiones del tablero, el tipo de jugada y la pantalla en sí.
 */
@Deprecated
public class Launcher {
	
	private static final int MIN = 3; // Dimensión mínima del tablero.
	private static final int MAX = 33; // Dimensión máxima del tablero.

	public static void main(String[] args) {

            int sizex = Input.input("Introduzca la cantidad de filas:", MIN, MAX, true);
            int sizey = Input.input("Introduzca la cantidad de columnas:", MIN, MAX, true);

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
                    sizex = Input.input("Introduzca la cantidad de filas:", MIN, MAX, true);
                    sizey = Input.input("Introduzca la cantidad de columnas:", MIN, MAX, true);
            }

            var modos = new String[] { "Clásico", "Experimental", "Cancelar" };
            int type = Dialog.choices("¿Qué modo desea jugar?", modos);
            var juego = new SumaTres(new Settings(sizex, sizey, type==1));
            var app = new JFrame(Dialog.TITLE);

            // Todos estos comandos son, en esencia, los incluídos en el enunciado del trabajo.
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // Se utiliza WindowCosntants en vez de JFrame para evitar advertencias.
            app.setBounds(0, 0, Graphic.defineX(juego) + 15, Graphic.defineY(juego) + 39);
            /*
             *  Por algún extraño motivo, setBounds() establece una ventana 15 píxeles más pequeña
             *  horizontalmente y 39 píxeles más pequeña verticalmente de lo que le devuelven
             *  defineX() y defineY() respectivamente.
             */
            app.add(juego);
            app.setVisible(true);
            app.setResizable(false); // <-- lo siento! no tengo fuerzas para hacer eso true.
            app.setFocusable(true);
            app.setIconImage(Graphic.ICON.getImage());

            juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                new Keyboard(juego, event).keyboardHandler();
                }
            });
        
            juego.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    new Mouse(juego, event).mouseHandler();
                }
            });
        }
}
