// TODO: refactor to use location
abstract public class Piece {
    private Location _location;

    public Piece(int xCoord, int yCoord, int boardLength, int boardWidth) {
        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        _location = new Location(xCoord, yCoord);
    }

    /*
     *  Called by constructor to make sure coordinates are "in-bounds"
     *  @param xCoord: desired horizontal location of piece
     *  @param yCoord: desired vertical location of piece
     *  @param boardWidth: Horizontal size of board
     *  @param boardlength: Vertical size of board
     */

    public void checkCoordinates(int xCoord, int yCoord, int boardWidth, int boardLength) {
        if((xCoord < 0) || (yCoord < 0)) {
            throw new IllegalArgumentException();
        }

        if((xCoord >= boardWidth) || (yCoord >= boardLength)) {
            throw new IllegalArgumentException();
        }

    }

    abstract MoveType move(int xCoord, int yCoord, Board board);

    // getter and setter

    public Location getLocation() {
        return _location;
    }

    public void setLocation(Location newLocation) {
        _location = newLocation;
    }
}
