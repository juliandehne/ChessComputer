package chesscomputer;

import java.util.List;

/**
 * 
 * @author Julian
 */
public interface IChessField {
    void initiate();    
    List<ChessDiagonal> getDiagonals(ChessLocation chessLocation);
    List<ChessVertical> getVertical(ChessLocation chessLocation);
    Boolean isEmptyChessField(ChessLocation cL);
    Boolean isSchach(ChessLocation cl);
}
