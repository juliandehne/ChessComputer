package workspace

object AufgabenBlatt3_Rekursion {
  /** stellung ist eine Liste von (Figurenname : String,x : Int,y :Int,Farbe : java.awt.color),
   * vgl. ScalaInterface */
  val stellung = ScalaInterface.getStellung   
  
   
  /**
   * Berechne alle Züge die ein Läufer auf Position (x,y) auf einem leeren Feld
   * ziehen kann ->rekursiv<- !
   * */
  def Aufgabe1(x:Int, y:Int): List[(Int,Int)] = {
    Nil    
    //TODO: hier euer Code    
  }
  
  /** 
   * optional: 
   * Schreibe eine Funktion, die berechnet, wie viele Züge ein Springer 
   * von Position (x1,y1) nach Position (x2,y2)  braucht! ->rekursiv <- 
   * Veranschaulicht eure Lösung am Brett
   * Warnung: Nicht so einfach dies!
   * List.intersect ist euer Freund
   */ 
   
  def Aufgabe3(x:Int,y:Int) : (Int,Int) = {
    //TODO: hier euer Code    
    ScalaInterface.startGame    
    (-1,-1)
  }
}
