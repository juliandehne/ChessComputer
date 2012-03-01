package chesscomputer.scala

import chesscomputer.ScalaInterfaceImpl
import java.awt.Color


object Main {   
  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    val gui = new ScalaInterfaceImpl();
    
    //erst einmal ein Spiel starten
    gui.startGame()      
        
    
    //Spielschleife: Der Computer zieht immer nur, wenn er an der Reihe ist        
    while (true) {  
      Thread.sleep(4000);
      var turn = gui.getTurn()   
      if (turn.booleanValue) {
        var computer = new ChessComputerLogic(gui,Color.black)
        computer.move        
        gui.setTurn(false)
      }          
    }
    
    
    var tester = new ChessComputerTester
//    tester.test1(gui, Color.black)
//    tester.testKingCalc(gui, Color.black)
//    tester.testPawnCalc(gui, Color.black)
//    tester.testFilterBlack(gui, Color.black)
//    tester.testLinearCalc(gui, Color.black)    
//    tester.testCalculateAllMoves(gui,Color.black)
//    tester.testExistsOwn(gui, Color.black)
  }   

}
