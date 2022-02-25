/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import game.LauncherRF;

/**
 *
 * @author JuanMier
 */
public class LoopComms extends LoopTask {
    
    private boolean isStopped;

    public LoopComms(LauncherRF l) {
        super(l);
    }

    @Override
    public void Start() {
        ventana.loopStarting();
    }

    @Override
    public void Finish() {
        ventana.loopEnding();
    }

    @Override
    public void Message(String s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update() {
        partida.update();
    }

    @Override
    public boolean Stop() {
        return isStopped;
    }
    
    public void setStop() {
        this.isStopped = true;
    }

    @Override
    public void Progress(int val) {

    }
    
}
