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

    public ChessLocation getLocation() {
        return location;
    }

    public void setLocation(ChessLocation location) {
        this.location = location;
    }
    private ChessLocation location;

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }
    private JPanel jpanel;
    
    private ChessPiece chessPiece;

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public ChessLocationMapper(ChessLocation location, JPanel jpanel, ChessPiece chessPiece) {
        this.location = location;
        this.jpanel = jpanel;
        this.chessPiece = chessPiece;
    }

    public ChessLocationMapper(ChessLocation location, JPanel jpanel) {
        this.location = location;
        this.jpanel = jpanel;        
    }    
}
