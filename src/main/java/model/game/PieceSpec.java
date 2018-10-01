package model.game;

import javafx.util.Pair;
import model.game.Location;
import model.pieces.PieceType;

import java.util.Vector;

/**
 * Data type used for initial instantiation of pieces
 * @author Benjamin Pollak
 */

public class PieceSpec extends Vector<Pair<PieceType, Location[]>> {
    public PieceSpec() {
        super();
    }
}