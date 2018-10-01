package model.game;

import model.pieces.Piece;

/**
 * Represents the totality of the model (M in MVC)
 * @author Benjamin Pollak
 */
public class Model {
    private int _numWhiteVictories = 0;
    private int _numBlackVictories = 0;

    private String _whitePlayer;
    private String _blackPlayer;

    private int _bRows;
    private int _bCols;

    private Game _game;

    private boolean kingInCheck = false;

    /**
     *
     * @param whitePlayer: Name of white player
     * @param blackPlayer: Name of black player
     * @param bRows: number of board rows
     * @param bCols: number of board columns
     */
    public Model(String whitePlayer, String blackPlayer, int bRows, int bCols) {
        _whitePlayer = whitePlayer;
        _blackPlayer = blackPlayer;
        _bRows = bRows; _bCols = bCols;
        startNewGame(false);
    }

    /**
     * Discovers if the opposing king is in check
     * @param piece: piece that was moved
     * @throws KingInCheck
     */
    public void discoverCheckState(Piece piece) throws KingInCheck{
        try {
            piece.findIfKingInCheck(_game.getBoard().getField());
            kingInCheck = false;
        } catch(KingInCheck e) {
            kingInCheck = true;
            throw new KingInCheck(null, null); // TODO
        }
    }

    /**
     * Starts a new game, with or without custom pieces
     * @param useCustomPieces Indicates whether or not to use custom pieces
     */
    public void startNewGame(boolean useCustomPieces) {
        _game = null;
        if(useCustomPieces) {
            // TODO
            _game = new Game(_bRows, _bCols, null, null);
        }
        else {
            _game = new Game(_bRows, _bCols, null, null);
        }
    }

    /**
     * Gets _numWhiteVictories
     * @return _numWhiteVictories
     */
    public int getNumWhiteVictories() {
        return _numWhiteVictories;
    }

    /**
     * Gets _numBlackVictories
     * @return _numBlackVictories
     */
    public int getNumBlackVictories() {
        return _numBlackVictories;
    }

    /**
     * Gets name of white player
     * @return _whitePlayer
     */
    public String getNameWhitePlayer() {
        return _whitePlayer;
    }

    /**
     * Gets name of black player
     * @return _blackPlayer
     */
    public String getNameBlackPlayer() {
        return _blackPlayer;
    }

    public Game getGame() {
        return _game;
    }
}
