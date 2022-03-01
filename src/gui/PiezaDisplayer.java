/*
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import obj.Pieza;
import util.Paint;

/**
 *
 * @author under
 */
class PiezaDisplayer extends JPanel {
    
    private int valor;
    private Color color;
    private boolean brillo;
    
    protected PiezaDisplayer() {}
    
    protected void setPieza(int nv) {
        valor = nv;
        color = Pieza.getColores().get(nv);
        brillo = Pieza.getBrillos().get(nv);
    }
    
    @Override
    public void paintComponent(Graphics g1) {
        if(valor != 0) {
            super.paintComponent(g1);

            Graphics2D g = (Graphics2D) g1;
            g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
            g.setStroke(new BasicStroke(Paint.STROKE_SIZE));

            g.setColor(color);
            g.fillRoundRect(0, 0,
                Paint.SQUARE_SIZE, Paint.SQUARE_SIZE,
                Paint.ROUND_DIAMETER, Paint.ROUND_DIAMETER);
            g.setColor(Paint.BOARD_COLOR);
            int desiredFontSize = valor >= 350000 ? 10 : 19 - (String.valueOf(valor).length() - 1);
            g.setFont(new Font("Helvetica", Font.PLAIN, desiredFontSize));
            int displacer = 4*(String.valueOf(valor).length() - 1);
            g.drawString(String.valueOf(valor), 
                    23 - displacer, 37);
        }
    }
}
