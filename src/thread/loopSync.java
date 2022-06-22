package thread;

/**
 * Interfaz con los métodos de las clases referentes al loop de jugadas.
 * Estructura necesaria debido a restricciones del trabajo de CPM.
 * @author Juan Mier / Profesores de la asignatura CPM (GIITIN), EPI Gijón 21-22.
 */
public interface loopSync {

    void start(); // Método que se ejecuta al iniciar el loop.
    void finish(); // Método que se ejecuta al finalizar el loop.

    void run(); // Método que ejecuta el loop.
    boolean stop(); // Método que devuelve si el loop se ha detenido.

    void update(); // Método que informa a la ventana principal del estado actual del loop.
}
