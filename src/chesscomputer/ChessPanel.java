/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Julian
 */
class ChessPanel extends JPanel {

    public ChessLocation location;
    public static ChessLocation origin;    
    public static boolean first = true;
    public static ChessPanel chessPanel;
    private final ChessGuiHandler handler;

    public ChessPanel(ChessLocation loc, final ChessGuiHandler handler) {
        this.location = loc;
        this.handler = handler;
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                chessPanel = (ChessPanel) me.getComponent();                
                if (first) {
                    chessPanel.setBorder(new LineBorder(Color.red));
                    ChessPanel.origin = location;
                    first = false;
                } else {
                    first = true;
                    try {
                        JPanel jpanel = handler.getLocationMapping(origin).getJpanel();
                        jpanel.setBorder(null);                        
                        handler.validate();
                    } catch (Exception ex) {
                        Logger.getLogger(ChessPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {                        
                        handler.moveFigure(origin, location);
                        handler.setTurn(true);
                    } catch (Exception ex) {
                        Logger.getLogger(ChessPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }
}
