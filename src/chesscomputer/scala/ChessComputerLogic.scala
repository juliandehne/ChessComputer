  /**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import chesscomputer.ScalaInterfaceImpl
import java.awt.Color
import java.lang.String
import scala.util.Random

class ChessComputerLogic(gui : ScalaInterfaceImpl, color:Color) {    
  
  var cStellung = JavaToScala.convertStellung(gui.getStellung)
    /**dieser Typ kodiert das Schachfeld als Figur, X-Koordinate, Y-Koordinate, Farbe der Figur*/
  type mytypeNXYC = (String,Int,Int,Color)    
  
    /**führt einen Zug des Computers aus*/
  def move {    
    cStellung = JavaToScala.convertStellung(gui.getStellung)
    var computer = new ChessComputerMoveCalculator(cStellung,color)    
    var moves = computer.calculateAllComputerMoves()
    var size = moves.size
    if (size == 0) {
      System.out.println("kann nicht ziehen")
    } else {  
      var randomIndex = Math.abs(Random.nextInt(size))
      var arraymoves = moves.toArray
      var mover = arraymoves.apply(randomIndex)
      gui.move(mover.getfromx, mover.getfromy, mover.gettox, mover.gettoy)
      System.out.print("The computer would make a move now "+ mover.gettox +" " +mover.gettoy  +"\n")
    }
  }
}

