/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import chesscomputer.Quadrupel
import java.awt.Color

object JavaToScala {
  /**convertiert die Liste mit den Figuren in einer Scalaliste*/
  def convertStellung(list1 :java.util.List[Quadrupel]):List[(java.lang.String,Int,Int,Color)]= {
    if (list1.isEmpty) {
      List.empty[(String,Int,Int,Color)] //todo
    } else {
      var elem = list1.get(0)
      list1.remove(0)      
      (elem.object1,elem.object2,elem.object3,elem.object4)::convertStellung(list1)
    }
      
  } 
}
