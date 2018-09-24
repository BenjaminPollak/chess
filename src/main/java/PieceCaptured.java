public class PieceCaptured extends RuntimeException  {
    private Color _color; // color of piece captured
    private Location _location; // location where piece captured

    public PieceCaptured(Color color, int xCoord, int yCoord) {
        super("Captured piece");
        _color = color;
        _location = new Location(xCoord, yCoord);
    }

    public Color getColor() {
        return _color;
    }

    public Location getLocation() {
        return _location;
    }
}
