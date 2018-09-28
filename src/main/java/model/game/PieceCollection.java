package model.game;

import model.pieces.Piece;
import model.pieces.PieceType;

import java.util.HashMap;
import java.util.Vector;

// TODO: create empty constructor for null argument
public class PieceCollection extends HashMap<PieceType, Vector<Piece>> {
    public PieceCollection() {
        super();
    }
}