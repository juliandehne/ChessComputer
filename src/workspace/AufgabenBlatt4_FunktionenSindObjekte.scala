package workspace

import chesscomputer.scala.Move
import java.awt.Color
import scala.util.Random

object AufgabenBlatt4_FunktionenSindObjekte {
    
    /** 
     * Schreibe eine Funktion und zwei Hilfsfunktionen mit folgenden Eigenschaften:
     * Die Funktion soll für eine Liste vom Typ (String,Int,Int,Color)
     * als Parameter nehmen und entweder 
     * eine Liste vom Typ (Int,Int), oder eine Liste vom Typ (String,Color)
     * zurückgeben. Dabei soll sie wahlweise die erste oder die zweite 
     * Hilfsfunktion verwenden
     *    
     */
    def Aufgabe1(signaturMüsstIhrDiesmalMachen:Any) {}
    
    /** 
     * Schreibe eine Funktion, die aus einer für eine Stellung vom Typ List[(String,Int,Int,Color)]
     * dies entspricht einer Liste (Figurname, x, y, Farbe)
     * alle Zugmöglichkeiten (vonx1,vony1,nachx2,nachy2) der Form (Int,Int,Int,Int) berechnet. 
     * Verwendet hierfür eure Funktionen aus den vorhergehenden Arbeitsblättern.
     * Falls eine Figur noch nicht ziehen kann, ist nicht so schlimm.
     * Zum Testen könnt ihr die Funktion unten verwenden
     */
    def Aufgabe2(signaturMüsstIhrDiesmalMachen:Any) :List[(Int,Int,Int,Int)] = {
      Nil
    }
    
    
    /**
     * Wir spielen wieder:
     * zieht einen beliebigen Zug aus Aufgabe 2
     */
    def testeAufgabe2() {      
      ScalaInterface.startGame
      val zugListe = Aufgabe2(ScalaInterface.getStellung)
      val neueZugListe= zugListe.drop(Random.nextInt(zugListe.size))
      ScalaInterface.move(new Move(neueZugListe.head._1,neueZugListe.head._2,neueZugListe.head._3,neueZugListe.head._4))
    }
    
}
