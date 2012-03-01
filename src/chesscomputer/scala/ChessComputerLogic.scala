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
    var moves = computer.calculateChessFreeMoves
       
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
  
  /**
   * berechnet die Wertigkeit aller Figuren
   * gegenerische Dame +9
   * gegnerischer Turm +8
   * gegnerischer Läufer +3,5
   * gegnerischer Springer +3
   * gegnerischer Bauer +1
   */
  def sumValuesOfPieces(list: List[mytypeNXYC]): Double = {
    var listOfOponentNXYC = Util.filterWithParameter(list,color,FilterNXYC.isComputerColor)
    sumValuesOfPiecesHelper(listOfOponentNXYC)-
    sumValuesOfPiecesHelper(list.filter(listOfOponentNXYC.contains)
    )            
  }
  
  def sumValuesOfPiecesHelper(list: List[mytypeNXYC]): Double = {
    list.filter(FilterNXYC.filterBishop).size * 3.5 +
    list.filter(FilterNXYC.filterQueen).size * 9 +
    list.filter(FilterNXYC.filterTower).size * 5 +
    list.filter(FilterNXYC.filterPawn).size +
    list.filter(FilterNXYC.filterKnight).size * 3 
  }   
  
  def calculateBestMove(list: List[Move], stellung: List[mytypeNXYC], color: Color) : Move = {
    var stellung = JavaToScala.convertStellung(gui.getStellung)
    var computer = new ChessComputerMoveCalculator(stellung,color)    
    var moves = computer.calculateChessFreeMoves
    var listIndices = findIndexOfHighestDouble(moves.map(computer.mapMoveToPositions).map(sumValuesOfPieces).toList)
    var listOfMovesTheorem1 = List.empty[Move]
    listIndices.foreach(moves.take):: listOfMovesTheorem1
    // Positionen auf Zahlen mappen (value)    
    //entsprechenden index bei moves raussuchen
    //analog für Zugmöglichkeiten        
    var listOfMovesTheorem2 = List.empty[Move]
    var listIndices2 = (findIndexOfHighestDouble(moves.map(computer.mapMoveToPositions).map(mylength).toList))    
    listIndices2.foreach(moves.take):: listOfMovesTheorem2
    if (listOfMovesTheorem1.intersect(listOfMovesTheorem2).isEmpty) {
      listOfMovesTheorem1.head
    } 
    else {
      listOfMovesTheorem1.intersect(listOfMovesTheorem2).head
    }    
    //Matt filtern (schach + keine Stellung, in der kein Schach ist möglich)
    //todo
    
  }
  
  def mylength (list: List[Any]) : Double = {
    list.size
  }
  
  def findIndexOfHighestDouble (list: List[Double]) : List[Int] ={
    var result = 0.0
    var count = 0
    var resultIndices = List.empty[Int]
    while (!list.isEmpty) {
      if (list.head > result) {
        result = list.head
        count::resultIndices
      }
      count = count + 1
      list.drop(1)
    }
    resultIndices
  }
      
}

