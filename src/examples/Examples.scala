package examples

import java.awt.Color

object Examples {
  val list = 1::2::4::8::Nil
  val list2 = list.drop(3) // ist was?
  val clist = list:::list2 // ist was?
  val hlist = list.head

  /** so definiert man eine Funktion   */
  def tueEtwasMitderListe(list:List[Any]) : List[Any]= {
    if (list.isEmpty) Nil //wenn leer gibt sie Nil(=null) zurück
    else list.distinct // wirft Doppelte aus der Liste
  }
  
  /** Tupel   */
  def soFunktionierenTupel() {
    val list = (1,1)::(3,6)::(8,8)::Nil // Liste von Paaren
    list.head._1 == 1
    list.drop(1).head._2 == 6    
  }
  
  /** Rekursion */
  def fact(n:Int) : Int = {    
    if (n == 0)
      return 1
    else 
      return ( n * fact(n-1))    
  }
  
  /** Signaturen und funktionale Parameter */
  def tueEtwasMitEinerZahle[B](n:Int, funktion:(Int)=>(B)) : B = {
    funktion(n)
  }
  val macheTupel = tueEtwasMitEinerZahle(3,x=>(x,x)) // macheTupel == (3,3)
  val macheTupel2 = tueEtwasMitEinerZahle(3,_.toBinaryString) // macheTupel2 == "11"
  
  /** Map ist eine surjektive Abbildung  */
  def mapExample(position:List[(String,Int,Int,Color)]): List[(Int,Int)] = {
    position.map(x=>(x._2,x._3)).toList
  }
  /** Filter nach Kriterium figur */
  def filterExample(position:List[(String,Int,Int,Color)], figurname:String) : List[(String,Int,Int,Color)] ={
    position.filter(x=>(x._1 == figurname))
  }
  
}


