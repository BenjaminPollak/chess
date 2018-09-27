package model.game;

import model.game.Location;
import model.pieces.Color;

public class PieceCaptured extends RuntimeException  {
    private Color _color; // color of piece captured
    private Location _location; // location where piece captured

    /**
     * constructor for model.game.PieceCaptured exception
     * @param color: color of piece captured
     * @param xCoord: new horizontal position of piece
     * @param yCoord: new vertical position of piece
     */
    public PieceCaptured(Color color, int xCoord, int yCoord) {
        super("Captured piece");
        _color = color;
        _location = new Location(xCoord, yCoord);
    }

    /**
     * Gets _color member variable
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Gets _location member variable
     */
    public Location getLocation() {
        return _location;
    }
}
