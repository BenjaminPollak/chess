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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Handles view.Gui, class based on: https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * @author Benjamin Pollak
 */
public class Gui extends JPanel {
    private final JPanel _gui = new JPanel();
    private JButton[][] _chessBoardSquares;
    private JPanel _titlePage;
    JPanel _chessView = new JPanel();
    private JPanel _chessBoard;
    private JPanel _whiteGameInfo;
    private JPanel _blackGameInfo;
    private int _boardRows;
    private int _boardCols;
    private Game _game;
    private Controller _controller;
    private JMenuBar _menuBar;

    private String _firstUser = null;
    private String _secondUser = null;

    private int _top;
    private int _left;
    private int _right;
    private int _bottom;

    public Runnable guiRunner = new Runnable() {
            @Override
            public void run() {
                Gui gui = new Gui(_game);

                JFrame f = new JFrame("Chess");
                f.setJMenuBar(_menuBar);
                f.add(gui.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                f.pack();
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
    }


    private final void createMenus() {
        // TODO: implement forfeit, restart, and info
        _menuBar = new JMenuBar();

        JMenu whiteMenu = new JMenu("White Player Options");
        JMenu blackMenu = new JMenu("Black Player Options");

        JMenuItem whiteDrawItem = new JMenuItem("Propose Draw", KeyEvent.VK_T);
        JMenuItem whiteForfeitItem = new JMenuItem("Forfeit", KeyEvent.VK_T);
        whiteForfeitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: implement
                System.out.println("forfeit");
                _gui.remove(_chessView);
                _chessView.setVisible(false);
                _chessView = null;
                _game = new Game(_boardRows, _boardCols, null, null);
                initializeGui(_boardRows, _boardCols);
            }
        });
        JMenuItem whiteStatsItem = new JMenuItem("Statistics", KeyEvent.VK_T);
        JMenuItem blackDrawItem = new JMenuItem("Propose Draw", KeyEvent.VK_T);
        JMenuItem blackForfeitItem = new JMenuItem("Forfeit", KeyEvent.VK_T);
        JMenuItem blackStatsItem = new JMenuItem("Statistics", KeyEvent.VK_T);

        whiteMenu.add(whiteDrawItem); whiteMenu.add(whiteForfeitItem); whiteMenu.add(whiteStatsItem);
        blackMenu.add(blackDrawItem); blackMenu.add(blackForfeitItem); blackMenu.add(blackStatsItem);

        _menuBar.add(whiteMenu);
        _menuBar.add(blackMenu);
    }

    private final void createTitlePage() {
        _titlePage = new JPanel(new GridLayout(3, 2));
        JButton startButton = new JButton("Start a new game");
        JLabel firstPlayerLabel = new JLabel("First player's name");
        JTextField firstPlayerName = new JTextField(16);
        JLabel secondPlayerLabel = new JLabel("Second player's name");
        JTextField secondPlayerName = new JTextField(16);
        JLabel errorLabel = new JLabel(); errorLabel.setText("EMPTY FIELD");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstPlayerName.getText();
                String secondName = secondPlayerName.getText();
                if((firstName.equals("")) || (secondName.equals(""))) {
                    errorLabel.setVisible(true);
                    return;
                }
                else if (firstName.equals(secondName)) {
                    errorLabel.setText("Names cannot be the same!");
                    errorLabel.setVisible(true);
                    return;
                }
                if(_firstUser != null)
                    _firstUser = firstName;
                if(_secondUser != null)
                    _secondUser = secondName;
                _titlePage.setVisible(false);
                _gui.remove(_titlePage);
                createChessView();
            }
        });
        _titlePage.add(firstPlayerLabel); _titlePage.add(firstPlayerName);
        _titlePage.add(secondPlayerLabel); _titlePage.add(secondPlayerName);
        _titlePage.add(startButton);
        _titlePage.add(errorLabel); errorLabel.setVisible(false);
        _gui.add(_titlePage);
    }

    private final void createChessView() {
        _chessView = new JPanel();
        _chessView.setLayout(new BorderLayout());
        createChessBoard(_boardRows, _boardCols);
        createWhiteGameInfo();
        createBlackGameInfo();

        _chessView.add(_chessBoard, BorderLayout.CENTER);
        _chessView.add(_whiteGameInfo, BorderLayout.LINE_START);
        _chessView.add(_blackGameInfo, BorderLayout.LINE_END);
        _gui.add(_chessView);
    }
    private final void createBlackGameInfo() {
        _blackGameInfo = new JPanel(new GridLayout(4, 2));
        _blackGameInfo.setBorder(new LineBorder(java.awt.Color.BLACK));
        _blackGameInfo.add(new JTextArea("Black In Check:"));
        _blackGameInfo.add(new JTextArea(""));
        _blackGameInfo.add(new JTextArea("Black In Checkmate:"));
        _blackGameInfo.add(new JTextArea(""));
        _blackGameInfo.add(new JTextArea("Black In Stalemate"));
        _blackGameInfo.add(new JTextArea(""));
        _blackGameInfo.add(new JTextArea("Black Good Move:"));
        _blackGameInfo.add(new JTextArea(""));
    }

    private final void createWhiteGameInfo() {
        _whiteGameInfo = new JPanel(new GridLayout(4, 2));
        _whiteGameInfo.setBorder(new LineBorder(java.awt.Color.BLACK));
        _whiteGameInfo.add(new JTextArea("White In Check:"));
        _whiteGameInfo.add(new JTextArea(""));
        _whiteGameInfo.add(new JTextArea("White In Checkmate:"));
        _whiteGameInfo.add(new JTextArea(""));
        _whiteGameInfo.add(new JTextArea("White In Stalemate"));
        _whiteGameInfo.add(new JTextArea(""));
        _whiteGameInfo.add(new JTextArea("White Good Move:"));
        _whiteGameInfo.add(new JTextArea(""));
    }

    private final void createChessBoard(int boardRows, int boardCols) {
        _chessBoard = new JPanel(new GridLayout(boardRows, boardCols));
        _chessBoard.setBorder(new LineBorder(java.awt.Color.BLACK));

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
                _chessBoard.add(_chessBoardSquares[row][col]);
            }
        }
        drawWhitePieces(_game.getWhitePieces());
        drawBlackPieces(_game.getBlackPieces());
    }

    private final void initializeGui(int boardRows, int boardCols) {
        _gui.setBorder(new EmptyBorder(_top, _left, _bottom, _right));
        createTitlePage();
        createMenus();
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
        return _gui;
    }

    public static void main(String[] args) {
        Game game = new Game(8, 8, null, null);
        Gui gui = new Gui(game);
        SwingUtilities.invokeLater(gui.guiRunner);
    }
}
