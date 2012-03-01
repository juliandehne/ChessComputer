package chesscomputer.scala

/*
 * Diese Klasse kapselt einen Zug von (x,y) nach (x,y)
 */
class Move(fromx:Int,fromy:Int, tox:Int, toy : Int) {
    var name = ""
    def getName() : String = name
    def setName(newname: String) {
      name = newname
    }
    
    def getfromx : Int = fromx
    def getfromy : Int = fromy
    def gettox : Int = tox
    def gettoy : Int = toy
}
