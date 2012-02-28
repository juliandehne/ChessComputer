package chesscomputer;

import java.util.List;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessDiagonal {
    List<ChessField> northEastDiagonal;
    List<ChessField> northWestDiagonal;
    List<ChessField> southWestDiagonal;
    List<ChessField> southEastDiagonal;

    public ChessDiagonal(List<ChessField> northEastDiagonal, List<ChessField> northWestDiagonal, List<ChessField> southWestDiagonal, List<ChessField> southEastDiagonal) {
        this.northEastDiagonal = northEastDiagonal;
        this.northWestDiagonal = northWestDiagonal;
        this.southWestDiagonal = southWestDiagonal;
        this.southEastDiagonal = southEastDiagonal;
    }
}
