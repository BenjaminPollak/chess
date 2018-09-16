// TODO: refactor to use location
abstract public class Piece {
    private Location _location;

    private PieceType _pieceType;

    public Piece(Location pieceLocation, Location boardParameters, PieceType pieceType, Color color) {
        int xCoord = pieceLocation.getKey();
        int yCoord = pieceLocation.getValue();

        int boardWidth = boardParameters.getKey();
        int boardLength = boardParameters.getValue();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        _location = new Location(xCoord, yCoord);
        _pieceType = pieceType;
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
            throw new IllegalArgumentException("OUT OF BOUNDS");
        }

        if((xCoord >= boardWidth) || (yCoord >= boardLength)) {
            throw new IllegalArgumentException("OUT OF BOUNDS");
        }

    }

    abstract MoveType move(int xCoord, int yCoord, Board board);

    // getters and setter

    public Location getLocation() {
        return _location;
    }

    public PieceType getPieceType() {
        return _pieceType;
    }


    public void setLocation(Location newLocation) {
        _location = newLocation;
    }
}
