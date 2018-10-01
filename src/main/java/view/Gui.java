package view;
// TODO: why can't board be of mismatched size?
import control.Controller;
import javafx.util.Pair;
import model.game.Game;
import model.game.Location;
import model.game.Model;
import model.game.PieceSpec;
import model.pieces.PieceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Handles view.Gui, class based on: https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * @author Benjamin Pollak
 */
public class Gui extends JPanel {
    private Model _model;
    private JLabel _currentTurn = new JLabel();
    private final JPanel _gui = new JPanel();
    private JButton[][] _chessBoardSquares;
    private JPanel _titlePage;
    private JPanel _headsUpDisplay;
    JPanel _chessView = new JPanel();
    private JPanel _chessBoard;
    private JPanel _whiteGameInfo;
    private Vector<Component> _whiteGameInfoComponents = new Vector<>();
    private JPanel _blackGameInfo;
    private Vector<Component> _blackGameInfoComponents = new Vector<>();
    private int _boardRows;
    private int _boardCols;
    private Controller _controller;
    private JMenuBar _menuBar;

    private String _firstUser = null;
    private String _secondUser = null;

    private int _top;
    private int _left;
    private int _right;
    private int _bottom;

    public Color colorInCheck = null;

    public final String _whiteMove = "WHITE'S MOVE";

