/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import game.LauncherRF;
import game.SumaTres;
import handler.Keyboard;
import util.Random;

/**
 *
 * @author JuanMier
 */
abstract class LoopTask implements SincroForeBack {
    protected LauncherRF ventana;
    protected SumaTres partida;
    protected String jugadas;
    protected int limit;
    protected int slowdown;
    
    protected LoopTask(LauncherRF l) {
        this.ventana = l;
        this.partida = l.getPartida();
        this.jugadas = partida.getSettings().isDiagonalMovementEnabled() ? 
                Keyboard.VALID_EXPERIMENTAL_KEYS : Keyboard.VALID_CLASSIC_KEYS;
        limit = 1;
        slowdown = 0;
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
    
    @Override
    public void Run() {
            
        Start();
    
        int i = 0;
        while(!partida.isFinished() && i < limit) {
            partida.jugada(jugadas.toCharArray()[Random.newRandom(jugadas.length())]);
            Update();
            try {
                Thread.sleep(0);
            } catch(InterruptedException ex) {break;}
            Progress(barProgress());
            if(Stop()) {Finish(); return;}
            i++;
        }
        
        Finish();
        
    }
    
    private int barProgress() {
        int a = 0;
        for(int i = 0; i < partida.getSettings().getX(); i++) {
            for(int j = 0; j < partida.getSettings().getY(); j++) {
                a += partida.getTablero().getTab(i, j) == 0 ? 1 : 0;
            }
        }
        return a / partida.getSettings().getX() * partida.getSettings().getY();
    }
}
