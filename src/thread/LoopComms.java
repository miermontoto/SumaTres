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
