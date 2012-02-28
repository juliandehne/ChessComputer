package chesscomputer;

/**
 * 
 * @author Julian
 */
interface IChessPiece  {
    void move(ChessLocation location) throws IllegalMoveException;
    void move(ChessLocation location, ChessPiece newPiece) throws IllegalMoveException; //neue Figur holen    
}
