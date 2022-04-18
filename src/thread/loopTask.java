/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import game.LauncherRF;
import game.SumaTres;
import handler.Keyboard;
import util.Crypto;

/**
 * Clase abstracta que contiene la lógica y ejecuta el loop de jugadas.
 * @author Juan Mier
 */
abstract class loopTask implements loopSync{
    protected LauncherRF ventana;
    protected SumaTres partida;
    protected String jugadas;
    protected int limit;
    protected int slowdown;
    protected boolean mode;
    
    /**
     * Constructor de la clase.
     * @param l Objeto de clase {@link LauncherRF} que contenga información sobre la partida.
     */
    protected loopTask(LauncherRF l) {
        this.ventana = l;
        this.partida = l.getPartida();
        this.jugadas = partida.getSettings().getStatus("diagonalMovement") ? 
                Keyboard.VALID_EXPERIMENTAL_KEYS : Keyboard.VALID_CLASSIC_KEYS;
        this.limit = Integer.MAX_VALUE; // Por defecto, no hay límite.
        this.slowdown = 0; // Por defecto, no hay retraso.
        this.mode = false; // Modo aleatorio por defecto.
    }
    
    /**
     * Método que establece el límite de iteraciones a realizar por el bucle. <p>
     * Si el número es -1, establece el número máximo posible en Java.
     * @param val Número entero a establecer.
     */
    public void setLimit(int val) {
        if(val >= 0) limit = val;
        else if(val == -1) limit = Integer.MAX_VALUE;
    }
    
    /**
     * Método que establece la cantidad de tiempo en milisegundos entre jugadas del bucle.
     * @param val Valor entero en milisegundos.
     */
    public void setSlowdown(int val) {
        if(val >= 0) slowdown = val;
    }
    
    /**
     * Método que establece el modo de loop sobre el tablero.
     * Cuando es falso, se recorre de forma aleatoria.
     * Cuando es verdadero, se recorre de forma secuencial las jugadas disponibles.
     * @param val Valor booleano que define el modo de la tarea.
     */
    public void setMode(boolean val) {
        this.mode = val;
    }
    
    @Override
    public void run() {
            
        start();
    
        int i = 0;
        while(!partida.isFinished() && limit != 0) {
            if(mode) { // Modo secuencial.
                try {
                    partida.jugada(Keyboard.VALID_EXPERIMENTAL_KEYS.charAt(i++)); 
                } catch(StringIndexOutOfBoundsException oob) {i = 0;}
            } else partida.jugada(jugadas.toCharArray()[Crypto.newRandom(jugadas.length())]); // Modo aleatorio.
            update();
            try {
                Thread.sleep(slowdown);
            } catch(InterruptedException ex) {break;}
            if(stop()) {finish(); return;}
            limit--;
        }
        
        finish();
        
    }
}
