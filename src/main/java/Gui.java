// TODO: place pieces on board
// TODO: why can't board be of mismatched size?
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

// class based on: https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
public class Gui extends JPanel {
    //private final JPanel gui = new JPanel(new BorderLayout(0, 0));
    private final JPanel gui = new JPanel();
    private JButton[][] chessBoardSquares;
    private JPanel chessBoard;

    Gui(int boardWidth, int boardLength) {
        chessBoardSquares = new JButton[boardWidth][boardLength];
        initializeGui(boardWidth, boardLength);
    }

    public final void initializeGui(int boardWidth, int boardLength) {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(50, 50, 50, 50));

        chessBoard = new JPanel(new GridLayout(boardWidth, boardLength));
        chessBoard.setBorder(new LineBorder(java.awt.Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int col = 0; col < chessBoardSquares.length; col++) {
            for (int row = 0; row < chessBoardSquares[col].length; row++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon( new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0))
                    b.setBackground(java.awt.Color.BLACK);
                else
                    b.setBackground(java.awt.Color.WHITE);
                chessBoardSquares[row][col] = b;
            }
        }

        // fill the black non-pawn piece row
        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardLength; row++) {
                chessBoard.add(chessBoardSquares[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Gui gui = new Gui(8,8);

                JFrame f = new JFrame("Chess");
                f.add(gui.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public final JComponent getGui() {
        return gui;
    }
}
