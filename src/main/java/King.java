import static java.lang.Math.abs;

/**
 * @author Benjamin Pollak
 */
public class King extends Piece {
    boolean _yetToMove;

    /**
     * Constructor for king
     * pieceLocation: location for king
     * boardParameters: size of board
     * color: color of new piece
     */
    public King(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation, boardParameters, PieceType.KING, color);
        _yetToMove = true;
    }

    @Override
    /**
     * Would check to see if the opposing king is in check, but does
     * nothing because kings can't check
     */
    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        // doesnt do anything, a king cannot check a king
    }

    /**
     *  Handles moving and attacking with the king
     *  @param xCoord: horizontal position where piece should be moved
     *  @param yCoord: vertical position where piece should be moved
     *  @param board: board that piece should be moved on
     *  @return: the type of move performed
     */
    public MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        Location oldLocation = super.getLocation();
        int boardWidth = board.getBoardWidth();
        int boardLength = board.getBoardLength();
        Piece[][] field = board.getField();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        boolean isAnAttack = checkValidMove(xCoord, yCoord, field);


        field[xCoord][yCoord] = this;
        field[oldLocation.getKey()][oldLocation.getValue()] = null;
        setLocation(new Location(xCoord, yCoord));

        if(isAnAttack) {
            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            else throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        return MoveType.MOVE;
    }

    /**
     * Checks that the movement made by the king is valid. Helper function for move()
     * @param int newX: the new horizontal position
     * @param int newY: the new vertical position
     * @param Piece[][] field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @return a boolean describing whether or not a piece is taken
     */
    Boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = super.getLocation();
        int oldX = oldLocation.getKey(); int oldY = oldLocation.getValue();

        int deltaX = newX - oldX;
        int deltaY = newY - oldY;

        if((abs(deltaX) > 1) || (abs(deltaY) > 1)) throw new IllegalArgumentException();

        Piece pieceInTheWay = field[newX][newY];
        if(pieceInTheWay != null) {
            if(pieceInTheWay.getColor() == this.getColor())
                throw new IllegalArgumentException();
            else return true;
        }
        else return false;
    }
}
