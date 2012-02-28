package chesscomputer;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Julian
 */
public class ChessGUI extends JFrame {

    public final ChessGuiHandler viewmodell;

    public ChessGUI() throws HeadlessException, Exception {
        this.viewmodell = new ChessGuiHandler(this);
        this.viewmodell.paintBoard();
        this.setSize(new Dimension(800, 800));
        this.setVisible(true);
    }

    public static void main(String[] args) throws HeadlessException, Exception {
        ChessGUI cgui = new ChessGUI();   
        cgui.viewmodell.initializeBoard();
//        cgui.viewmodell.printAllMappings();
    }
}