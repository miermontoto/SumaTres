package thread;

import game.LauncherRF;

/**
 *
 * @author JuanMier
 */
public class LoopComms extends LoopTask {
    
    private boolean isStopped;
    private LauncherRF ventanaPrincipal;

    public LoopComms(LauncherRF l) {
        super(l);
        ventanaPrincipal = l;
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
    public void Update() {
        partida.update();
    }

    @Override
    public boolean Stop() {
        return isStopped;
    }
    
    public void setStop(boolean t) {
        this.isStopped = t;
    }

    @Override
    public void Progress(int val) {
        ventanaPrincipal.setProgress(val);
    }
    
}
