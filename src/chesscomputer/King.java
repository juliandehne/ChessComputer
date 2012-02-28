package chesscomputer;

import java.awt.Color;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class King extends ChessPiece {

    /**
     * 
     * @param location
     * @param chessField
     * @param color
     * @throws ColorInvalidException
     */
    public King(ChessLocation location, ChessField chessField, Color color) throws ColorInvalidException  {
         super(location,chessField, color);
    }
    
    King(Color white) throws ColorInvalidException {
        super(white);
    }  

    @Override
    protected String getName() {
        return "König";
    }
    
}
