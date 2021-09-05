package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.SumaTres;
import obj.Pieza;


public final class Paint {

	public static final int SPOT_SPACER    = (int) (3  * Graphic.SCALE); // Espacio entre piezas.
	public static final int BOARD_SPACER   = (int) (6  * Graphic.SCALE); // Espacio entre el borde del tablero y las piezas.
	public static final int ROUND_DIAMETER = (int) (10 * Graphic.SCALE); // Radio del diámetro del arco.
	public static final int BUTTON_SIZE    = (int) (20 * Graphic.SCALE); // Tamaño de los botones.
	public static final int SQUARE_SIZE    = (int) (40 * Graphic.SCALE); // Tamaño de las piezas.
	public static final int MAIN_SPACER    = (int) (50 * Graphic.SCALE); // Espacio entre el tablero y el borde de la pantalla.
	
	public static final int STROKE_SIZE    = 3; // Tamaño de la "brocha". (i)
	
	private static Graphics2D g;
	private static SumaTres s;
	/* Se utilizan estáticos para evitar la continua llamada de los objetos entre métodos.
	 * Siempre se utiliza el mismo motor gráfico y la misma partida a representar, por lo
	 * que no debería haber ningún problema. Por supuesto, este método de representación
	 * gráfica está creado muy específicamente para este juego y no puede ser utilizado ni
	 * ajustado a otro juego sin mucho esfuerzo y sufrimiento.
	 */
	
	
	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Paint() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Método que optimiza el cambio del tamaño de la fuente. Puesto que siempre se utiliza 'Helvetica' como
	 * fuente, no es necesario cambiar nada más y se acorta más de esta manera. <p>
	 * Se utiliza Helvetica como fuente porque se encuentra en casi cualquier sistema operativo hoy en día y
	 * además está mejor diseñada y adaptada que Arial.
	 * 
	 * @param g2d  Entorno gráfico
	 * @param size Tamaño de fuente a establecer
	 */
	private static void setFontSize(Graphics2D g2d, int size) {
		g2d.setFont(new Font("Helvetica", Font.PLAIN, size));
	}
	
	/**
	 * Método que pinta la partida en la aplicación gráfica. El programa NO se basa en
	 * {@link #game.SumaTres.toString()} para pintar el tablero, no siempre existe la misma
	 * información en ambas salidas.
	 * <ul>
	 * <li>Para pintar las flechas, se utiliza {@link #pintarFlechas(Graphics)}. </li>
	 * <li>Para pintar el tablero, se utiliza {@link #pintarTablero(Graphics)}. </li>
	 * <li>Para pintar las fichas, se utiliza {@link #pintarFichas(Graphics)}. </li>
	 * <li>Para pintar la información, se utiliza {@link #pintarInfo(Graphics)}. </li>
	 * </ul>
	 */
	public static void paint(Graphics gi, SumaTres si) {

		g = (Graphics2D) gi;
		s = si;

		var rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		/*
		 *  Gracias al antialiasing,  los bordes de las fichas, son mucho más suaves y
		 *  aptos para una pantalla de gran dpi y resolución. No afecta al rendimiento del programa.
		 *  Algunas de estas mejoras no conllevan una clara diferencia en la calidad visual del juego.
		 */
		

		g.setRenderingHints(rh);
		g.setStroke(new BasicStroke(STROKE_SIZE));
		
		pintarFlechas();
		pintarTablero();
		pintarFichas();
		pintarInfo();
		if(s.getMode()) pintarBotones(); // Solo existen botones en modo experimental.
	}
	
