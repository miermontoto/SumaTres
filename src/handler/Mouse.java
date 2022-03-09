package handler;

import java.awt.event.MouseEvent;

import game.SumaTres;
import util.Graphic;
import util.Paint;

public class Mouse {
    
    private static boolean handlingEnabled = true;
    
    private final SumaTres s;
    private final MouseEvent e;
    
    public static void disableHandling() {handlingEnabled = false;}

    public Mouse(SumaTres si, MouseEvent ei) {
        s = si;
        e = ei;
    }
    
    /**
     * Método que diferencia entre eventos y los redirige.
     */
    public void mouseHandler() {
        //System.out.printf("%d %d (%d) %n", e.getX(), e.getY(), Paint.MAIN_SPACER);
        if(e.getButton() == MouseEvent.BUTTON1 && handlingEnabled 
                && inFrameClick() && !inBoardClick()) jugadaHandler();
    }
    
    private boolean inFrameClick() {
        return e.getY() <= (Graphic.lateralSize(s.getSettings().getX()) + Paint.MAIN_SPACER);
    }
    
    private boolean inBoardClick() {
        return e.getX() > Paint.MAIN_SPACER && e.getY() > Paint.MAIN_SPACER &&
                e.getX() <= Graphic.lateralSize(s.getSettings().getY()) && 
                e.getY() <= Graphic.lateralSize(s.getSettings().getX());
    }
    
    private void jugadaHandler() {
        int x = e.getX();
        int y = e.getY();

        int limitX = Graphic.lateralSize(s.getSettings().getX());
        int limitY = Graphic.lateralSize(s.getSettings().getY());
        boolean diagonal = s.getSettings().isDiagonalMovementEnabled();

        if(x <= Paint.MAIN_SPACER) { 
            if(y <= Paint.MAIN_SPACER && diagonal) s.jugada('q');
            else {
                if(y <= limitY) s.jugada('a');
                else if(diagonal && y <= limitY + Paint.MAIN_SPACER) s.jugada('z');
            }
        } else {
            if(x >= limitX) {
                if(y <= Paint.MAIN_SPACER && diagonal) s.jugada('e');
                else {
                    if(y <= limitY) s.jugada('d');
                    else if(diagonal && y <= limitY + Paint.MAIN_SPACER) s.jugada('c');
                }
            } else {
                if(y <= Paint.MAIN_SPACER) s.jugada('w');
                else if(y <= limitY + Paint.MAIN_SPACER && limitY <= y) s.jugada('s');
            }
        }
    }
}
