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
    /** 
     * startet das Spiel
     */
    void startGame();
   
    /**
     * @return
     */
    List<Quadrupel> getStellung(); 
    /**
     * 
     * Name der Figur, x Koordinate y Koodinate als int
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     */
    void move(int fromX, int fromY, int toX, int toY);
    /**
     * 
     * @return
     */
    Boolean getTurn(); 
    /**
     * true computer has to move
     * @param bool
     */
    void setTurn(Boolean bool);
    /**
     * utility function
     * @param color
     */
    void setPlayerColor(Color color);    
}
