/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscomputer;

import java.awt.Color;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Julian
 */
public class ChessPieceView extends JLabel {

    /**
     * 
     */
    public ImageIcon icon;

    /**
     * 
     * @param cp
     * @throws IOException
     * @throws Exception
     */
    public ChessPieceView(ChessPiece cp) throws IOException, Exception {

        if (cp.getClass().getCanonicalName().equals(Bishop.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/bishop_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(King.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/king_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Bishop.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/bishop_white.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(King.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/king_white.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Knight.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/knight_white.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Knight.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/knight_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Pawn.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/pawn_white.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Pawn.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/pawn_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Tower.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/tower_white.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Tower.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/tower_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Queen.class.getCanonicalName())
                && cp.color.equals(Color.black)) {
            icon = createImageIcon("pics/queen_black.gif", "");
        } else if (cp.getClass().getCanonicalName().equals(Queen.class.getCanonicalName())
                && cp.color.equals(Color.white)) {
            icon = createImageIcon("pics/queen_white.gif", "");
        } else {
            throw new Exception("Konnte Bild nicht laden");
        }

//        BufferedImage image = ImageIO.read(new File("./pics/king_black.gif"));
//        icon = new ImageIcon(image);
        setIcon(icon);
    }

    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path 
     * @param description
     * @return  
     */
    protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }
   
}