	/**
	 * Método que imprime las flechas que indican dónde hacer click para realizar un movimiento
	 * y en qué dirección, aunque sea bastante intuitivo de por sí. Los cálculos de la posición
	 * de las flechas son aproximados a ojo, pero se mantienen en cualquier con cualquier
	 * combinación de columnas, filas y resolución de pantalla. <p> Si está activado el modo
	 * experimental, se pintan las flechas que delimitan el movimiento en diagonal. Se han
	 * escogido estos caracteres en concreto porque representan bastante bien el carácter del
	 * movimiento en diagonal: se mueve hasta encontrarse con otra pieza o el borde del tablero,
	 * no hasta estar en la posición más extrema en la dirección indicada. <p> Necesita información
	 * sobre la partida para dibujar correctamente, por lo que se le debe pasar el objeto de tipo
	 * SumaTres correspondiente.
	 *
	 * @param g Entorno gráfico
	 * @param s Objeto SumaTres que contiene la partida en sí
	 */
	private static void pintarFlechas() {
		g.setColor(Color.blue);
		setFontSize(g, 18);
		g.drawString("\u2191", Graphic.defineX(s) / 2, MAIN_SPACER * 14 / 24);
		g.drawString("\u2190 ", MAIN_SPACER * 9 / 24, (Graphic.defineY(s) - MAIN_SPACER) / 2);
		g.drawString("\u2192", Graphic.defineX(s) - MAIN_SPACER * 16 / 24, (Graphic.defineY(s) - MAIN_SPACER) / 2);
		g.drawString("\u2193", Graphic.defineX(s) / 2, Graphic.defineY(s) - MAIN_SPACER * 35 / 24);
		if(s.getMode()) {
			g.drawString("\u2B76", MAIN_SPACER * 9 / 24, MAIN_SPACER * 14 / 24);
			g.drawString("\u2B77", Graphic.defineX(s) - MAIN_SPACER * 16 / 24, MAIN_SPACER * 14 / 24);
			g.drawString("\u2B79", MAIN_SPACER * 9 / 24, Graphic.defineY(s) - MAIN_SPACER * 35 / 24);
			g.drawString("\u2B78", Graphic.defineX(s) - MAIN_SPACER * 16 / 24, Graphic.defineY(s) - MAIN_SPACER * 35 / 24);
		}
	}
	
	/**
	 * Método sencillo que imprime el tablero, es decir, un rectángulo blanco, en la aplicación
	 * gráfica. Para obtener las dimensiones del tablero, se tienen en cuenta los espaciados
	 * entre el tablero y el borde de la ventana, el tamaño de las piezas, la separación entre
	 * piezas y la separación entre las piezas y el tablero. También se encarga de pintar los botones.
	 * 
	 * @param g Entorno gráfico
	 * @param s Objeto SumaTres que contiene la partida en sí
	 */
	private static void pintarTablero() {
		g.setColor(Color.white);
		g.fillRoundRect(MAIN_SPACER, MAIN_SPACER,
			s.getTablero().getRows() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER,
			s.getTablero().getColumns() * (SPOT_SPACER + SQUARE_SIZE) + 2 * BOARD_SPACER - SPOT_SPACER,
			ROUND_DIAMETER, ROUND_DIAMETER);
	}

