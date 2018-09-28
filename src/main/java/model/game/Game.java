package model.game;

import javafx.util.Pair;
import model.pieces.*;

import java.util.HashMap;
import java.util.Vector;

/*
 * @author Benjamin Pollak
 */
public class Game {
    private Location _boardParams;
    private Board _board;

    private PieceSpec _whitePieces;
    private PieceSpec _blackPieces;

    /** Main method. Starts a game of chess*/
    public static void main(String[] args) {
        Game game = new Game(8,8, null, null);
    }

    /**
     * Constructor for starting a game
     * @param boardWidth: how many columns the board has
     * @param boardLength: how many rows the board has
     * @param whitePieces: the type of a white piece and all occurrences
     *        of its starting place
     * @param blackPieces: the type of black piece and all occurrences
     *        of its starting place
     */
    public Game(int boardWidth, int boardLength, PieceSpec whitePieces, PieceSpec blackPieces) {
        _boardParams = new Location(boardWidth, boardLength);

        if(whitePieces != null) {
            _whitePieces = whitePieces;
        } else {
            gatherPieces(Color.WHITE);
        }

        if(blackPieces != null) {
            _blackPieces = blackPieces;
        } else {
            gatherPieces(Color.BLACK);
        }
        _board = new Board(_boardParams.getKey(), _boardParams.getValue(), _whitePieces, _blackPieces);
    }

