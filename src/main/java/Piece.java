abstract public class Piece {
    private Location _location;
    private PieceType _pieceType;
    private Color _color;

    public Piece(Location pieceLocation, Location boardParameters, PieceType pieceType, Color color) {
        int xCoord = pieceLocation.getKey();
        int yCoord = pieceLocation.getValue();

        int boardWidth = boardParameters.getKey();
        int boardLength = boardParameters.getValue();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        _location = new Location(xCoord, yCoord);
        _pieceType = pieceType;
        _color = color;
    }

    /*
     *  makes sure coordinates are "in-bounds"
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

    public abstract MoveType move(int xCoord, int yCoord, Board board);
    public abstract void findIfKingInCheck(Piece[][] field) throws KingInCheck;

    // getters and setter

    public Color getColor() {
        return _color;
    }

    public Location getLocation() {
        return _location;
    }

    public PieceType getPieceType() {
        return _pieceType;
    }


    public void setLocation(Location newLocation) {
        _location = newLocation;
    }

    // custom exception
    public class KingInCheck extends RuntimeException {
        public KingInCheck(String message) {
            super(message);
        }
        public KingInCheck() {
            super("KING IN CHECK");
        }
    }
}
