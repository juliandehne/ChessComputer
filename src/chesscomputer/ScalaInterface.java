/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import java.awt.Color;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ScalaInterface {  
    void startGame();
    List<Quadrupel> getStellung(); //Name der Figur, x Koordinate y Koodinate als int
    void move(int fromX, int fromY, int toX, int toY);
    Boolean getTurn(); //if true computer has to move
    void setTurn(Boolean bool);
    void setPlayerColor(Color color);    
}
