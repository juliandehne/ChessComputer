package chesscomputer;

import java.awt.Point;
import java.util.Comparator;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessLocation implements Comparable<ChessLocation> {

    /**
     * 
     */
    public Point point;
    /**
     * 1 2 3 4 5 6 7 8 (x)
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * (y)
     * @param x
     * @param y
     * @throws Exception 
     */
    public ChessLocation(int x, int y) throws Exception {
        if (x < 9 && y < 9 && x > 0 && y > 0) {
            this.point = new Point(x, y);
        } else {
            this.point = null;
            throw new Exception("Diese Koordinate ist nicht valide bzw. größer 8: (" + x +", "+ y +")" );
        }
    }

    /**
     * 
     * @param x
     * @param y
     * @param noTest
     * @throws Exception
     */
    public ChessLocation(int x, int y, boolean noTest) throws Exception {
        this.point = new Point(x, y);
    }

    /**
     * 
     */
    public void transform18_07() {
        if (this.point.x > 0) {
            this.point.x = this.point.x - 1;
        }
        if (this.point.y > 0) {
            this.point.y = this.point.y - 1;
        }
    }

    /**
     * 
     */
    public void transform07_18() {
        this.point.x = this.point.x + 1;
        this.point.y = this.point.y + 1;
    }

    /**
     * 
     * @param c1
     * @return
     */
    public Boolean isEqual(ChessLocation c1) {
        return (this.point.x == c1.point.x && this.point.y == c1.point.y);
    }

    @Override
    public int compareTo(ChessLocation o) {
        return equals(o) ? 1 : -1;
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public static int transform(int x, int y) {
        return (x - 1) + ((y - 1) * 8);
    }
}