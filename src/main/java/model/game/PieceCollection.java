package model.game;

import model.pieces.Piece;
import model.pieces.PieceType;

import java.util.HashMap;
import java.util.Vector;

public class PieceCollection extends HashMap<PieceType, Vector<Piece>> {
    public PieceCollection() {
        super();
    }
}
