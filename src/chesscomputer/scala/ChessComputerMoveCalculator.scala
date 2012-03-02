/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import java.awt.Color

class ChessComputerMoveCalculator(cStellung: List[(String,Int,Int,Color)  ], color: Color) {
  /** dieser Typ kodiert das Schachfeld als Figur, X-Koordinate, Y-Koordinate, Farbe der Figur */
  type mytypeNXYC = (String,Int,Int,Color)   
       
  /*** 
   * berechnet alle möglichen Züge des Computers 
   */
  def calculateAllComputerMoves(): List[Move] ={      
    var filteredList = Util.filterWithParameter(cStellung,color,FilterNXYC.isComputerColor)    
    
 
    ((     
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterKing, calcKing),"König",mapName) :::
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterKnight, calcKnight),"Springer",mapName) :::
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterQueen, calcQueen),"Dame",mapName) :::
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterPawn, calcPawn),"Bauer",mapName) :::
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterBishop, calcBishop),"Läufer",mapName) :::
      Util.mapWithParameter[Move,String](filterAndCalculate(filteredList, FilterNXYC.filterTower, calcTower),"Turm",mapName)
    )
    .filterNot(FilterMove.filterOriginEqualsDestination)
    .filterNot(FilterMove.filterOutOfBounds))            
  }
  
  def calculateChessFreeMoves() : List[Move] = {
    calculateAllComputerMoves.filter(filterNotSchach)
  }
  
  def mapName( move: Move, name: String): Move = {
    move.name= name
    move    
  } 
 
  
  /***
   * Hilfsfunktion, um generisch die Züge für Figuren berechnen, die linear ziehen
   */
  def calcLinear(x:Int,y:Int, xS:Int, yS:Int, xF: Int=>Int,yF: Int=>Int) : List[Move] = {    
    if (x > 8 || x < 1 || y > 8 || y < 1) {
      List.empty[Move]
    } else if (x == xS && y == yS) {
      calcLinear(xF(x),yF(y),xS,yS, xF,yF)      
    } 
    else if (existsOwn(x, y)) {
      List.empty[Move]      
    }
    else if (existsOther(x, y)) {
      List.apply(new Move(xS,yS,x,y))  
    }
    else {
      List.apply(new Move(xS,yS,x,y)):::calcLinear(xF(x),yF(y),xS,yS, xF,yF)
    }
  }
  
  /***
   * berechnet alle möglichen Züge für den König, der auf Position x,y steht
   */
  def calcKing(x : Int, y: Int) : List[Move] = {    
    ((new Move(x,y,x+1,y))::
     List.apply(new Move(x,y,x,y+1)) :::
     List.apply(new Move(x,y,x+1,y+1)):::
     List.apply(new Move(x,y,x-1,y)):::
     List.apply(new Move(x,y,x,y-1)):::
     List.apply(new Move(x,y,x-1,y-1)):::
     List.apply(new Move(x,y,x+1,y-1)):::
     List.apply(new Move(x,y,x-1,y+1)))
    .filter(notMoveOnOwn)
  }  
  
  /***
   * berechnet alle möglichen Züge für den Turm, der auf Position x,y steht
   */
  def calcTower(x:Int, y: Int) : List[Move] = {
    calcLinear(x, y, x, y, id, minus1):::
    calcLinear(x, y, x, y, minus1, id):::
    calcLinear(x, y, x, y, add1, id):::
    calcLinear(x, y, x, y, id, add1)    
  }
  /***
   * berechnet alle möglichen Züge für den Läufer, der auf Position x,y steht
   */
  def calcBishop(x:Int, y: Int) : List[Move] = {
    calcLinear(x, y, x, y, minus1, minus1):::
    calcLinear(x, y, x, y, minus1, add1):::
    calcLinear(x, y, x, y, add1, minus1):::
    calcLinear(x, y, x, y, add1, add1)    
  }
  
  /***
   * berechnet alle möglichen Züge für die Dame, der auf Position x,y steht
   */
  def calcQueen(x:Int, y: Int) : List[Move] = {
    calcTower(x, y):::calcBishop(x, y)
  }   

  /***
   * berechnet alle möglichen Züge für die Springer, der auf Position x,y steht
   */
  def calcKnight(x:Int, y: Int) : List[Move] = {
    (List.apply(new Move(x,y,x+2,y-1)):::
     List.apply(new Move(x,y,x+2,y+1)):::
     List.apply(new Move(x,y,x-1,y+2)):::
     List.apply(new Move(x,y,x-1,y-2)):::      
     List.apply(new Move(x,y,x-2,y+1)):::
     List.apply(new Move(x,y,x-2,y-1)):::
     List.apply(new Move(x,y,x+1,y-2)):::
     List.apply(new Move(x,y,x+1,y+2)))
    .filter(notMoveOnOwn)
  }
  
  /***
   * berechnet alle möglichen Züge für die Bauer, der auf Position x,y steht
   */
  def calcPawn(x:Int,y:Int) : List[Move] = {    
    var list =  List.empty[Move]
    if (color == Color.black) {
      list =((new Move(x,y,x,y-1))::list).filter(notMoveOnOther)
      if (existsOther(x+1,y-1)) {
        list = (new Move(x,y,x+1,y-1)) :: list
      }
      if (existsOther(x-1,y-1)) {
        list = (new Move(x,y,x-1,y-1)) :: list
      }
    } else {
      list = ((new Move(x,y,x,y+1)) :: list).filter(notMoveOnOther)
      if (existsOther(x+1, y+1)) {
        list = (new Move(x,y,x+1,y+1)) :: list
      } 
      if (existsOther(x-1, y+1)) {
        list = (new Move(x,y,x-1,y+1)) :: list
      }
    }
    (list.filter(notMoveOnOwn))
  }
  
  /**
   * nimmt eine Liste mit Koordinaten und ruft die entsprechende Zugberechungsfunktion für die Figurenart auf
   * Die Idee ist, dass die Zweiertupel auf die Parameter der Calcfunktionen gemappt werden 
   */ 
  def createListOfCalcResults(koordinaten: List[(Int,Int)], p:(Int,Int) => List[Move] ) : List[Move]= {    
    if (koordinaten.isEmpty) {
      List.empty[Move]      
    } else {
      p(koordinaten.head._1,koordinaten.head._2):::createListOfCalcResults(koordinaten.drop(1),p)     
    }
  }
  
  /***
   * nimmt eine Filterfunktion, mit der aus einer Liste von NXYC die entsprechenden Figuren samt ihrer Position herausgesucht werden
   * auf diese Tupel wird dann die Hilfsfunktion createListOfCalcResults aufgerufen, die mit für diese Figuren die möglichen Züge berechnet
   */
  def filterAndCalculate(list:List[mytypeNXYC], filterer:(mytypeNXYC => Boolean), calculate: (Int,Int) => List[Move]) : List[Move] = {
    var test = list.filter(filterer).map(cutName).map(cutColor).toList
    createListOfCalcResults(test,calculate) 
  }
   
  /**diese Hilfsfunktion berechnet, ob eine Figur auf einem Feld x,y steht*/
  def isSamePosition(elem:mytypeNXYC,x:Int,y:Int): Boolean = {
    elem._2==x && elem._3==y
  }
   
  /**schneidet aus einem NXYC den Namen (N) weg*/
  def cutName(elem:mytypeNXYC) : (Int,Int,Color) ={
    (elem._2,elem._3,elem._4)
  } 
   
  /**schneidet aus einem NXYC die Farbe (C) weg*/
  def cutColor(elem:(Int,Int,Color)) : (Int,Int) ={
    (elem._1,elem._2)
  } 

  /**testet ob auf einem Feld x,y eine eigene Figur steht. Es besteht eine Abhängigkeit zu der Bekanntheit der Stellung*/
  def existsOwn (x:Int,y:Int) : Boolean = {
    var blacks = Util.filterWithParameter(cStellung,color,(FilterNXYC.isComputerColor))
    blacks.map(cutName).contains((x,y,color))    
  }
   
  /**testet ob auf einem Feld x,y eine fremde Figur steht. Es besteht eine Abhängigkeit zu der Bekanntheit der Stellung*/
  def existsOther(x:Int,y:Int) : Boolean = {
    var blacks = Util.filterWithParameter(cStellung,color,(FilterNXYC.isNotComputerColor))    
    if (color == Color.black) {
      blacks.map(cutName).contains((x,y,Color.white))    
    } else {
      blacks.map(cutName).contains((x,y,Color.black))    
    }
  }
   
  /**
   * testet, ob x, y noch im Feld sind
   */
  def notOutOfBounds(x:Int,y:Int) :Boolean = {
    x>0 && x<9 && y>0 && y<9 
  }
  
  /***
   * filtered Züge danach, ob sie auf eine eigene Figur ziehen würden
   * TODO: unabhängig machen, so dass es in FilterMove verschoben werden kann
   */
  def notMoveOnOwn(move: Move) : Boolean =  {
    !existsOwn(move.gettox,move.gettoy)
  }
   
  /***
   * filtered Züge danach, ob sie auf eine fremde Figur ziehen würden
   * TODO: unabhängig machen, so dass es in FilterMove verschoben werden kann
   */
  def notMoveOnOther(move: Move) : Boolean =  {
    !existsOther(move.gettox,move.gettoy)
  }  
  

  /**
   * erstellt eine imaginäre Stellung
   */
  def createImaginaryStellung(stellung:List[mytypeNXYC], move:Move, color:Color) : List[mytypeNXYC] = {
    (move.getName,move.gettox,move.gettoy,color)::Util.filterWithParameter(stellung,move,FilterNXYC.filterMove)
  }
  
  /**
   * filtert Züge danach, ob danach Schach herrscht
   */
  def filterNotSchach(move:Move): Boolean = {   
    var imaginaryStellung = createImaginaryStellung(cStellung,move,color)
    var listKingPosition = Util.filterWithParameter(imaginaryStellung.filter(FilterNXYC.filterKing),color,FilterNXYC.isComputerColor)    
    var imaginaryMoves = createImaginaryMoves(imaginaryStellung, oppositecolor(color))
    Util.filterWithParameter(imaginaryMoves,listKingPosition.head,FilterMove.filterDestinationEqualsPosition).isEmpty    
  }
  
  def createImaginaryMoves(imaginaryStellung:List[mytypeNXYC], color:Color) : List[Move] = {
    var imaginaryPlayer = new ChessComputerMoveCalculator(imaginaryStellung,color)
    imaginaryPlayer.calculateAllComputerMoves    
  }
  
  def mapMoveToPositions(move:Move): (List[mytypeNXYC],Move) ={
    (createImaginaryStellung(cStellung, move, color),move)
  }
 
  
  def oppositecolor(c: Color) : Color = {
    if (c == Color.black) {
      Color.white 
    } else {
      Color.black
    }
  }
  
  def countNumberofPossibleMoves(list:List[mytypeNXYC],color:Color) : Double =  {
      var computerdor = new ChessComputerMoveCalculator(list,color)
      computerdor.calculateChessFreeMoves.size
  }
  
   
  /** ohne Kommentar*/
  def add1(x:Int) : Int = {x+1}
  /** ohne Kommentar*/
  def minus1(x:Int) : Int = {x-1}
  /** ohne Kommentar*/
  def id(x:Int) : Int = {x}
}
