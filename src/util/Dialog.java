package util;

import javax.swing.JOptionPane;
import obj.Pieza;

/**
 * Clase que utiliza JOptionPane para interactuar con el usuario. <p>
 * Incluye mensajes genéricos, mensajes de errores, diálogos yes/no, inputs directos y
 * selección entre opciones con botones.
 * 
 * @author under
 */
public final class Dialog {

    public static final String TITLE = "SumaTres";

    /**
     * Constrctor generado para cumplir con SonarLint:S1118.
     * 
     * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
     * 		Regla SonarLint:S1118 </a>
     */
    private Dialog() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Muestra un cuadro de sí o no, devuelve un booleano acorde a lo escogido por el usuario.
     * @param s Cadena a mostrar al usuario.
     * @return Booleano de la respuesta, true si sí, false si no.
     */
    public static boolean confirm(String s) {
        return JOptionPane.showConfirmDialog(null, s, TITLE, JOptionPane.YES_NO_OPTION) == 0;
    }

    /**
     * Muestra una cadena al usuario.
     * @param s Cadena a mostrar.
     */
    public static void show(String s) {
        JOptionPane.showMessageDialog(null, s, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra una cadena con símbolo de error al usuario.
     * @param s Cadena a mostrar.
     */
    public static void showError(String s) {
        JOptionPane.showMessageDialog(null, s, TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Devuelve una cadena introducida por el usuario.
     * @param s Cadena a mostrar.
     * @return Cadena introducida por el usuario en respuesta.
     */
    public static String input(String s) {
        return JOptionPane.showInputDialog(null, s, TITLE, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Método que devuelve un índice escogido por el usuario mediante botones.
     * Se utiliza un vector de Strings para mostrar las opciones.
     * @param s Mensaje a mostrar.
     * @param c Opciones a escoger.
     * @return Devuelve el índice de la opción escogida dentro del vector.
     */
    public static int choices(String s, String[] c) {
        return JOptionPane.showOptionDialog(null, s, TITLE, JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, c, c[0]);
    }

    /**
     * Exactamente igual que {@link #choices(String, String[])}, pero devuelve la cadena en sí.
     * @param s Mensaje a mostrar.
     * @param c Opciones a escoger.
     * @return Devuelve la cadena de la opción escogida.
     */
    public static String choicesString(String s, String[] c) {
        return c[JOptionPane.showOptionDialog(null, s, TITLE, JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, c, c[0])];
    }

    /**
     * Método que genera un mensaje de error a través de {@link #showError(String)}
     * mediante una excepción.
     * @param e Excepción a mostrar.
     */
    public static void showError(Exception e) {
        showError("ERROR: " + e.toString());
        // Técnicamente, solo debe lanzar errores generados por el código, no por el usuario.
    }

    /**
     * Método que muestra un mensaje de error genérico. <p>
     * Puesto que está pensado para SumaTres, el mensaje es "Valor inválido". <p>
     * Utiliza el método genérico {@link #showError(String)}.
     */
    public static void showError() {
        showError("Valor inválido");
    }
    
    public static int valueDialog(String s) {
        int nV;
        try {
            String respuesta = Dialog.input(s);
            if (respuesta == null || respuesta.length() == 0) {
                nV = -1;
            } else {
                nV = Integer.parseInt(respuesta);
            }
            while (nV != -1 || !Pieza.validValue(nV) || nV == 0) {
                Dialog.showError();
                respuesta = Dialog.input(s);
                if (respuesta == null || respuesta.length() == 0) {
                    nV = -1;
                } else {
                    nV = Integer.parseInt(respuesta);
                }
            }
        } catch (NumberFormatException ex) {
            Dialog.showError(ex);
            nV = -1;
        }
        return nV;
    }
}
