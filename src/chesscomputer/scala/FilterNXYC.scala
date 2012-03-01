/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chesscomputer.scala

import java.awt.Color

/**
 * Diese Klasse kapselt alle Filter, mit denen Listen von Figuren der Form NXYC (siehe unten) 
 * gefiltert werden können
 */
object FilterNXYC  {
  /**dieser Typ kodiert das Schachfeld als Figur, X-Koordinate, Y-Koordinate, Farbe der Figur*/
  type mytypeNXYC = (String,Int,Int,Color)   
   
  def filterKing(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "König")
  }
  def filterKnight(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "Springer")
  } 
  def filterTower(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "Turm")
  } 
  def filterQueen(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "Dame")
  } 
  def filterPawn(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "Bauer")
  }
  def filterBishop(elem:mytypeNXYC) : Boolean = {
    (elem._1 == "Läufer")
  }   
   
     
  def isComputerColor(elem:mytypeNXYC, color: Color) : Boolean = {
    elem._4 == color
  }
  def isNotComputerColor(elem:mytypeNXYC, color: Color) : Boolean = {
    !isComputerColor(elem,color)
  }
 
  def filterMove(elem:mytypeNXYC, move:Move): Boolean =  {
    !(elem._2 == move.getfromx && elem._3 == move.getfromy || elem._2 == move.gettox && elem._3 == move.gettoy )
  }
  
 
  
  
  
}
