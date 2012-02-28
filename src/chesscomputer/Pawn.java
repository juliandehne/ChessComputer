package chesscomputer;

import java.awt.Color;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class Pawn extends ChessPiece {

    public Pawn(ChessLocation location, ChessField chessField, Color color) throws ColorInvalidException  {
         super(location,chessField,color);
    }
     Pawn(Color white) throws ColorInvalidException {
        super(white);
    }
    @Override
    protected void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void move(ChessLocation location) throws IllegalMoveException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void move(ChessLocation location, ChessPiece newPiece) throws IllegalMoveException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String getName() {
        return "Bauer";
    }
    
}