    public Runnable guiRunner = new Runnable() {
            @Override
            public void run() {
                Gui gui = new Gui(8,8); // TODO: hardcoding...

                JFrame f = new JFrame("Chess");
                f.setJMenuBar(_menuBar);
                f.add(gui.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };

    /**
     * Gui constructor
     * @param bRows: number of rows
     * @param bCols: number of columns
     */
    public Gui(int bRows, int bCols) {
        _chessBoardSquares = new JButton[bRows][bCols];
        _top = 50; _bottom = 50; _left = 50; _right=  50;
        _boardRows = bRows; _boardCols = bCols;
        initializeGui(_boardRows, _boardCols);
    }

    /**
     * Creates the menu across the top
     */
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


    /**
     *
     * @param firstPlayerName: string of first player's name
     * @param secondPlayerName: string of second player's name
     * @param errorLabel
     */
    private void actionPerformedWrapper(JTextField firstPlayerName, JTextField secondPlayerName, JLabel errorLabel) {
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
        _model = new Model(firstName, secondName, _boardRows, _boardCols);
        _controller = new Controller(_model, _currentTurn, _whiteGameInfoComponents, _blackGameInfoComponents);
        _titlePage.setVisible(false);
        _gui.remove(_titlePage);
        createChessView();
    }

    /**
     * Creates introductory page
     */
    private final void createTitlePage() {
        _titlePage = new JPanel(new GridLayout(3, 2));
        _titlePage.setName("titlePage");
        JButton startButton = new JButton("Start a new game");
        startButton.setName("startButton");
        JLabel firstPlayerLabel = new JLabel("First player's name");
        firstPlayerLabel.setName("firstPlayer");
        JTextField firstPlayerName = new JTextField(16);
        JLabel secondPlayerLabel = new JLabel("Second player's name");
        secondPlayerLabel.setName("secondPlayer");
        JTextField secondPlayerName = new JTextField(16);
        JLabel errorLabel = new JLabel(); errorLabel.setText("EMPTY FIELD");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformedWrapper(firstPlayerName, secondPlayerName, errorLabel);
            }
        });
        _titlePage.add(firstPlayerLabel); _titlePage.add(firstPlayerName);
        _titlePage.add(secondPlayerLabel); _titlePage.add(secondPlayerName);
        _titlePage.add(startButton);
        _titlePage.add(errorLabel); errorLabel.setVisible(false);
        _gui.add(_titlePage);
    }

    /**
     * Creates display indicating whose turn it is
     */
    private void createHeadsUpDisplay() {
        _headsUpDisplay = new JPanel();
        _headsUpDisplay.setName("headsUpDisplay");
        _currentTurn.setText(_whiteMove);
        _headsUpDisplay.add(_currentTurn);
    }

    /**
     * Creates chess board
     */
    private final void createChessView() {
        _chessView = new JPanel();
        _chessView.setName("chessView");
        _chessView.setLayout(new BorderLayout());
        createChessBoard(_boardRows, _boardCols);
        createWhiteGameInfo();
        createBlackGameInfo();
        createHeadsUpDisplay();

        _chessView.add(_chessBoard, BorderLayout.CENTER);
        _chessView.add(_whiteGameInfo, BorderLayout.LINE_START);
        _chessView.add(_blackGameInfo, BorderLayout.LINE_END);
        _chessView.add(_headsUpDisplay, BorderLayout.SOUTH);
        _gui.add(_chessView);
    }

    /**
     * Creates GUI for the black side's metadata
     */
    private final void createBlackGameInfo() {
        _blackGameInfo = new JPanel(new GridLayout(4, 2));
        _blackGameInfo.setName("blackGameInfo");
        _blackGameInfo.setBorder(new LineBorder(java.awt.Color.BLACK));

        _blackGameInfo.add(new JTextArea("Black In Check:"));
        JTextArea bCheckText = new JTextArea(""); bCheckText.setName("BCHECK");
        _blackGameInfo.add(bCheckText); _blackGameInfoComponents.add(bCheckText);

        _blackGameInfo.add(new JTextArea("Black In Checkmate:"));
        JTextArea bCheckmateText = new JTextArea(""); bCheckmateText.setName("BCHECKMATE");
        _blackGameInfo.add(bCheckmateText); _blackGameInfoComponents.add(bCheckmateText);

        _blackGameInfo.add(new JTextArea("Black In Stalemate"));
        JTextArea bStalemateText = new JTextArea(""); bStalemateText.setName("BSTALEMATE");
        _blackGameInfo.add(bStalemateText); _blackGameInfoComponents.add(bStalemateText);

        _blackGameInfo.add(new JTextArea("Black Valid Move:"));
        JTextArea bValidText = new JTextArea(""); bValidText.setName("BVALID");
        _blackGameInfo.add(bValidText); _blackGameInfoComponents.add(bValidText);
    }

    /**
     * Creates GUI for the white side's metadata
     */
    private final void createWhiteGameInfo() {
        _whiteGameInfo = new JPanel(new GridLayout(4, 2));
        _whiteGameInfo.setName("whiteGameInfo");
        _whiteGameInfo.setBorder(new LineBorder(java.awt.Color.BLACK));

        _whiteGameInfo.add(new JTextArea("White In Check:"));
        JTextArea wCheckText = new JTextArea(""); wCheckText.setName("WCHECK");
        _whiteGameInfo.add(wCheckText); _whiteGameInfoComponents.add(wCheckText);

        _whiteGameInfo.add(new JTextArea("White In Checkmate:"));
        JTextArea wCheckmateText = new JTextArea(""); wCheckmateText.setName("WCHECKMATE");
        _whiteGameInfo.add(wCheckmateText); _whiteGameInfoComponents.add(wCheckmateText);

        _whiteGameInfo.add(new JTextArea("White In Stalemate"));
        JTextArea wStalemateText = new JTextArea(""); wStalemateText.setName("WSTALEMATE");
        _whiteGameInfo.add(wStalemateText); _whiteGameInfoComponents.add(wStalemateText);

        _whiteGameInfo.add(new JTextArea("White Valid Move:"));
        JTextArea wValidText = new JTextArea(""); wValidText.setName("WVALID");
        _whiteGameInfo.add(wValidText); _whiteGameInfoComponents.add(wValidText);
    }

    /**
     * Creates the chess board
     * @param boardRows: number of rows
     * @param boardCols: number of columns
     */
    private final void createChessBoard(int boardRows, int boardCols) {
        Game game = _model.getGame();
        _chessBoard = new JPanel(new GridLayout(boardRows, boardCols));
        _chessBoard.setName("chessBoard");
        _chessBoard.setBorder(new LineBorder(java.awt.Color.BLACK));

        for (int col = 0; col < _boardRows; col++) {
            for (int row = 0; row < _boardCols; row++) {
                Square b = new Square(row, col, game, _controller);

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
        drawWhitePieces(game.getWhitePieces());
        drawBlackPieces(game.getBlackPieces());
    }

    /**
     * Creates the gui
     * @param boardRows: number of rows
     * @param boardCols: number of columns
     */
    private final void initializeGui(int boardRows, int boardCols) {
        _gui.setBorder(new EmptyBorder(_top, _left, _bottom, _right));
        createTitlePage();
        createMenus();
    }

    /**
     * Draws black pieces on board initially
     * @param pieces: Pieces to be drawn on
     */
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

    /**
     * Draws white pieces on board initially
     * @param pieces: Pieces to be drawn on
     */
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

    /**
     * Draws king(s) on board
     * @param locations: location(s) of king(s)
     * @param piece: String representing King character
     */
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

    /**
     * Draws queen(s) on board
     * @param locations: location(s) of queens(s)
     * @param piece: String representing queen character
     */
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

    /**
     * Draws bishops on board
     * @param locations: locations of bishops
     * @param piece: String representing bishop character
     */
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

    /**
     * Draws knights on board
     * @param locations: locations of knights
     * @param piece: String representing knight character
     */
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

    /**
     * Draws rooks on board
     * @param locations: locations to place rooks on the board
     * @param string: String representing the character for rooks
     */
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

    /**
     * Draws pawns on the board
     * @param locations: Locations for pawns
     * @param piece: String representing pawn character
     */
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

    /**
     * Getter for _gui variable
     * @return The root JFrame _gui
     */
    public final JComponent getGui() {
        return _gui;
    }

    /**
     * Main method
     * @param args: Ignore
     */
    public static void main(String[] args) {
        Gui gui = new Gui(8, 8); // TODO: hard-coding...
        SwingUtilities.invokeLater(gui.guiRunner);
    }
}