    /**
     * Gathers all the pieces needed for one side of a traditional chess game
     * @param color: Which "color" or side to instantiate
     * @return nothing, the function is of type void
     */
    public void gatherPieces(Color color) {
        if(color == Color.WHITE) {
            _whitePieces = new PieceSpec();
            Location whitePawnSchematics[] = createPawnSchematics(Color.WHITE);
            Location whiteKnightSchematics[] = createKnightSchematics(Color.WHITE);
            Location whiteRookSchematics[] = createRookSchematics(Color.WHITE);
            Location whiteBishopSchematics[] = createBishopSchematics(Color.WHITE);
            Location whiteQueenSchematics[] = createQueenSchematics(Color.WHITE);
            Location whiteKingSchematics[] = createKingSchematics(Color.WHITE);

            _whitePieces.addElement(new Pair(PieceType.PAWN, whitePawnSchematics));
            _whitePieces.addElement(new Pair(PieceType.ROOK, whiteRookSchematics));
            _whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteKnightSchematics));
            _whitePieces.addElement(new Pair(PieceType.BISHOP, whiteBishopSchematics));
            _whitePieces.addElement(new Pair(PieceType.QUEEN, whiteQueenSchematics));
            _whitePieces.addElement(new Pair(PieceType.KING, whiteKingSchematics));
        }

        else if(color == Color.BLACK) {
            _blackPieces = new PieceSpec();
            Location blackPawnSchematics[] = createPawnSchematics(Color.BLACK);
            Location blackKnightSchematics[] = createKnightSchematics(Color.BLACK);
            Location blackRookSchematics[] = createRookSchematics(Color.BLACK);
            Location blackBishopSchematics[] = createBishopSchematics(Color.BLACK);
            Location blackQueenSchematics[] = createQueenSchematics(Color.BLACK);
            Location blackKingSchematics[] = createKingSchematics(Color.BLACK);

            _blackPieces.addElement(new Pair(PieceType.PAWN, blackPawnSchematics));
            _blackPieces.addElement(new Pair(PieceType.ROOK, blackRookSchematics));
            _blackPieces.addElement(new Pair(PieceType.KNIGHT, blackKnightSchematics));
            _blackPieces.addElement(new Pair(PieceType.BISHOP, blackBishopSchematics));
            _blackPieces.addElement(new Pair(PieceType.QUEEN, blackQueenSchematics));
            _blackPieces.addElement(new Pair(PieceType.KING, blackKingSchematics));
        }
    }

    /**
     * Generates pawn schematics
     * @param color: "color" or side of the pawn(s)
     * @return an array of locations for pawns
     */
    public Location[] createPawnSchematics(Color color) {
        if(color == Color.WHITE) {
            Location pawns[] = new Location[8];
            for (int pawnXCoord = 0; pawnXCoord < 8; ++pawnXCoord) {
                pawns[pawnXCoord] = new Location(pawnXCoord, _boardParams.getValue() - 2);
            }
            return pawns;
        }
        else { // color == model.pieces.Color.BLACK
            Location pawns[] = new Location[8];
            for (int pawnXCoord = 0; pawnXCoord < 8; ++pawnXCoord) {
                pawns[pawnXCoord] = new Location(pawnXCoord,1);
            }
            return pawns;
        }
    }

    /**
     * Generates rook schematics
     * @param color: "color" or side of the rook(s)
     * @return an array of locations for rook
     */
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

    /**
     * Generates knight schematics
     * @param color: "color" or side of the knight(s)
     * @return an array of locations for knight
     */
    public Location[] createKnightSchematics(Color color) {
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

    /**
     * Generates bishop schematics
     * @param color: "color" or side of the bishop(s)
     * @return an array of locations for bishop
     */
    public Location[] createBishopSchematics(Color color) {
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

    /**
     * Generates queen schematics
     * @param color: "color" or side of the queen(s)
     * @return an array of locations for queen
     */
    public Location[] createQueenSchematics(Color color) {
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

    /**
     * Generates king schematics
     * @param color: "color" or side of the king(s)
     * @return an array of locations for king
     */
    public Location[] createKingSchematics(Color color) {
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

    /**
     * Detects if model.pieces.Color color is in checkmate
     * @param board: Where the game is being played
     * @param color: the color that may be in checkmate
     * @param e: the model.game.KingInCheck exception that was thrown, triggering this function call
     * @return true if the color is in checkmate, false otherwise
     */
    public static boolean detectCheckmate(Board board, Color color, KingInCheck e) {
        HashMap<PieceType, Vector<Piece>> atkPieces;
        HashMap<PieceType, Vector<Piece>> defPieces;
        King threatenedKing;
        if(color == Color.WHITE) {
            atkPieces = board.getBlackPieces();
            defPieces = board.getWhitePieces();
        }
        else {
            atkPieces = board.getWhitePieces();
            defPieces = board.getBlackPieces();
        }

        Vector kingArr = defPieces.get(PieceType.KING);
        threatenedKing = (King) kingArr.elementAt(0);
        boolean canMoveKing = lookForValidKingMoves(board, threatenedKing, atkPieces);
        if(canMoveKing) return false;

        // try moving literally every other piece to defend king
        Direction direction = e.getDirectionOfThreat();
        Location atkLocation = e.getAtkLocation();
        boolean defenseExists = searchForDefense(direction, board, defPieces, threatenedKing.getLocation(), atkLocation);
        return !defenseExists;
    }

    /**
     * Looks at pieces for a defense against checkmate
     * @param direction: The direction in which to look for defenses
     * @param board: The board on which to look
     * @param defensePieces: The pieces for which to look
     * @param kingLoc: model.game.Location of king
     * @param attackerLoc: location of attacker
     * @return true if a defense exists, false otherwise
     */
    public static boolean searchForDefense(Direction direction, Board board, HashMap<PieceType, Vector<Piece>> defensePieces, Location kingLoc, Location attackerLoc) {
        int kingX = kingLoc.getKey(); int kingY = kingLoc.getValue();
        int atkX = attackerLoc.getKey(); int atkY = attackerLoc.getValue();

        int startX = (atkX < kingX) ? atkX : kingX;
        int endX = (kingX < atkX) ? atkX : kingX;

        int startY = (atkY < kingY) ? atkY : kingY;
        int endY = (kingY < atkY) ? kingY : atkY;

        if((direction == Direction.WEST) || (direction == Direction.EAST)) {
            int i;
            for(i = startX + 1; i <= endX; ++i) {
                boolean moveExists = canMovePieceTo(i, kingLoc.getValue(),  board, defensePieces);
                if(moveExists) return true;
            }
        }
        else if((direction == Direction.NORTH) || (direction == Direction.SOUTH)) {
            for(int i = startY; i < endY; ++i) {
                boolean moveExists = canMovePieceTo(i, kingLoc.getValue(),  board, defensePieces);
                if(moveExists) return true;
            }
        }
        return false;
    }

    /**
     * Check if a given piece can be moved to a specified location. Wraps  checkValidMove(), essentially
     * @param xCoord: horizontal target for piece
     * @param yCoord: vertical target for piece
     * @param board: board on which to move piece
     * @param pieceSet: piece set to check over
     */
    public static boolean canMovePieceTo(int xCoord, int yCoord, Board board, HashMap<PieceType, Vector<Piece>> pieceSet) {
        Vector<Piece> pawns = pieceSet.get(PieceType.PAWN);
        Vector<Piece> queens = pieceSet.get(PieceType.QUEEN);
        Vector<Piece> bishops = pieceSet.get(PieceType.BISHOP);
        Vector<Piece> knights = pieceSet.get(PieceType.KNIGHT);
        Vector<Piece> rooks = pieceSet.get(PieceType.ROOK);

        if(pawns != null) {
            for (Piece p : pawns) {
                Pawn pawn = (Pawn) p;
                try {pawn.checkValidMove(xCoord, yCoord, board.getField());}
                catch (IllegalArgumentException e) {return false;}
                return true;
            }
        }

        if(queens != null) {
            for (Piece q : queens) {
                Queen queen = (Queen) q;
                try {queen.checkValidMove(xCoord, yCoord, board.getField()); }
                catch (IllegalArgumentException e) {return false;}
                return true;
            }
        }

        if(bishops != null) {
            for (Piece b : bishops) {
                Bishop bishop = (Bishop) b;
                try {boolean canMove = bishop.checkValidMove(xCoord, yCoord, board.getField()); }
                catch (IllegalArgumentException e) {return false;}
                return true;
            }
        }

        if(rooks != null) {
            for (Piece r : rooks) {
                Rook rook = (Rook) r;
                boolean canMove = false;
                try {canMove = rook.checkValidMove(xCoord, yCoord, board.getField()); }
                catch (IllegalArgumentException e) {return false;}
                return true;
            }
        }

        if(knights != null) {
            for (Piece k : knights) {
                Knight knight = (Knight) k;
                boolean canMove = knight.checkValidMove(xCoord, yCoord, board.getField());
                if (canMove) return true;
            }
        }

        return false;
    }

    /**
     * Looks for ways to move the king
     * @param board: where the game is being played
     * @param threatenedKing: the king in check
     * @param atkPieces: pieces that can attack the threatened king
     * @return true if there are moves threatenedKing can legally make
     */
    public static boolean lookForValidKingMoves(Board board, King threatenedKing, HashMap<PieceType, Vector<Piece>> atkPieces) {
        int oldX = threatenedKing.getLocation().getKey();
        int oldY = threatenedKing.getLocation().getValue();
        Piece[][] field = board.getField();

        // east
        try {
            threatenedKing.checkCoordinates(oldX-1, oldY, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX - 1, oldY, field);
        } catch (IllegalArgumentException e) {};
        // west
        try {
            threatenedKing.checkCoordinates(oldX+1, oldY, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX + 1, oldY, field);
        } catch (IllegalArgumentException e) {};
        // north
        try {
            threatenedKing.checkCoordinates(oldX, oldY-1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX, oldY - 1, field);
        } catch (IllegalArgumentException e) {};
        // south
        try {
            threatenedKing.checkCoordinates(oldX, oldY+1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX, oldY + 1, field);
        } catch (IllegalArgumentException e) {};
        // northeast
        try {
            threatenedKing.checkCoordinates(oldX+1, oldY-1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX + 1, oldY - 1, field);
        } catch (IllegalArgumentException e) {};
        // northwest
        try {
            threatenedKing.checkCoordinates(oldX-1, oldY-1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX - 1, oldY - 1, field);
        } catch (IllegalArgumentException e) {};
        // southeast
        try {
            threatenedKing.checkCoordinates(oldX+1, oldY+1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX + 1, oldY + 1, field);
        } catch (IllegalArgumentException e) {};
        // southwest
        try {
            threatenedKing.checkCoordinates(oldX-1, oldY+1, board.getBoardWidth(), board.getBoardLength());
            threatenedKing.checkValidMove(oldX - 1, oldY + 1, field);
        } catch (IllegalArgumentException e) {};

        return false;
    }

    /** 
     * Retrieves piece at given x and y coord
     * @param xCoord x position to get piece from
     * @param yCoord y position to get piece from
     */
    public Piece retrievePiece(int xCoord, int yCoord) {
        return _board.retrievePiece(new Location(xCoord, yCoord));
    }

    /**
     * Get _whitePieces member variable
     */
    public PieceSpec getWhitePieces() {
        return _whitePieces;
    }

    /**
     * Get _blackPieces member variable
     */
    public PieceSpec getBlackPieces() {
        return _blackPieces;
    }

    /**
     * Get _board member variable
     */
    public Board getBoard() {
        return _board;
    }

}
