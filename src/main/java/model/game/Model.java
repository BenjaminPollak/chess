package model.game;

public class Model {
    private int _numWhiteVictories = 0;
    private int _numBlackVictories = 0;

    String _whitePlayer;
    String _blackPlayer;

    int _bRows;
    int _bCols;

    Game _game;

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

    public int getNumWhiteVictories() {
        return _numWhiteVictories;
    }

    public int getNumBlackVictories() {
        return _numBlackVictories;
    }

    public String getNameWhitePlayer() {
        return _whitePlayer;
    }

    public String getNameBlackPlayer() {
        return _blackPlayer;
    }

    public Game getGame() {
        return _game;
    }
}
