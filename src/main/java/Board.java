import javafx.util.Pair;

import javax.swing.*;
import java.util.HashMap;
import java.util.Vector;

public class Board extends JFrame{
    // NOTE: top left corner is (0, 0).
    //       bottom right corner is (_boardWidth - 1, _boardLength - 1)

    // useful type
    // member variables
    private int _boardWidth;
    private int _boardLength;
    private Piece[][] _field;

    private HashMap<PieceType, Vector<Piece>> _whitePieces;
    private HashMap<PieceType, Vector<Piece>> _blackPieces;

    public Board(int boardWidth, int boardLength) throws IllegalArgumentException {
        if(boardWidth < 8) {
            throw new IllegalArgumentException("Cannot have board width < 8");
        }
        if(boardLength < 5) {
            throw new IllegalArgumentException("Cannot have board length < 5");
        }

        _boardWidth = boardWidth;
        _boardLength = boardLength;
        _field = new Piece[_boardWidth][_boardLength];
        //_whitePieces = createAndPlacePiecesOnBoard(null);
        //_blackPieces = createAndPlacePiecesOnBoard(null);
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

    // TODO: i smell a bug
    /*
     * Instantiates all the pieces needed for chess
     * @param unplacedPieces: array of pairs containing pieceTypes and their locations
     * @returns a hash map containing all the pieces needed for a game of chess
     */
        public HashMap<PieceType, Vector<Piece>> createAndPlacePiecesOnBoard(Pair<PieceType, Location[]> unplacedPieces[], Piece[][] field) {
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
                    if(field[xCoord][yCoord] != null) throw new PositionAlreadyTakenException("Position already taken");

                    if(type == PieceType.ROOK) {
                        Rook newPiece = new Rook(xCoord, yCoord, _boardWidth, _boardLength);
                        pieces.add(newPiece);
                        field[xCoord][yCoord] = newPiece;
                    }
                }
            }

            return placedPieces;
    }

    // getters
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
    }
}