package chesscomputer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Julian Dehne
 * Dieser Code ist stets buggy und sollte nicht als für irgendwas benutzt werden,
 * was boooooom machen kann.
 */
public class ChessGuiHandler {

    private final ChessGUI gui;
    private GridLayout layout;
    private Container contentPane;
    public List<ChessLocationMapper> clm;
    public JPanel gridContainer;
    private ChessField chessField;
    private Boolean turn = false;

    public ChessGuiHandler(ChessGUI aThis) {   
        this.gui = aThis;
        this.clm = new ArrayList<ChessLocationMapper>(64);
        layout = new GridLayout(8, 8);
        contentPane = this.gui.getContentPane();
        gridContainer = new JPanel(layout);
        Dimension gridContainerSize = new Dimension(800, 800);
        gridContainer.setSize(gridContainerSize);
        contentPane.add(gridContainer);
        contentPane.setSize(gridContainerSize);
    }

    void paintBoard() throws Exception {
        Dimension size = new Dimension(100, 100);
        int k = 0;
        Color color;
        for (int j = 1; j < 9; j++) {
            k = 1 - k;
            for (int i = 1; i < 9; i++) {
                if (i % 2 == k) {
                    color = Color.black;
                } else {
                    color = Color.white;
                }
                JPanel panel = new ChessPanel(new ChessLocation(i, j), this);
//                panel.setBorder(new LineBorder(Color.red));
                panel.setSize(size);
                panel.setBackground(color);
                gridContainer.add(panel);
                clm.add(new ChessLocationMapper(new ChessLocation(i, j), panel));
            }
        }
    }

    public void initializeBoard() throws IOException, Exception {
        chessField = new ChessField();
        chessField.initiate();

        for (ChessPiece piece : chessField.chessModell.getListOfPieces()) {
            piece.location.transform07_18();
            initFigure(piece, piece.location, piece.color);
        }
    }

    /**
     * Fügt eine neue Figur auf das Brett und update auf das Mapping
     * @param chessPiece
     * @param chessLocation
     * @param color
     * @throws IOException
     * @throws Exception 
     */
    public JPanel addFigure(ChessPiece chessPiece, ChessLocation chessLocation) throws IOException, Exception {
        JPanel panel = ((JPanel) this.gridContainer.getComponent(ChessLocation.transform(chessLocation.point.x, chessLocation.point.y)));
        panel.removeAll();
        panel.add(new ChessPieceView(chessPiece));
        this.gui.repaint();
        this.gui.validate();
        return panel;
    }

    public void initFigure(ChessPiece chessPiece, ChessLocation chessLocation, Color color) throws IOException, Exception {
        JPanel addFigure = addFigure(chessPiece, chessLocation);
        ChessLocationMapper mapper = getLocationMapping(chessLocation);
        mapper.setChessPiece(chessPiece);
    }

    public void removeFigure(ChessLocation chessLocation) {
        JPanel panel = ((JPanel) this.gridContainer.getComponent(ChessLocation.transform(chessLocation.point.x, chessLocation.point.y)));
        panel.removeAll();
        validate();
    }

    public void validate() {
        this.gui.repaint();
        this.gui.validate();
    }

    /**
     * Zieht eine Figur von Location X zu Location Y
     * Dabei wird das LocationMapping geupdated
     * @param origin
     * @param destination
     * @throws Exception 
     */
    public void moveFigure(ChessLocation origin, ChessLocation destination) throws Exception {
        JPanel originPanel = ((JPanel) this.gridContainer.getComponent(ChessLocation.transform(origin.point.x, origin.point.y)));
        JPanel destinationPanel = ((JPanel) this.gridContainer.getComponent(ChessLocation.transform(destination.point.x, destination.point.y)));

        ChessLocationMapper originMapping = getLocationMapping(origin);
        ChessPiece chessPiece = originMapping.getChessPiece();
        originMapping.setChessPiece(null);

        removeFigure(origin);
        removeFigure(destination);

        if (chessPiece == null) {
            throw new Error("auf Location" + origin.toString() + "gab es keine gemappte Figur");
        }

        addFigure(chessPiece, destination);
        ChessLocationMapper destinationMapping = getLocationMapping(destination);
        destinationMapping.setChessPiece(chessPiece);
    }

    ChessLocationMapper getLocationMapping(ChessLocation location) throws Exception {
        for (ChessLocationMapper chessLocationMapper : clm) {
            if (chessLocationMapper.getLocation().isEqual(location)) {
                return chessLocationMapper;
            }
        }
        throw new Exception("did not find Panel with given Koordinate");
    }

    public void removeLocationMapping(ChessLocation location) throws Exception {
        int count = 0;
        int resultCount = -1;
        for (ChessLocationMapper chessLocationMapper : clm) {
            if (chessLocationMapper.getLocation().isEqual(location)) {
                resultCount = count;
            }
            count++;
        }
        if (resultCount != -1) {
            clm.remove(resultCount);
        }
    }

    public void printAllMappings() {
        for (ChessLocationMapper mapping : clm) {
            String className;
            if (mapping.getChessPiece() == null) {
                className = "keine";
            } else {
                className = mapping.getChessPiece().getClass().getCanonicalName();
            }
            System.out.println("Mapping: " + mapping.getLocation().point.toString() + " Figur: " + className + "\n");
        }
    }

    /**
     * @return the turn
     */
    public Boolean getTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(Boolean turn) {
        this.turn = turn;     
    }
}