	private static void pintarBotones() {
		// Primero se dibujan los botones. Solo se deben poder utilizar en modo experimental y cuando
		// solo esté jugando un jugador.

		g.setColor(Color.white);
		g.fillRoundRect(Graphic.defineX(s) - BUTTON_SIZE, Graphic.defineY(s) - BUTTON_SIZE,
				BUTTON_SIZE, BUTTON_SIZE, ROUND_DIAMETER, ROUND_DIAMETER); // Botón de toggleConsole()
		g.setColor(Color.darkGray);
		g.drawRoundRect(Graphic.defineX(s) - BUTTON_SIZE,  Graphic.defineY(s) - BUTTON_SIZE,
				BUTTON_SIZE, BUTTON_SIZE, ROUND_DIAMETER, ROUND_DIAMETER);
		g.setColor(s.consoleStatus() ? Color.green : Color.red);
		setFontSize(g, (int) (7 * Graphic.SCALE * 2));
		g.drawString("T", Graphic.defineX(s) - BUTTON_SIZE * 3 / 4 , Graphic.defineY(s) - BUTTON_SIZE / 4);

		g.setColor(Color.white);
		g.fillRoundRect(0, Graphic.defineY(s) - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE,
				ROUND_DIAMETER, ROUND_DIAMETER);
		g.setColor(Color.darkGray);
		g.drawRoundRect(0, Graphic.defineY(s) - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE,
				ROUND_DIAMETER, ROUND_DIAMETER);

		if(!s.cheatsUsed()) { // Botón de enableCheats()
			setFontSize(g, (int) (7 * Graphic.SCALE * 5));
			g.drawString("*", BUTTON_SIZE / 8, Graphic.defineY(s) + BUTTON_SIZE / 2);
		} else {
			setFontSize(g, (int) (7 * Graphic.SCALE * 4));
			g.drawString("+", BUTTON_SIZE / 7, Graphic.defineY(s)); // ponerPieza())

			g.setColor(Color.white); // Botón de quitarPieza()
			g.fillRoundRect(BUTTON_SIZE, Graphic.defineY(s) - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE,
					ROUND_DIAMETER, ROUND_DIAMETER);
			g.setColor(Color.darkGray);
			g.drawRoundRect(BUTTON_SIZE, Graphic.defineY(s) - BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE,
					ROUND_DIAMETER, ROUND_DIAMETER);
			setFontSize(g, (int) (7 * Graphic.SCALE * 5));
			g.drawString("-", BUTTON_SIZE + 2*BUTTON_SIZE / 8, Graphic.defineY(s) - BUTTON_SIZE / 18);

			if(s.getTurnos() > 1) { // Botón de undo()
				g.setColor(Color.white);
				g.fillRoundRect(Graphic.defineX(s) - 2 * BUTTON_SIZE + SPOT_SPACER, Graphic.defineY(s) - BUTTON_SIZE,
						BUTTON_SIZE	, BUTTON_SIZE, ROUND_DIAMETER, ROUND_DIAMETER); 
				g.setColor(Color.darkGray);
				g.drawRoundRect(Graphic.defineX(s) - 2 * BUTTON_SIZE + SPOT_SPACER, Graphic.defineY(s) - BUTTON_SIZE,
						BUTTON_SIZE	, BUTTON_SIZE, ROUND_DIAMETER, ROUND_DIAMETER);

				setFontSize(g, (int) (7 * Graphic.SCALE * 3));
				g.drawString("⤺", Graphic.defineX(s) - 8 * BUTTON_SIZE / 4 + SPOT_SPACER,
						Graphic.defineY(s) - BUTTON_SIZE / 6); // ponerPieza())
			}
		}



	}
	
	/**
	 * Método que imprime información sobre la partida como los turnos, los puntos o la
	 * siguiente pieza en pantalla. Requiere un objeto de tipo SumaTres para calcular
	 * posiciones correctamente y obtener información sobre la partida.
	 * 
	 * @param g Entorno gráfico
	 * @param s Objeto SumaTres que contiene la partida en sí
	 */
	private static void pintarInfo() {
		g.setColor(Color.black);
		setFontSize(g, 15);
		
		// Siguiente:
		g.drawString("Siguiente:", MAIN_SPACER * 2 - 75, Graphic.defineY(s) - MAIN_SPACER / 2);
		// Turnos:
		g.drawString(String.format("[%d]", s.getTurnos()), Graphic.defineX(s) / 2, Graphic.defineY(s) - MAIN_SPACER / 2);
		// Puntos:
		/*
		 *  Primero se calcula si es necesario decrecer el tamaño de la fuente dependiendo
		 *  de la longitud de los puntos de la ficha. Es ligeramente inncesario ya que en
		 *  rara ocasión el usuario va a llegar a cantidades tan altas, pero eh.
		 */
		setFontSize(g, s.getPuntos() >= 10000000 ? 12 : 15);
		g.drawString(String.format("Puntos: %d", s.getPuntos()), Graphic.defineX(s) - 2 * MAIN_SPACER,
				Graphic.defineY(s) - MAIN_SPACER / 2);
		
		// Pieza siguiente:
		g.setColor(Pieza.getColores().get(s.getSiguiente()));
		g.fillRoundRect(MAIN_SPACER * 2, Graphic.defineY(s) - MAIN_SPACER,
				SQUARE_SIZE, SQUARE_SIZE,
				ROUND_DIAMETER, ROUND_DIAMETER);
		g.setColor(Color.white);
		int desiredFontSize = s.getSiguiente() >= 350000 ? 10 : 19 - (String.valueOf(s.getSiguiente()).length() - 1);
		setFontSize(g, desiredFontSize); // Se desplaza a la derecha cuando la pieza pasa a tener más de un dígito.
		g.drawString(String.format("%d", s.getSiguiente()), MAIN_SPACER * 112 / 48,
			Graphic.defineY(s) - MAIN_SPACER / 2);
	}
	
