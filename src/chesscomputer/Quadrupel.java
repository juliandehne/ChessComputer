/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import java.awt.Color;

/**
 *
 * @author Julian
 */
public class Quadrupel {
    /**
     * 
     */
    public final int object2;
    /**
     * 
     */
    public final int object3;
    /**
     * 
     */
    public final String object1;
    /**
     * 
     */
    public final Color object4;

    /**
     *
     * Das Spielfeld sieht so aus: </br>
     * 1 2 3 4 5 6 7 8 (x)       </br>
     * 2 </br>
     * 3 </br>
     * 4 </br>
     * 5 </br>
     * 6 </br>
     * 7 </br>
     * 8 </br>
     * (y) </br>
     * object 1 ist der Name der Figur, object 2 die x und object 3 die y Koordinate </br>
     * @param object1
     * @param object2
     * @param object3
     * @param object4
     */
    public Quadrupel(String object1, int object2, int object3, Color object4) {
        this.object1 = object1; 
        this.object2 = object2; 
        this.object3 = object3;       
        this.object4 = object4;       
    }
    
}
