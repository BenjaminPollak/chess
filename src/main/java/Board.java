import javafx.util.Pair;

import javax.swing.*;
import java.util.HashMap;
import java.util.Vector;

public class Board extends JFrame{
    // NOTE: top left corner is (0, 0).
    //       bottom right corner is (_boardWidth - 1, _boardLength - 1)

    // member variables
    private int _boardWidth;
    private int _boardLength;
    private Piece[][] _field;
    private Location _boardParams;

    private HashMap<PieceType, Vector<Piece>> _whitePieces;
    private HashMap<PieceType, Vector<Piece>> _blackPieces;

    public Board(int boardWidth, int boardLength, Pair<PieceType, Location[]> whitePieces[], Pair<PieceType, Location[]> blackPieces[]) throws IllegalArgumentException {
        if(boardWidth < 8) {
            throw new IllegalArgumentException("Cannot have board width < 8");
        }
        if(boardLength < 5) {
            throw new IllegalArgumentException("Cannot have board length < 5");
        }

        _boardWidth = boardWidth;
        _boardLength = boardLength;
        _boardParams = new Location(boardWidth, boardLength);
        _field = new Piece[_boardWidth][_boardLength];
        _whitePieces = createAndPlacePiecesOnBoard(whitePieces, Color.WHITE);
        _blackPieces = createAndPlacePiecesOnBoard(blackPieces, Color.BLACK);
    }

    /*
     * Checks if an array is needed for a dictionary
     * @param unplacedPieceType: all unplaced pieces of the same type
     * @param placedPieces: all placed pieces
     * @returns: true if a new array is needed, false otherwise
     */
    public boolean checkIfNewArrayNeeded(Pair<PieceType, Location[]> unplacedPieceType, HashMap<PieceType, Vector<Piece>> placedPieces) {
        PieceType type = unplacedPieceType.getKey();
        Location[] locations =  unplacedPieceType.getValue();
        if(!placedPieces.containsKey(unplacedPieceType)) return true;
        return false;
    }

    // TODO: potential bug here?
    /*
     * Instantiates all the pieces needed for chess
     * @param unplacedPieces: array of pairs containing pieceTypes and their locations
     * @returns a hash map containing all the pieces needed for a game of chess
     */
    public HashMap<PieceType, Vector<Piece>> createAndPlacePiecesOnBoard(Pair<PieceType, Location[]> unplacedPieces[], Color color) {
        if((_field == null) || (unplacedPieces == null)) return new HashMap<>();

        HashMap<PieceType, Vector<Piece>> placedPieces = new HashMap();

        for(Pair<PieceType, Location[]> unplacedPieceType: unplacedPieces) {
            PieceType type = unplacedPieceType.getKey();
            boolean newArrayNeeded = checkIfNewArrayNeeded(unplacedPieceType, placedPieces);
            if(newArrayNeeded) {
                placedPieces.put(type, new Vector<Piece>());
            }
            Location[] locations = unplacedPieceType.getValue();
            Vector<Piece> pieces = placedPieces.get(type);
            for(Location loc: locations) {
                int xCoord = loc.getKey();
                int yCoord = loc.getValue();
                if(_field[xCoord][yCoord] != null) throw new PositionAlreadyTakenException();

                if(type == PieceType.ROOK) {
                    Rook newPiece = new Rook(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }

                else if(type == PieceType.KNIGHT) {
                    Knight newPiece = new Knight(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }

                else if(type == PieceType.PAWN) {
                    Pawn newPiece = new Pawn(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }

                else if(type == PieceType.BISHOP) {
                    Bishop newPiece = new Bishop(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }
                else if(type == PieceType.QUEEN) {
                    Queen newPiece = new Queen(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }
                else if(type == PieceType.KING) {
                    King newPiece = new King(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }
                else if(type == PieceType.HOPPER) {
                    Hopper newPiece = new Hopper(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }
                else if(type == PieceType.FERZ) {
                    Ferz newPiece = new Ferz(loc, _boardParams, color);
                    pieces.add(newPiece);
                    _field[xCoord][yCoord] = newPiece;
                }
            }
        }

        return placedPieces;
    }

    /* TODO: test?
     * Returns the piece found at a given location. Will return null if no piece found
     * @param loc: Location from which to retrieve the piece from _field
     * @returns piece at the position given
     */
    public Piece retrievePiece(Location loc) {
        int xCoord = loc.getKey();
        int yCoord = loc.getValue();
        return _field[xCoord][yCoord];
    }

    // getters
    public HashMap<PieceType, Vector<Piece>>  getBlackPieces() {
        return _blackPieces;
    }

    public HashMap<PieceType, Vector<Piece>> getWhitePieces() {
        return _whitePieces;
    }
    public int getBoardWidth() {
        return _boardWidth;
    }

    public int getBoardLength() {
        return _boardLength;
    }

    public Piece[][] getField() {
        return _field;
    }

    // custom exception
    public class PositionAlreadyTakenException extends RuntimeException {
        public PositionAlreadyTakenException(String message) {
            super(message);
        }
        public PositionAlreadyTakenException() {
            super("Position already taken");
        }
    }
}