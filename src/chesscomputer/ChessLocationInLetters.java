package chesscomputer;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessLocationInLetters extends ChessLocation {

    String letter;

    /**
     * Diese Klasse ist in der Lage, die Zahlen auf die im Schach üblichen Buchstaben abzubilden
     * @param x
     * @param y
     * @throws Exception
     */
    public ChessLocationInLetters(int x, int y) throws Exception {
        super(x, y);
        this.letter = getLetter18(x);
    }
    
    /**
     * map x-Koordinate auf literalen Bezeichner
     * @param x
     * @return 
     */
    private String getLetter18(int x) {
        switch (x) {
            case 1:
                return "a";
            case 2:
                return "b";
            case 3:
                return "c";
            case 4:
                return "d";
            case 5:
                return "e";
            case 6:
                return "f";
            case 7:
                return "g";
            case 8:
                return "h";
        }
        return null;

    }

    private String getLetter07(int x) {
        return getLetter18(x + 1);
    }
}
