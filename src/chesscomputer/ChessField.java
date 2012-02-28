package chesscomputer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessField  {
         
    /**
     * 
     * @return
     */
    public  List<ChessPiece> initiate() {
        try {
             List<ChessPiece> listOfPieces = new ArrayList<ChessPiece>();
            //weiße Bauern
            Color white = Color.white;
            ChessLocation chessLocation01 = new ChessLocation(0, 1, false);
            ChessLocation chessLocation11 = new ChessLocation(1, 1, false);
            ChessLocation chessLocation21 = new ChessLocation(2, 1, false);
            ChessLocation chessLocation31 = new ChessLocation(3, 1, false);
            ChessLocation chessLocation41 = new ChessLocation(4, 1, false);
            ChessLocation chessLocation51 = new ChessLocation(5, 1, false);
            ChessLocation chessLocation61 = new ChessLocation(6, 1, false);
            ChessLocation chessLocation71 = new ChessLocation(7, 1, false);          
            ChessLocation chessLocation00 = new ChessLocation(0, 0, false);
            ChessLocation chessLocation10 = new ChessLocation(1, 0, false);
            ChessLocation chessLocation20 = new ChessLocation(2, 0, false);
            ChessLocation chessLocation30 = new ChessLocation(3, 0, false);
            ChessLocation chessLocation40 = new ChessLocation(4, 0, false);
            ChessLocation chessLocation50 = new ChessLocation(5, 0, false);
            ChessLocation chessLocation60 = new ChessLocation(6, 0, false);
            ChessLocation chessLocation70 = new ChessLocation(7, 0, false);

            listOfPieces.add(new Pawn(chessLocation01, this, white));
            listOfPieces.add(new Pawn(chessLocation11, this, white));
            listOfPieces.add(new Pawn(chessLocation21, this, white));
            listOfPieces.add(new Pawn(chessLocation31, this, white));
            listOfPieces.add(new Pawn(chessLocation41, this, white));
            listOfPieces.add(new Pawn(chessLocation51, this, white));
            listOfPieces.add(new Pawn(chessLocation61, this, white));
            listOfPieces.add(new Pawn(chessLocation71, this, white));            
            listOfPieces.add(new Tower(chessLocation00, this, white));
            listOfPieces.add(new Knight(chessLocation10, this, white));
            listOfPieces.add(new Bishop(chessLocation20, this, white));
            listOfPieces.add(new Queen(chessLocation30, this, white));
            listOfPieces.add(new King(chessLocation40, this, white));
            listOfPieces.add(new Bishop(chessLocation50, this, white));
            listOfPieces.add(new Knight(chessLocation60, this, white));
            listOfPieces.add(new Tower(chessLocation70, this, white));

            //black
            ChessLocation chessLocation06 = new ChessLocation(0, 6, false);
            ChessLocation chessLocation16 = new ChessLocation(1, 6, false);
            ChessLocation chessLocation26 = new ChessLocation(2, 6, false);
            ChessLocation chessLocation36 = new ChessLocation(3, 6, false);
            ChessLocation chessLocation46 = new ChessLocation(4, 6, false);
            ChessLocation chessLocation56 = new ChessLocation(5, 6, false);
            ChessLocation chessLocation66 = new ChessLocation(6, 6, false);
            ChessLocation chessLocation76 = new ChessLocation(7, 6, false);            
            ChessLocation chessLocation07 = new ChessLocation(0, 7, false);
            ChessLocation chessLocation17 = new ChessLocation(1, 7, false);
            ChessLocation chessLocation27 = new ChessLocation(2, 7, false);
            ChessLocation chessLocation37 = new ChessLocation(3, 7, false);
            ChessLocation chessLocation47 = new ChessLocation(4, 7, false);
            ChessLocation chessLocation57 = new ChessLocation(5, 7, false);
            ChessLocation chessLocation67 = new ChessLocation(6, 7, false);
            ChessLocation chessLocation77 = new ChessLocation(7, 7, false);

            Color black = Color.black;

            listOfPieces.add(new Pawn(chessLocation06, this, black));
            listOfPieces.add(new Pawn(chessLocation16, this, black));
            listOfPieces.add(new Pawn(chessLocation26, this, black));
            listOfPieces.add(new Pawn(chessLocation36, this, black));
            listOfPieces.add(new Pawn(chessLocation46, this, black));
            listOfPieces.add(new Pawn(chessLocation56, this, black));
            listOfPieces.add(new Pawn(chessLocation66, this, black));
            listOfPieces.add(new Pawn(chessLocation76, this, black));
            listOfPieces.add(new Tower(chessLocation07, this, black));
            listOfPieces.add(new Knight(chessLocation17, this, black));
            listOfPieces.add(new Bishop(chessLocation27, this, black));
            listOfPieces.add(new Queen(chessLocation37, this, black));
            listOfPieces.add(new King(chessLocation47, this, black));
            listOfPieces.add(new Bishop(chessLocation57, this, black));
            listOfPieces.add(new Knight(chessLocation67, this, black));
            listOfPieces.add(new Tower(chessLocation77, this, black));   
            
            return listOfPieces;

        } catch (Exception ex) {
            Logger.getLogger(ChessField.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;

    }
}
