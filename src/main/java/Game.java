import javafx.util.Pair;

public class Game {
    Location _boardParams;
    Board _board;

    private Pair<PieceType, Location[]> _whitePieces[];
    private Pair<PieceType, Location[]> _blackPieces[];

    public static void main(String[] args) {
        Pair<PieceType, Location[]>[] whitePieces = new Pair [6];

        Pair<PieceType, Location[]> pawns = new Pair(PieceType.PAWN, null);


        //Game game = new Game(8,8, whitePieces, blackPieces);
    }

    public Game(int boardWidth, int boardLength, Pair<PieceType, Location[]> whitePieces[],Pair<PieceType, Location[]> blackPieces[]) {
        _whitePieces = whitePieces;
        _blackPieces = whitePieces;
        _boardParams = new Location(boardWidth, boardLength);
        _board = new Board(_boardParams.getKey(), _boardParams.getValue(), _whitePieces, _blackPieces);
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
