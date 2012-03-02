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
//    var mover = calculateBestMoveN(cStellung, color,color, 4, cStellung, List.empty[Move], 4, 0, new Move(-1,-1,-1,-1))
//    System.out.println(mover.getfromx+ mover.getfromy+ mover.gettox+ mover.gettoy)
    
//    Random Move::
//    var computer = new ChessComputerMoveCalculator(cStellung,color)    
//    var moves = computer.calculateChessFreeMoves       

    /**
     * bester Zug mit Tiefe 1
     */
    var moves = calculateBestMove(cStellung,color)
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
    sumValuesOfPiecesHelper(list.filterNot(listOfOponentNXYC.contains)
    )            
  }
  
  def sumValuesOfPiecesHelper(list: List[mytypeNXYC]): Double = {
    list.filter(FilterNXYC.filterBishop).size * 3.5 +
    list.filter(FilterNXYC.filterQueen).size * 9 +
    list.filter(FilterNXYC.filterTower).size * 5 +
    list.filter(FilterNXYC.filterPawn).size +
    list.filter(FilterNXYC.filterKnight).size * 3 
  } 
  
  def mapValuesOfPieces (list: (List[mytypeNXYC],Move)) : (Double,Move) = {
    (sumValuesOfPieces(list._1),list._2)
  }
    
  def calculateBestMove(stellung: List[mytypeNXYC], color: Color) : List[Move] = { 
    if (positionIstMatt(stellung,color)) {
      throw new Exception ("Matt!!!")
    }
    var computer = new ChessComputerMoveCalculator(stellung,color)    
    var moves = computer.calculateChessFreeMoves
    
    /** 
     * teste, mit welchem Zug der Computer Matt setzen kann listOfMovesTheorem3 = 
     */ 
//  
    var listOfMovesTheorem3 = (moves.map(computer.mapMoveToPositions).filter(x => positionIstMatt(x._1, computer.oppositecolor(color)))).map(x=>x._2).toList    

    /**
     * teste, mit welchem Zug der Computer am meisten Material gewinnt
     */
    var list1 = ((x:List[(Double,Move)]) => (x.takeWhile(_._1==x.head._1)))(moves.map(computer.mapMoveToPositions).map(mapValuesOfPieces).sortWith(_._1>_._1).toList)
    var listOfMovesTheorem1 = list1.map(z => z._2).toList
    /**
     * teste, mit welchem Zug der Computer die meisten Zugmöglichkeiten erhält
     */
    var list2 = ((x:List[(Double,Move)]) => (x.takeWhile(_._1==x.head._1)))(moves.map(computer.mapMoveToPositions).map(x => (computer.countNumberofPossibleMoves(x._1,color),x._2)).sortWith(_._1>_._1).toList)
    var listOfMovesTheorem2 = list2.map(z => z._2).toList    
    
    /**
     * gewichte die Theoreme
     */
    if (listOfMovesTheorem3.isEmpty) {
      if (listOfMovesTheorem1.intersect(listOfMovesTheorem2).isEmpty) {
        listOfMovesTheorem1      
      } 
      else {
        listOfMovesTheorem1.intersect(listOfMovesTheorem2)
      }          
    } else {
      System.out.println("Matt gefunden")
      listOfMovesTheorem3    
    }
    
    
  }
  
  def calculateBestMoveN(stellung: List[mytypeNXYC], color: Color, originalColor: Color, tiefe:Int, originalStellung:List[mytypeNXYC], blackList:List[Move], originaltiefe:Int, rating:Int, bestMove : Move) : Move = {
    if (stellung.filter(x=>FilterNXYC.isComputerColor(x,originalColor)).size == blackList.size) {
      bestMove
    }
    var computer = new ChessComputerMoveCalculator(stellung,color)
    var currentmove = computer.calculateChessFreeMoves().filterNot(blackList.contains).head
    if (tiefe != 0 && tiefe % 2 != 1) {
      var computer = new ChessComputerMoveCalculator(stellung, color)
      var nextStellung = computer.createImaginaryStellung(stellung,currentmove,color)
      var currentRating = rate(nextStellung, color)
      if (currentRating > rating) {          
        calculateBestMoveN(nextStellung,computer.oppositecolor(color), originalColor,originaltiefe, originalStellung,currentmove::blackList,originaltiefe, currentRating, currentmove)          
      }  else {
        calculateBestMoveN(nextStellung,computer.oppositecolor(color),originalColor,originaltiefe, originalStellung,currentmove::blackList,originaltiefe, rating, bestMove)                   
      }
    } else {
      calculateBestMoveN(stellung, color, originalColor, tiefe -1, originalStellung, blackList, originaltiefe, rating, bestMove)
    }
  }
  
  def rate(stellung: List[mytypeNXYC], color: Color): Int = {
    var moves = calculateBestMove(stellung,color)
    var computer = new ChessComputerMoveCalculator(stellung, color)
    var listOfMovesTheorem3Rating = (moves.map(computer.mapMoveToPositions).filter(x => positionIstMatt(x._1, computer.oppositecolor(color)))) 
    if (!listOfMovesTheorem3Rating.isEmpty) {
      1000
    } else {
        
      /**
       * teste, mit welchem Zug der Computer am meisten Material gewinnt
       */
      var list1 = ((x:List[(Double,Move)]) => (x.takeWhile(_._1==x.head._1)))(moves.map(computer.mapMoveToPositions).map(mapValuesOfPieces).sortWith(_._1>_._1).toList)
      var listOfMovesTheorem1 = list1.map(z => z._1).toList.head * 10
      /**
       * teste, mit welchem Zug der Computer die meisten Zugmöglichkeiten erhält
       */
      var list2 = ((x:List[(Double,Move)]) => (x.takeWhile(_._1==x.head._1)))(moves.map(computer.mapMoveToPositions).map(x => (computer.countNumberofPossibleMoves(x._1,color),x._2)).sortWith(_._1>_._1).toList)
      var listOfMovesTheorem2 = list2.map(z => z._1).toList.head   
      (listOfMovesTheorem1 + listOfMovesTheorem2).toInt
    }
  }
  
  def positionIstMatt(stellung: List[mytypeNXYC],color: Color) : Boolean = {
    var computer = new ChessComputerMoveCalculator (stellung,color)
    computer.calculateChessFreeMoves.filter(computer.filterNotSchach).isEmpty
  }
  
  def mylength (list: List[Any]) : Double = {
    list.size
  }
    
      
}

