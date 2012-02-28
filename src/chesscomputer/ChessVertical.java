package chesscomputer;

import java.util.List;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public  class ChessVertical {
    List<ChessField> north;
    List<ChessField> south;
    List<ChessField> east;
    List<ChessField> west;

    public ChessVertical(List<ChessField> north, List<ChessField> south, List<ChessField> east, List<ChessField> west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
    
}
