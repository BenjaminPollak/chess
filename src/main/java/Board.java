import javafx.util.Pair;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class Board extends JFrame{
    // NOTE: top left corner is (0, 0).
    //       bottom right corner is (_boardWidth - 1, _boardLength - 1)

    // useful type
    // member variables
    private int _boardWidth;
    private int _boardLength;
    private Piece[][] _field;

    private HashMap<PieceType, Piece[]> _whitePieces;
    private HashMap<PieceType, Piece[]> _blackPieces;

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
        _whitePieces = instantiatePieces(null);
        _blackPieces = instantiatePieces(null);
    }

    /*
     * Checks if an array is needed for a dictionary
     * @param
     * @returns
     */
    public void checkIfNewArrayNeeded(Pair<PieceType, Location[]> unplacedPieceType, HashMap<PieceType, Piece[]> placedPieces) {
        PieceType type = unplacedPieceType.getKey();
        Location[] locations =  unplacedPieceType.getValue();
        if(!placedPieces.containsKey(unplacedPieceType)) {
            Piece[] pieces = new Piece[locations.length];
            placedPieces.put(type, pieces);
        }
    }

    /*
     * Instantiates all the pieces needed for chess
     * @param unplacedPieces: array of pairs containing pieceTypes and their locations
     * @returns a hash map containing all the pieces needed for a game of chess
     */
    public HashMap<PieceType, Piece[]> instantiatePieces(Pair<PieceType, Location[]> unplacedPieces[]) {
        HashMap<PieceType, Piece[]> placedPieces = new HashMap();

        // TODO: place pieces
        for(Pair<PieceType, Location[]> unplacedPieceType: unplacedPieces) {
            checkIfNewArrayNeeded(unplacedPieceType, placedPieces);
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
}