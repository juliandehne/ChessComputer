/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package workspace

import chesscomputer.ScalaInterfaceImpl
import chesscomputer.scala.JavaToScala
import chesscomputer.scala.Move
import java.awt.Color


object ScalaInterface {
  
  /** dieser Typ kodiert das Schachfeld als Figur, X-Koordinate, Y-Koordinate, Farbe der Figur */
  type mytypeNXYC = (String,Int,Int,Color)   
  
  val gui = new ScalaInterfaceImpl
  
  def startGame() {
    gui.startGame
  }
  
  def getStellung() : List[mytypeNXYC] = {
    JavaToScala.convertStellung(gui.getStellung)
  }
  
  def move(move: Move) {
    gui.move(move.getfromx, move.getfromy, move.gettox, move.gettoy)
  }
  
  def getTurn(): Boolean = {
    gui.getTurn().booleanValue
  }
  
  /**
   * Spielschleife: Der Computer zieht immer nur, wenn er an der Reihe ist        
   */ 
   def play() {
      while (true) {  
        Thread.sleep(4000);        
        if (getTurn) {
          move(calculateMove(getStellung))
          gui.setTurn(false)
        }          
      }
    }
  
   /**
    * hier einen neuen Zug berechnen!
    */
   def calculateMove(list:List[mytypeNXYC]) : Move = {
      new Move(4,2,4,3)
    }
   }
