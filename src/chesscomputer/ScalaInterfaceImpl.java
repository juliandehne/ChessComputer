/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import java.awt.Color;
import java.awt.HeadlessException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class ScalaInterfaceImpl implements ScalaInterface {

    private ChessGUI gui;
    private Boolean started;
    private Color color = Color.BLACK;

    /**
     * 
     * @return
     */
    @Override
    public List<Quadrupel> getStellung() {
        testStarted();
        List result = new LinkedList<Quadrupel>();
        for (ChessLocationMapper mapper : gui.viewmodell.clm) {
            if (mapper.getChessPiece() != null) {               
                    result.add(new Quadrupel(mapper.getChessPiece().getName(), mapper.getLocation().point.x, mapper.getLocation().point.y, mapper.getChessPiece().color));                
            }
        }
        return result;
    }

    /**
     * 
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     */
    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        testStarted();
        try {
            gui.viewmodell.moveFigure(new ChessLocation(fromX, fromY), new ChessLocation(toX, toY));
        } catch (Exception ex) {
            Logger.getLogger(ScalaInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public Boolean getTurn() {
        testStarted();
        return gui.viewmodell.getTurn();
    }

    /**
     * 
     */
    @Override
    public void startGame() {
        try {
            this.gui = new ChessGUI();
            gui.viewmodell.initializeBoard();
        } catch (HeadlessException ex) {
            Logger.getLogger(ScalaInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ScalaInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param color
     */
    @Override
    public void setPlayerColor(Color color) {
        this.color = color;
    }

    private void testStarted() {
        if (gui == null) {
            throw new Error("Spiel wurde noch nicht gestarted");
        }
    }

    /**
     * 
     * @param bool
     */
    public void setTurn(Boolean bool) {
        this.gui.viewmodell.setTurn(bool);
    }
}
