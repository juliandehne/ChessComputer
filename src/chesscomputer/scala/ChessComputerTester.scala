/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import chesscomputer.ScalaInterfaceImpl
import java.awt.Color

class ChessComputerTester {
  def printPositions(positions:List[(java.lang.String,Int,Int,Color)]) {
    if (!positions.isEmpty)
    {
      var elem = positions.head      
      System.out.print("Figur: "+elem._1 + " (" + elem._2+"," + elem._3+","+elem._4.toString +")\n")
      printPositions(positions.drop(1))
    } 
  }
  def printMoves(moves : List[Move], label:String) {
    var moveIterator = moves.iterator
    while (moveIterator.hasNext) {
      var elem = moveIterator.next.asInstanceOf[Move]
      System.out.println(label+ "Name der Figur "+elem.getName+": "+elem.getfromx+", "+elem.getfromy+", "+elem.gettox+", "+elem.gettoy);
    }
  }
  
  def test1(gui : ScalaInterfaceImpl, color:Color): Boolean= {
    System.out.println("TestJavaToScala")      
    printPositions(JavaToScala.convertStellung(gui.getStellung))
    true
  } 
  
  def testBishopCalc(gui : ScalaInterfaceImpl, color:Color) {
    System.out.println("TestLinearCalc")
    gui.move(4, 7, 4, 5)
    var logic = new ChessComputerMoveCalculator(JavaToScala.convertStellung(gui.getStellung),color)    
    printMoves(logic.calcBishop(3, 8),"Läufer")
    //erwartet Züge > nil
  }
  def testLinearCalc(gui : ScalaInterfaceImpl, color:Color) {
    System.out.println("TestLinearCalc")
    gui.move(4, 7, 4, 5)
    var logic = new ChessComputerMoveCalculator(JavaToScala.convertStellung(gui.getStellung),color)    
    printMoves(logic.calcLinear(3, 8, 3, 8, logic.add1, logic.minus1),"Läufer")
  }
  
  def testExistsOwn(gui: ScalaInterfaceImpl, color: Color) {
    System.out.println("TestLinearCalc")
    gui.move(4, 7, 4, 5)
    var logic = new ChessComputerMoveCalculator(JavaToScala.convertStellung(gui.getStellung),color)
    System.out.println("Es existiert eine Figur auf 4,7"+ logic.existsOwn(4, 7))
  }
  
  
  def testKingCalc(gui : ScalaInterfaceImpl, color:Color) {
    System.out.println("TestKingCalc")
    var logic = new ChessComputerMoveCalculator(JavaToScala.convertStellung(gui.getStellung),color)
    printMoves(logic.calcKing(3, 6),"King")
  }
  
  def testPawnCalc(gui : ScalaInterfaceImpl, color:Color) {
    System.out.println("TestPawnCalc")
    var logic = new ChessComputerMoveCalculator(JavaToScala.convertStellung(gui.getStellung),color)
    printMoves(logic.calcPawn(3, 3),"Pawn")
  }   
  
  def testCalculateAllMoves(gui:ScalaInterfaceImpl,color:Color) {
    gui.move(4, 1, 4, 3)
    gui.move(6, 1, 4, 7)
    var cStellung = JavaToScala.convertStellung(gui.getStellung)
    var logic = new ChessComputerMoveCalculator(cStellung,color)   
    var move = new Move(5,8,4,7)
    move.setName("König")
    var imaginaryStellung = logic.createImaginaryStellung(cStellung, move,Color.black)
    var listKingPosition = Util.filterWithParameter(imaginaryStellung.filter(FilterNXYC.filterKing),color,FilterNXYC.isComputerColor)    
    var imaginaryMoves = logic.createImaginaryMoves(imaginaryStellung, Color.white)
    printPositions(imaginaryStellung)
    printMoves(logic.createImaginaryMoves(imaginaryStellung, Color.white), "Zug: ")
    
  }
  
  def calculateBestMove(gui:ScalaInterfaceImpl, color: Color)  {
    gui.move(4, 8, 6, 6)
    gui.move(7, 8, 7, 4)
    
    var stellung = JavaToScala.convertStellung(gui.getStellung)
    var logic = new ChessComputerLogic(gui, color)
    printMoves(logic.calculateBestMove(stellung, color),"Züge ")
    
  }
  
  def irgendwas (list:List[Int]) {    
    var elem = list.map(addab(1))
  }
  
  def addab (a: Int) (b: Int) : Int ={
    a+b
  }
  
//  def testsumValuesOfPieces(gui:ScalaInterfaceImpl,color:color) {
//    System.out.println("TestSumValues")
//    var kiLogic = new ChessComputerLogic(gui,color)
//    System.out.println(kiLogic.sumValuesOfPieces(kiLogic.cStellung))   
//  }
  
}
