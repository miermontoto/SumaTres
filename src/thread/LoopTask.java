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
abstract public class LoopTask implements SincroForeBack {
    protected LauncherRF ventana;
    protected SumaTres partida;
    protected String jugadas;
    
    public LoopTask(LauncherRF l) {
        this.ventana = l;
        this.partida = l.getPartida();
        this.jugadas = partida.getSettings().isDiagonalMovementEnabled() ? 
                Keyboard.VALID_EXPERIMENTAL_KEYS : Keyboard.VALID_CLASSIC_KEYS;
    }
    
    @Override
    public void Run() {
            
        Start();
    
        while(!partida.isFinished()) {
            partida.jugada(jugadas.toCharArray()[Random.newRandom(jugadas.length())]);
            Update();
            try {
                Thread.sleep(50);
            } catch(InterruptedException ex) {}
            //Progress(barProgress());
            if(Stop()) {Finish(); return;}
            
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
