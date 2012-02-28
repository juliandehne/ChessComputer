package chesscomputer;

import java.awt.Color;
import java.util.UUID;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public abstract class ChessPiece implements IChessPiece {

    public  ChessLocation location;
    private ChessField chessField;
    private UUID id;
    Color color;

    public ChessPiece(ChessLocation location, ChessField chessField, Color color) throws ColorInvalidException {
        this.location = location;
        this.chessField = chessField;
        this.id = new UUID(0, 64);
        if (color != Color.black && color != Color.white) {
            throw new ColorInvalidException();
        }
        this.color = color;
    }

    public ChessPiece(Color color) throws ColorInvalidException {
        this.id = new UUID(0, 64);
        if (color != Color.black && color != Color.white) {
            throw new ColorInvalidException();
        }
        this.color = color;

    }

    protected abstract void move();
    protected abstract String getName();
}
