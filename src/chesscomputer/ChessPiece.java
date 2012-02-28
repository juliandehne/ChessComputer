package chesscomputer;

import java.awt.Color;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public abstract class ChessPiece {

    /**
     * 
     */
    public ChessLocation location;
    Color color;

    /**
     * Konstruktor: Eine Schachfigur sollte eine Farbe haben und einen Platz
     * auf dem Brett
     * @param location
     * @param chessField
     * @param color
     * @throws ColorInvalidException 
     */
    public ChessPiece(ChessLocation location, ChessField chessField, Color color) throws ColorInvalidException {
        this.location = location;
        if (color != Color.black && color != Color.white) {
            throw new ColorInvalidException();
        }
        this.color = color;
    }

    /**
     * 
     * @param color
     * @throws ColorInvalidException
     */
    public ChessPiece(Color color) throws ColorInvalidException {
        if (color != Color.black && color != Color.white) {
            throw new ColorInvalidException();
        }
        this.color = color;

    }

    /**
     * Ordnet der Figur einen eindeutigen Namen zu, der in Scala verwendet werden kann
     * @return 
     */
    protected abstract String getName();
}
