import static java.lang.Math.abs;

/**
 * @author Benjamin Pollak
 */

public class Knight extends Piece {

    /**
     * Constructor for knigh
     * @param pieceLocation: location for piece
     * @param boardParameters: size of board
     * @param color: color of piece
     */
    public Knight(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.KNIGHT, color);
    }

    /**
     * finds if king is in check
     * @param field: field king is on
     * @throws KingInCheck if king is in check
     */
    @Override
    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        // TODO
        Location currentLocation = getLocation();
        int xCoord = currentLocation.getKey();
        int yCoord = currentLocation.getValue();

        try {
            Piece piece = field[xCoord - 2][yCoord - 1];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord-2, yCoord-1), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException e) {}

        try {
            Piece piece = field[xCoord - 2][yCoord + 1];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord-2, yCoord+1), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException e) {}

        try {
            Piece piece = field[xCoord + 2][yCoord - 1];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord+2, yCoord-1), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}

        try {
            Piece piece = field[xCoord + 2][yCoord + 1];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord+2, yCoord+1), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}

        try {
            Piece piece = field[xCoord - 1][yCoord - 2];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord-1, yCoord-2), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}

        try {
            Piece piece = field[xCoord - 1][yCoord + 2];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord-1, yCoord+2), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}

        try {
            Piece piece = field[xCoord + 1][yCoord - 2];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord+1, yCoord-2), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}

        try {
            Piece piece = field[xCoord + 1][yCoord + 2];
            if(piece.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(xCoord+1, yCoord+2), currentLocation);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
    }

    /**
     *  Handles moving and attacking with the knight
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
            throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        return MoveType.MOVE;
    }

    /**
     * Checks that the movement made by the knight is valid. Helper function for move()
     * @param newX: the new horizontal position
     * @param newY: the new vertical position
     * @param field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @return a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = super.getLocation();
        int oldX = oldLocation.getKey();
        int oldY = oldLocation.getValue();

        checkCoordinates(newX, newY, field.length, field[0].length);

        int differenceInX = abs(oldX - newX);
        int differenceInY = abs(oldY - newY);

        boolean validMove = false;
        if(differenceInX == 2) {
            if(differenceInY == 1) validMove = true;
        }
        else if(differenceInY == 2) {
            if(differenceInX == 1) validMove = true;
        }

        if(validMove) {
            Piece potentialVictim = field[newX][newY];
            if(potentialVictim != null) {
                if (potentialVictim.getColor() == this.getColor())
                    throw new IllegalArgumentException("ILLEGAL MOVEMENT");
                else {
                    return true;
                }
            }
            else return false;
        }
        else {
            throw new IllegalArgumentException("ILLEGAL MOVEMENT");
        }
    }
}
