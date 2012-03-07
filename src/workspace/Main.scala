/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package workspace

object Main {
  def main(args: Array[String]): Unit = {
    hello()
    ScalaInterface.startGame
  }
  def hello() {
    printf("Hallo, ich hoffe ihr werdet Spaß haben und etwas lernen!")
  }
}
