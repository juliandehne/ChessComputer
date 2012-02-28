package chesscomputer;

import java.awt.Color;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class Bishop extends ChessPiece {

    /**
     * Konstruktor
     * @param location
     * @param chessField
     * @param color
     * @throws ColorInvalidException 
     */
    public Bishop(ChessLocation location, ChessField chessField, Color color) throws ColorInvalidException  {
         super(location,chessField, color);
    }
    
    Bishop(Color white) throws ColorInvalidException {
        super(white);
    }  
       
    @Override
    protected String getName() {
        return "Läufer";
    }

}