	/**
	 * Método que pinta las fichas en pantalla.
	 * <p>
	 * Supuestamente, después de pintar el tablero mediante {@link #pintarTablero(Graphics)} se
	 * pintan las fichas. Para esto, se examina el tablero entero. Si la posición en el tablero
	 * tiene valor <code>0</code>, no se pinta nada.
	 * <p>
	 * Para obtener los colores con los que se va a pintar las piezas, se utiliza un HashMap con
	 * los valores de las piezas como claves y los colores como valores. Si no existe una clave,
	 * se genera un nuevo color y se guarda, de modo que todas las futuras piezas con ese valor
	 * tengan el mismo color. Para esto, se utiliza {@link #newRandom(int)}. Esto puede causar
	 * que el color de algunas piezas sea muy similar al de algunos valores predeterminados, o
	 * que la fuente blanca no se vea encima al imprimir el valor de la ficha.
	 * <p>
	 * Dependiendo de la cantidad de dígitos que tiene una ficha, su valor se imprime desplazado
	 * hacia la izquierda para centrar el valor de las fichas más grandes.
	 * 
	 * @param g Entorno gráfico
	 * @param s Objeto SumaTres que contiene la partida en sí
	 */
	private static void pintarFichas() {
		for (int i = 0; i < s.getTablero().getColumns(); i++)
			for (int j = 0; j < s.getTablero().getRows(); j++) { // Se recorre el tablero.
				if (s.getTab(i, j) != 0) { // Al detectarse una pieza, se obtiene su color referente.
					g.setColor(s.getTablero().getPieza(i, j).getColor());
					// Se pinta la pieza en sí.
					g.fillRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
						MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
						ROUND_DIAMETER, ROUND_DIAMETER);
					
					// 'Y' es la luminosidad del color de la ficha.
					double Y = (0.2126*g.getColor().getRed() + 0.7152*g.getColor().getGreen() + 0.0722*g.getColor().getBlue());
					
					// Si la pieza es demasiado clara, se le pinta un reborde para que se aprecie.
					if(Y>=211) {
						g.setStroke(new BasicStroke(1));
						g.setColor(Color.gray);
						g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j,
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i, SQUARE_SIZE, SQUARE_SIZE,
							ROUND_DIAMETER, ROUND_DIAMETER);
						g.setStroke(new BasicStroke(STROKE_SIZE));
					}
					
					// Si no se está en el primer turno, se imprime un rectángulo alrededor de la nueva ficha
					// para diferenciarla. (debería haber alguna manera mejor de señalarla?)
					if(s.getTurnos() > 1 && s.getWarning()[0] != -1) {
						g.setColor(Color.yellow);
						g.drawRoundRect(MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getWarning()[1],
							MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * s.getWarning()[0], SQUARE_SIZE, SQUARE_SIZE,
							ROUND_DIAMETER, ROUND_DIAMETER);
					}
					
					// Por último, se pinta el valor de la ficha.
					// Si la luminosidad pasa de un cierto valor, el color de la fuente del valor
					// de la ficha debería ser negro, de lo contrario es blanco. Si la ficha es la
					g.setColor(Y>=210 ? Color.black : Color.white);
					
					// Se establece un tamaño de fuente en función de los dígitos de la ficha.
					int desiredFontSize = 19 - (String.valueOf(s.getTab(i, j)).length() - 1);
					setFontSize(g, s.getTab(i, j) >= 350000 ? 10 : desiredFontSize);
					int sizer = s.getTab(i, j) >= 100000 ? (13 - (String.valueOf(s.getTab(i, j)).length()-1)) :
						(13 - (2*(String.valueOf(s.getTab(i, j)).length()-1)));
					g.drawString(String.format("%d", s.getTab(i, j)),
						MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * j + SQUARE_SIZE * sizer / 32,
						SQUARE_SIZE * 5 / 8 + MAIN_SPACER + BOARD_SPACER + (SQUARE_SIZE + SPOT_SPACER) * i);
					// Dependiendo del tamaño de la pieza, se desplaza ligeramente a la izquierda para que siga centrada
					// en concordancia con el resto de piezas.
			}
		}
	}
}
