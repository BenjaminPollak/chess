// TODO: refactor to use location
abstract public class Piece {
    private Location _location;

    public Piece(int x_coord, int y_coord, int board_length, int board_width) {
        checkCoordinates(x_coord, y_coord, board_width, board_length);
        _location = new Location(x_coord, y_coord);
    }

    public void checkCoordinates(int x_coord, int y_coord, int board_width, int board_length) {
        if((x_coord < 0) || (y_coord < 0)) {
            throw new IllegalArgumentException();
        }

        if((x_coord >= board_width) || (y_coord >= board_length)) {
            throw new IllegalArgumentException();
        }

    }

    abstract MoveType move(int x_coord, int y_coord, Board board);

    public Location getLocation() {
        return _location;
    }

    public void setLocation(Location newLocation) {
        _location = newLocation;
    }
}
