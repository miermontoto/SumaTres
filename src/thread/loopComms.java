package thread;

import game.LauncherRF;

/**
 * Clase que implementa la lógica del bucle de jugadas y se comunica con la ventana principal.
 * @author Juan Mier
 */
public class loopComms extends loopTask {

    private boolean isStopped;

    /**
     * Constructor de la clase.
     * @param l Objeto de clase {@link LauncherRF} que contenga información sobre la partida.
     */
    public loopComms(LauncherRF l) {
        super(l);
        isStopped = false;
    }

    /**
     * Método que informa a la ventana principal del comienzo del loop.
     */
    @Override
    public void start() {
        ventana.loopStarting();
        isStopped = false;
    }

    /**
     * Método que informa a la ventana principal del final del loop.
     */
    @Override
    public void finish() {
        ventana.loopEnding();
    }

    /**
     * Método que informa a la ventana principal del estado actual del loop.
     */
    @Override
    public void update() {
        partida.update();
        ventana.actualizarPneInfo();
    }

    /**
     * Método que devuelve un valor booleano que indica si el loop se ha detenido.
     * @return Valor booleano.
     */
    @Override
    public boolean stop() {
        return isStopped;
    }

    /**
     * Método que detiene o resume el loop de jugadas.
     * @param t Valor booleano que indica si el loop se detendrá o no.
     */
    public void setStop(boolean t) {
        this.isStopped = t;
    }
}
