package model.game;

import javafx.util.Pair;
import model.game.Location;
import model.pieces.PieceType;

import java.util.Vector;

public class PieceSpec extends Vector<Pair<PieceType, Location[]>> {
    public PieceSpec() {
        super();
    }
}