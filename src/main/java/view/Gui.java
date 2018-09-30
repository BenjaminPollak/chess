package view;
// TODO: why can't board be of mismatched size?
import control.Controller;
import javafx.util.Pair;
import model.game.Game;
import model.game.Location;
import model.game.PieceSpec;
import model.pieces.PieceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Handles view.Gui, class based on: https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * @author Benjamin Pollak
 */
public class Gui extends JPanel {
    private final JPanel gui = new JPanel();
    private JButton[][] _chessBoardSquares;
    private JPanel chessBoard;
    private int _boardRows;
    private int _boardCols;
    private Game _game;
    private Controller _controller;

    private int _top;
    private int _left;
    private int _right;
    private int _bottom;

    public Runnable guiRunner = new Runnable() {
            @Override
            public void run() {
                Gui gui = new Gui(_game);

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

    public Gui(Game game) {
        _controller = new Controller(game);
        int boardRows = game.getBoard().getBoardWidth();
        int boardCols = game.getBoard().getBoardLength();
        _game = game;
        _chessBoardSquares = new JButton[boardRows][boardCols];
        _top = 50; _bottom = 50; _left = 50; _right=  50;
        _boardRows = boardRows; _boardCols = boardCols;
        initializeGui(_boardRows, _boardCols);
        drawWhitePieces(game.getWhitePieces());
        drawBlackPieces(game.getBlackPieces());
    }

    public final void initializeGui(int boardRows, int boardCols) {
        gui.setBorder(new EmptyBorder(_top, _left, _bottom, _right));

        chessBoard = new JPanel(new GridLayout(boardRows, boardCols));
        chessBoard.setBorder(new LineBorder(java.awt.Color.BLACK));

        gui.add(chessBoard);

        // create the chess board squares
        for (int col = 0; col < _boardRows; col++) {
            for (int row = 0; row < _boardCols; row++) {
                Square b = new Square(row, col, _game, _controller);

                Font font = new Font("Code2000", Font.PLAIN, 36);
                b.setText("");
                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0))
                    b.setBackground(java.awt.Color.lightGray);
                else
                    b.setBackground(java.awt.Color.WHITE);
                _chessBoardSquares[row][col] = b;
                chessBoard.add(_chessBoardSquares[row][col]);
            }
        }
    }

    public void drawBlackPieces(PieceSpec pieces) {
         for(Pair pr: pieces) {
            if(pr.getKey() == PieceType.PAWN) {
                Location[] locations = (Location[]) pr.getValue();
                drawPawns(locations, "\u265f");
            }
            else if(pr.getKey() == PieceType.ROOK) {
                Location[] locations = (Location[]) pr.getValue();
                drawRooks(locations, "\u265c");
            }
            else if(pr.getKey() == PieceType.KNIGHT) {
                Location[] locations = (Location[]) pr.getValue();
                drawKnights(locations, "\u265e");
            }
            else if(pr.getKey() == PieceType.BISHOP) {
                Location[] locations = (Location[]) pr.getValue();
                drawBishops(locations, "\u265d");
            }
            else if(pr.getKey() == PieceType.QUEEN) {
                Location[] locations = (Location[]) pr.getValue();
                drawQueens(locations, "\u265b");
            }
            else if(pr.getKey() == PieceType.KING) {
                Location[] locations = (Location[]) pr.getValue();
                drawKings(locations, "\u265a");
            }
        }
    }

    public void drawWhitePieces(PieceSpec pieces) {
        for(Pair pr: pieces) {
            if(pr.getKey() == PieceType.PAWN) {
                Location[] locations = (Location[]) pr.getValue();
                drawPawns(locations, "\u2659");
            }
            else if(pr.getKey() == PieceType.ROOK) {
                Location[] locations = (Location[]) pr.getValue();
                drawRooks(locations, "\u2656");
            }
            else if(pr.getKey() == PieceType.KNIGHT) {
                Location[] locations = (Location[]) pr.getValue();
                drawKnights(locations, "\u2658");
            }
            else if(pr.getKey() == PieceType.BISHOP) {
                Location[] locations = (Location[]) pr.getValue();
                drawBishops(locations, "\u2657");
            }
            else if(pr.getKey() == PieceType.QUEEN) {
                Location[] locations = (Location[]) pr.getValue();
                drawQueens(locations, "\u2655");
            }
            else if(pr.getKey() == PieceType.KING) {
                Location[] locations = (Location[]) pr.getValue();
                drawKings(locations, "\u2654");
            }
        }
    }

    private void drawKings(Location[] locations, String piece) {
         for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(piece);
        }
    }

    private void drawQueens(Location[] locations, String piece) {
        for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(piece);
        }
    }

    private void drawBishops(Location[] locations, String piece) {
        for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(piece);
        }
    }

    private void drawKnights(Location[] locations, String piece) {
        for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(piece);
        }
    }

    private void drawRooks(Location[] locations, String string) {
        for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(string);
        }
    }

    private void drawPawns(Location[] locations, String piece) {
        for(Location lcn: locations) {
            int x = lcn.getKey();
            int y = lcn.getValue();
            Font font = new Font("Code2000", Font.PLAIN, 36);
            JButton b = _chessBoardSquares[x][y];
            b.setFont(font);
            b.setText(piece);
        }
    }

    public final Game getGame() {
        return _game;
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Game game = new Game(8, 8, null, null);
        Gui gui = new Gui(game);
        SwingUtilities.invokeLater(gui.guiRunner);
    }
}
