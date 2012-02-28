package chesscomputer;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessModell {

    private List<ChessPiece> listOfPieces;
    private Color turn = Color.white;

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    public Color getTurn() {
        return turn;
    }

    public void setListOfPieces(List<ChessPiece> listOfPieces) {
        this.listOfPieces = listOfPieces;
    }

    public List<ChessPiece> getListOfPieces() {
        return listOfPieces;
    }

    public ChessModell(List<ChessPiece> listOfPieces) {
        this.listOfPieces = listOfPieces;
    }

    public ChessPiece getPieceAtLocation(ChessLocation cl) {
        for (ChessPiece piece : listOfPieces) {
            if (cl.equals(piece)) {
                return piece;
            }
        }
        return null;
    }
}
