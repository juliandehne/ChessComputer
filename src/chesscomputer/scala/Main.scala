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
        
    /** Das Spielfeld sieht so aus:
     * 1 2 3 4 5 6 7 8 (x)       
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * (y)
     * object 1 ist der Name der Figur, object 2 die x und object 3 die y Koordinate
     */      
//    var stellung = gui.getStellung()
//    var iterator = stellung.listIterator    
//    while (iterator.hasNext) {
//      var elem = iterator.next      
//      System.out.println(elem.object1 + " " + elem.object2 + " " + elem.object3 + " " + elem.object4)
//    }
    
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
    
    
//    var tester = new ChessComputerTester
//    tester.test1(gui, Color.black)
//    tester.testKingCalc(gui, Color.black)
//    tester.testPawnCalc(gui, Color.black)
//    tester.testFilterBlack(gui, Color.black)
//    tester.testLinearCalc(gui, Color.black)    
//    tester.testCalculateAllMoves(gui,Color.black)
//    tester.testExistsOwn(gui, Color.black)
  }   

}
