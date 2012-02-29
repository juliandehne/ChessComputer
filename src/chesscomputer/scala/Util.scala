/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

/*
 * Dieses Singleton kapselt alle nützlichen Funktionen, die nicht für den Schachcomputer alleine wichtig sind
 */
object Util {
  /**
   * mapped eine List vom Typ B mittels einer Funktion (B,C) -> B
   */
  def mapWithParameter[B,C](list : List[B], param: C, f:(B,C)=>B) : List[B] = {
    if (list.isEmpty) {
      List.empty[B]
    } else {
      f(list.head,param)::mapWithParameter[B,C](list.drop(1),param,f)
    }
  }
  
  /**
   * filtert eine Liste vom Typ B mittels des Prädikates (B,C)-> Boolean
   */
  def filterWithParameter[B,C](list : List[B], param: C, f:(B,C)=>Boolean) : List[B] = {
    if (list.isEmpty) {
      List.empty[B]
    } else if (f(list.head,param)) {
      (list.head)::filterWithParameter[B,C](list.drop(1),param,f)
    } else {
      filterWithParameter[B,C](list.drop(1),param,f)
    }
  }
}
