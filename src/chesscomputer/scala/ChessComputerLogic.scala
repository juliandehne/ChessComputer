/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import chesscomputer.ScalaInterfaceImpl
import java.awt.Color
import java.lang.String
import scala.util.Random

class ChessComputerLogic(gui : ScalaInterfaceImpl, color:Color) {    
  var converter = new JavaToScala
  var cStellung = converter.convertStellung(gui.getStellung)
  
  def move {    
    cStellung = converter.convertStellung(gui.getStellung)
    var moves = calculateAllComputerMoves(cStellung)
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
  
  def calculateAllComputerMoves(list:List[(String,Int,Int,Color)]): List[Move] ={    
    var filteredList = list.filter(isComputerColor)
    (
      filterAndCalculate(filteredList, filterKing, calcKing) :::
      filterAndCalculate(filteredList, filterKnight, calcKnight) :::
      filterAndCalculate(filteredList, filterQueen, calcQueen) :::
      filterAndCalculate(filteredList, filterPawn, calcPawn) :::
      filterAndCalculate(filteredList, filterBishop, calcBishop) :::
      filterAndCalculate(filteredList, filterTower, calcTower)
    )
    .filterNot(filterOriginEqualsDestination)
    .filterNot(filterOutOfBounds)
    
  }
  
  
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
  
  def calcTower(x:Int, y: Int) : List[Move] = {
    calcLinear(x, y, x, y, id, minus1):::
    calcLinear(x, y, x, y, minus1, id):::
    calcLinear(x, y, x, y, add1, id):::
    calcLinear(x, y, x, y, id, add1)    
  }
  def calcBishop(x:Int, y: Int) : List[Move] = {
    calcLinear(x, y, x, y, minus1, minus1):::
    calcLinear(x, y, x, y, minus1, add1):::
    calcLinear(x, y, x, y, add1, minus1):::
    calcLinear(x, y, x, y, add1, add1)    
  }
  def calcQueen(x:Int, y: Int) : List[Move] = {
    calcTower(x, y):::calcBishop(x, y)
  }   

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
  
  def add1(x:Int) : Int = {x+1}
  def minus1(x:Int) : Int = {x-1}
  def id(x:Int) : Int = {x}
  def isComputerColor(elem:(String,Int,Int,Color)) : Boolean = {
    elem._4 == Color.black
  }
  def isNotComputerColor(elem:(String,Int,Int,Color)) : Boolean = {
    !isComputerColor(elem)
  }
  def isSamePosition(elem:(String,Int,Int,Color),x:Int,y:Int): Boolean = {
    elem._2==x && elem._3==y
  }
  def cutName(elem:(String,Int,Int,Color)) : (Int,Int,Color) ={
    (elem._2,elem._3,elem._4)
  } 
  def cutColor(elem:(Int,Int,Color)) : (Int,Int) ={
    (elem._1,elem._2)
  } 
     
  def existsOwn (x:Int,y:Int) : Boolean = {
    var blacks = cStellung.filter(isComputerColor)
    blacks.map(cutName).contains((x,y,color))    
  }
  def existsOther(x:Int,y:Int) : Boolean = {
    var blacks = cStellung.filter(isNotComputerColor)
    if (color == Color.black) {
      blacks.map(cutName).contains((x,y,Color.white))    
    } else {
      blacks.map(cutName).contains((x,y,Color.black))    
    }
  }
  def notMoveOnOwn(move: Move) : Boolean =  {
    !existsOwn(move.gettox,move.gettoy)
  }
  def notMoveOnOther(move: Move) : Boolean =  {
    !existsOther(move.gettox,move.gettoy)
  }
  def notOutOfBounds(x:Int,y:Int) :Boolean = {
    x>0 && x<9 && y>0 && y<9 
  }
  def filterKing(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "König")
  }
  def filterKnight(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "Springer")
  } 
  def filterTower(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "Turm")
  } 
  def filterQueen(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "Dame")
  } 
  def filterPawn(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "Bauer")
  }
  def filterBishop(elem:(String,Int,Int,Color)) : Boolean = {
    (elem._1 == "Läufer")
  }
  
  def filterOriginEqualsDestination(elem: Move) : Boolean = {
    (elem.getfromx == elem.gettox && elem.getfromy == elem.gettoy)
  }
  def filterOutOfBounds(elem : Move) : Boolean = {
    (elem.gettox < 1 || elem.gettoy < 1 || elem.gettox > 8 || elem.gettoy > 8 ||
     elem.getfromx < 1 || elem.getfromy < 1 || elem.getfromx > 8 || elem.getfromy > 8 )
  }
  
  def createListOfCalcResults(koordinaten: List[(Int,Int)], p:(Int,Int) => List[Move] ) : List[Move]= {    
    if (koordinaten.isEmpty) {
      List.empty[Move]      
    } else {
      p(koordinaten.head._1,koordinaten.head._2):::createListOfCalcResults(koordinaten.drop(1),p)     
    }
  }
  
  type myType = (String,Int,Int,Color)    
  def filterAndCalculate(list:List[(String,Int,Int,Color)], filterer:(myType => Boolean), calculate: (Int,Int) => List[Move]) : List[Move] = {
    var test = list.filter(filterer).map(cutName).map(cutColor).toList
    createListOfCalcResults(test,calculate) 
  }
  
 
}

