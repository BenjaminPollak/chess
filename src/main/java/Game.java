import javafx.util.Pair;

public class Game {
    Location _boardParams;
    Board _board;

    private Pair<PieceType, Location[]> _whitePieces[];
    private Pair<PieceType, Location[]> _blackPieces[];

    public static void main(String[] args) {
        Game game = new Game(8,8, null, null);
    }

    public Game(int boardWidth, int boardLength, Pair<PieceType, Location[]> whitePieces[],Pair<PieceType, Location[]> blackPieces[]) {
        if(whitePieces == null) gatherPieces(Color.WHITE);
        if(blackPieces == null) gatherPieces(Color.BLACK);

        _boardParams = new Location(boardWidth, boardLength);
        _board = new Board(_boardParams.getKey(), _boardParams.getValue(), _whitePieces, _blackPieces);
    }

    // TODO: how do I test this?
    public void gatherPieces(Color color) {
        if(color == Color.WHITE) {
            _whitePieces = new Pair[6];
            Location whitePawnSchematics[] = createPawnSchematics(Color.WHITE);
            Location whiteKnightSchematics[] = createKnightSchematics(Color.WHITE);
            Location whiteRookSchematics[] = createRookSchematics(Color.WHITE);
            Location whiteBishopSchematics[] = createBishopSchematics(Color.WHITE);
            Location whiteQueenSchematics[] = createQueenSchematics(Color.WHITE);
            Location whiteKingSchematics[] = createKingSchematics(Color.WHITE);

            _whitePieces[0] = new Pair(PieceType.PAWN, whitePawnSchematics);
            _whitePieces[1] = new Pair(PieceType.ROOK, whiteRookSchematics);
            _whitePieces[2] = new Pair(PieceType.KNIGHT, whiteKnightSchematics);
            _whitePieces[3] = new Pair(PieceType.BISHOP, whiteBishopSchematics);
            _whitePieces[4] = new Pair(PieceType.QUEEN, whiteQueenSchematics);
            _whitePieces[5] = new Pair(PieceType.KING, whiteKingSchematics);
        }

        else if(color == Color.BLACK) {
            _blackPieces = new Pair[6];
            Location blackPawnSchematics[] = createPawnSchematics(Color.BLACK);
            Location blackKnightSchematics[] = createKnightSchematics(Color.BLACK);
            Location blackRookSchematics[] = createRookSchematics(Color.BLACK);
            Location blackBishopSchematics[] = createBishopSchematics(Color.BLACK);
            Location blackQueenSchematics[] = createQueenSchematics(Color.BLACK);
            Location blackKingSchematics[] = createKingSchematics(Color.BLACK);

            _blackPieces[0] = new Pair(PieceType.PAWN, blackPawnSchematics);
            _blackPieces[1] = new Pair(PieceType.ROOK, blackRookSchematics);
            _blackPieces[2] = new Pair(PieceType.KNIGHT, blackKnightSchematics);
            _blackPieces[3] = new Pair(PieceType.BISHOP, blackBishopSchematics);
            _blackPieces[4] = new Pair(PieceType.QUEEN, blackQueenSchematics);
            _whitePieces[5] = new Pair(PieceType.KING, blackKingSchematics);
        }
    }

    public Location[] createPawnSchematics(Color color) {
        if(color == Color.WHITE) {
            Location pawns[] = new Location[8];
            for (int pawnXCoord = 0; pawnXCoord < 8; ++pawnXCoord) {
                pawns[pawnXCoord] = new Location(pawnXCoord, _boardParams.getValue() - 2);
            }
            return pawns;
        }
        else { // color == Color.BLACK
            Location pawns[] = new Location[8];
            for (int pawnXCoord = 0; pawnXCoord < 8; ++pawnXCoord) {
                pawns[pawnXCoord] = new Location(pawnXCoord,1);
            }
            return pawns;
        }
    }

    public Location[] createRookSchematics(Color color) {
        int boardWidth = _boardParams.getKey();
        int boardLength = _boardParams.getValue();
        if(color == Color.WHITE) {
            Location leftRook = new Location(0, boardLength - 1);
            Location rightRook = new Location(boardWidth - 1, boardLength - 1);
            return new Location[] {leftRook, rightRook};
        }
        else {
            Location leftRook = new Location(0, 0);
            Location rightRook = new Location(7, 0);
            return new Location[] {leftRook, rightRook};
        }
    }

    public Location[] createKnightSchematics(Color color) {
        // TODO
        if(color == Color.WHITE) {
            int boardLength = _boardParams.getKey();
            Location leftKnight = new Location(1, boardLength -1);
            Location rightKnight = new Location(6, boardLength -1);
            return new Location[] {leftKnight, rightKnight};
        }
        else {
            Location leftKnight = new Location(1, 0);
            Location rightKnight = new Location(6, 0);
            return new Location[] {leftKnight, rightKnight};
        }
    }

    public Location[] createBishopSchematics(Color color) {
        // TODO
        Location[] bishopSchematics = new Location[2];
        if(color == Color.WHITE) {
            int boardLength = _boardParams.getValue();
            Location leftBishop = new Location( 2, boardLength - 1);
            Location rightBishop = new Location(5, boardLength - 1);
            bishopSchematics[0] = leftBishop;
            bishopSchematics[1] = rightBishop;
            return bishopSchematics;
        }
        else {
            int boardWidth = _boardParams.getKey();
            Location leftBishop = new Location(2, 0);
            Location rightBishop = new Location(0, 5);
            bishopSchematics[0] = leftBishop;
            bishopSchematics[1] = rightBishop;
            return bishopSchematics;
        }
    }

    public Location[] createQueenSchematics(Color color) {
        // TODO
        if(color == Color.WHITE) {
            int boardLength = _boardParams.getValue();
            Location queenLoc = new Location(3, boardLength - 1);
            return new Location[]{queenLoc};
        }
        else {
            Location queenLoc = new Location(3, 0);
            return new Location[]{queenLoc};
        }
    }

    public Location[] createKingSchematics(Color color) {
        // TODO
        if(color == Color.WHITE) {
            int boardLength = _boardParams.getKey();
            Location kingLoc = new Location(4, boardLength - 1);
            return new Location[]{kingLoc};
        }
        else {
            Location kingLoc = new Location(4, 0);
            return new Location[]{kingLoc};
        }
    }
}
