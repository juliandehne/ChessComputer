  /**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

  /**Filter für eine Liste von Züge*/
object FilterMove {
  def filterOriginEqualsDestination(elem: Move) : Boolean = {
    (elem.getfromx == elem.gettox && elem.getfromy == elem.gettoy)
  }
    
  def filterOutOfBounds(elem : Move) : Boolean = {
    (elem.gettox < 1 || elem.gettoy < 1 || elem.gettox > 8 || elem.gettoy > 8 ||
     elem.getfromx < 1 || elem.getfromy < 1 || elem.getfromx > 8 || elem.getfromy > 8 )
  }
}
