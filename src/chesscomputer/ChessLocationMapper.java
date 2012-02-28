/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import javax.swing.JPanel;

/**
 *
 * @author Julian
 */
public class ChessLocationMapper {

    /**
     * 
     * @return
     */
    public ChessLocation getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     */
    public void setLocation(ChessLocation location) {
        this.location = location;
    }
    private ChessLocation location;

    /**
     * 
     * @return
     */
    public JPanel getJpanel() {
        return jpanel;
    }

    /**
     * 
     * @param jpanel
     */
    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }
    private JPanel jpanel;
    
    private ChessPiece chessPiece;

    /**
     * 
     * @param chessPiece
     */
    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    /**
     * 
     * @return
     */
    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    /**
     * 
     * @param location
     * @param jpanel
     * @param chessPiece
     */
    public ChessLocationMapper(ChessLocation location, JPanel jpanel, ChessPiece chessPiece) {
        this.location = location;
        this.jpanel = jpanel;
        this.chessPiece = chessPiece;
    }

    /**
     * 
     * @param location
     * @param jpanel
     */
    public ChessLocationMapper(ChessLocation location, JPanel jpanel) {
        this.location = location;
        this.jpanel = jpanel;        
    }    
}
