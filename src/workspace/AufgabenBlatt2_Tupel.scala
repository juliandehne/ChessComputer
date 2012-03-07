package workspace

import chesscomputer.scala.Move
import java.awt.Color

object AufgabenBlatt2_Tupel {

  /** stellung ist eine Liste von (Figurenname : String,x : Int,y :Int,Farbe : java.awt.color), vgl. ScalaInterface */
  val stellung = ScalaInterface.getStellung   
  
  /**
   * Gebe alle Figurennamen der Stellung aus!
   * */
  def Aufgabe1() {
    //TODO: hier euer Code
  }
  
  /**
   * Berechne alle Züge die ein Turm auf Position (x,y) auf einem leeren Feld
   * ziehen kann!
   * */
  def Aufgabe2(x:Int, y:Int): List[(Int,Int)] = {
    Nil    
    //TODO: hier euer Code    
  }
  
  /** 
   * Wir testen mal das Spiel!
   * Schreibe eine Funktion, die eine Springer zieht
   * z.B ScalaInterface.move(new Move(4,2,4,4))   
   */ 
   
  def Aufgabe3() {
    ScalaInterface.startGame    
  }
}
