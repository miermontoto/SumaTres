package util;

import java.util.function.Predicate;
import javax.swing.JOptionPane;
import obj.Pieza;

/**
 * Clase que utiliza JOptionPane para interactuar con el usuario. <p>
 * Incluye mensajes genéricos, mensajes de errores, diálogos yes/no, inputs directos y
 * selección entre opciones con botones.
 *
 * @author Juan Mier
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
     * @param msg Cadena a mostrar al usuario.
     * @return Booleano de la respuesta, true si sí, false si no.
     */
    public static boolean confirm(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, TITLE, JOptionPane.YES_NO_OPTION) == 0;
    }

    /**
     * Muestra una cadena al usuario.
     * @param msg Cadena a mostrar.
     */
    public static void show(String msg) {
        JOptionPane.showMessageDialog(null, msg, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra una cadena con símbolo de error al usuario.
     * @param msg Cadena a mostrar.
     */
    public static void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Devuelve una cadena introducida por el usuario.
     * @param msg Cadena a mostrar.
     * @param pred Predicado que filtra los resultados.
     * @return Cadena introducida por el usuario en respuesta.
     */
    public static String input(String msg, Predicate<String> pred) {
        String val = JOptionPane.showInputDialog(null, msg, TITLE, JOptionPane.QUESTION_MESSAGE);
        return pred.test(val) ? val : null;
    }

    public static String input(String msg) {
        return input(msg, x -> true);
    }

    /**
     * Método que devuelve un índice escogido por el usuario mediante botones.
     * Se utiliza un vector de Strings para mostrar las opciones.
     * @param msg Mensaje a mostrar.
     * @param choices Opciones a escoger.
     * @return Devuelve el índice de la opción escogida dentro del vector.
     */
    public static int choices(String msg, String[] choices) {
        return JOptionPane.showOptionDialog(null, msg, TITLE, JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
    }

    /**
     * Exactamente igual que {@link #choices(String, String[])}, pero devuelve la cadena en sí.
     * @param msg Mensaje a mostrar.
     * @param choices Opciones a escoger.
     * @return Devuelve la cadena de la opción escogida.
     */
    public static String choicesString(String msg, String[] choices) {
        return choices[JOptionPane.showOptionDialog(null, msg, TITLE, JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0])];
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

    /**
     * Método que obtiene un valor válido para una pieza.
     * @param msg Mensaje a mostrar.
     * @return Valor entero que se puede utilizar como valor de una pieza.
     */
    public static int valueDialog(String msg) {
        int nV = 0;
        boolean check = true;
        try {
            while (nV == Integer.MIN_VALUE || !Pieza.validValue(nV) || nV == 0) {
                String respuesta;
                if(!check) Dialog.showError();
                else check = false;
                respuesta = Dialog.input(msg,
                        (x) -> (x.isBlank() || x.isEmpty() || Pieza.validValue(Integer.parseInt(x))));
                if(respuesta == null) respuesta = String.valueOf(Integer.MIN_VALUE);
                if (respuesta.isBlank() || respuesta.isEmpty()) {nV = Integer.MIN_VALUE; break;}
                else nV = Integer.parseInt(respuesta);
            }
        } catch (NumberFormatException ex) {
            Dialog.showError(ex);
            nV = -1;
        }
        return nV;
    }
}
