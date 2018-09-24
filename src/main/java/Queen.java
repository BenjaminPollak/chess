import static java.lang.Math.abs;

/**
 * @author Benjamin Pollak
 */
public class Queen extends Piece{

    /**
    * Constructor for queen
    * @param pieceLocation: place for new queen
    * @param boardParameters: size of board
    * @param color: color of queen
    */
    public Queen(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.QUEEN, color);
    }

    /**
     * Finds if king in check
     * @param field: field to look on
     * @throw KingInCheck if king in check
     */
    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        int xCoord = getLocation().getKey();
        int yCoord = getLocation().getValue();

        // rook checks
        lookUpForKing(field, xCoord, yCoord - 1);
        lookDownForKing(field, xCoord, yCoord + 1);
        lookLeftForKing(field, xCoord - 1, yCoord);
        lookRightForKing(field, xCoord + 1, yCoord);

        // bishop checks
        lookUpAndLeftForKing(field, xCoord - 1, yCoord - 1);
        lookDownAndLeftForKing(field, xCoord - 1, yCoord + 1);
        lookUpAndRightForKing(field, xCoord + 1, yCoord - 1);
        lookDownAndRightForKing(field, xCoord + 1, yCoord + 1);
    }

    /**
     *  Handles moving and attacking with the queen
     *  @param xCoord: horizontal position where piece should be moved
     *  @param yCoord: vertical position where piece should be moved
     *  @param board: board that piece should be moved on
     *  @return: the type of move performed
     */
    public MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        Location oldLocation = getLocation();
        checkCoordinates(xCoord, yCoord, board.getBoardWidth(), board.getBoardLength());
        boolean isAnAttack = checkValidMove(xCoord, yCoord, board.getField());

        setLocation(new Location(xCoord, yCoord));
        Piece[][] field = board.getField();
        if(isAnAttack) {
            int oldX = oldLocation.getKey(); int oldY = oldLocation.getValue();
            field[xCoord][yCoord] = null;
            field[xCoord][yCoord] = field[oldX][oldY];
            field[oldX][oldY] = null;

            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            else throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        else return MoveType.MOVE;
    }

    /**
     * Checks that the movement made by the queen is valid. Helper function for move
     * @param newX: the new horizontal position
     * @param newY: the new vertical position
     * @param field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @return a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove( int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        int oldX = getLocation().getKey();
        int oldY = getLocation().getValue();

        int deltaX = abs(newX - oldX);
        int deltaY = abs(newY - oldY);

        // if not one of the three cases of queen movement, throw an exception
        if((deltaX != deltaY) && (oldX != newX) && (oldY != newY)) throw new IllegalArgumentException();

        if(newX == oldX) {
            boolean iterateUp = newY > oldY;
            boolean attacking = detectObstructions(true, iterateUp, oldX, newX, field);

            if(attacking) return true;
            return false;
        }
        else if(newY == oldY) {
            boolean iterateUp = newX > oldX;
            boolean attacking = detectObstructions(false, iterateUp, oldX, newX, field);

            if(attacking) return true;
            return false;
        }
        else {
            boolean moveRight = deltaX < 0;
            boolean moveDown = deltaY < 0;
            int iterations = abs(deltaX);
            while(iterations > 0) {
                if(moveRight) ++oldX;
                else --oldX;

                if(moveDown) ++oldY;
                else --oldY;

                Piece myPiece = field[oldX][oldY];

                if(myPiece != null) throw new IllegalArgumentException();
                --iterations;
            }
            if(field[newX][newY] == null) {
                return false;
            }
            return true;
        }
    }

    /**
     * Looks for obstructions. Helper function for checkValidMove()
     * @param vertical: indicates whether or not piece moves vertically
     * @param iterateUp: indicates whether or not to add or subtract fom the iterated value
     * @param field: where the movement happens
     * @return true if there's a piece in the way, false otherwise
     */
    boolean detectObstructions(boolean vertical, boolean iterateUp, int start, int end, Piece[][] field) throws IllegalArgumentException {
        if(vertical) {
            if(iterateUp) {
                int xCoord= getLocation().getKey();
                for(int yCoord = start; yCoord < end; ++yCoord) {
                    Piece obstruction = field[xCoord][yCoord];
                    if((obstruction != null) && (obstruction != this)) throw new IllegalArgumentException();
                }
                Piece finalPiece = field[xCoord][end];
                if((finalPiece != null) && (finalPiece.getColor() == this.getColor())) throw new IllegalArgumentException();
                else if(((finalPiece != null) && (finalPiece.getColor() != this.getColor()))) return true;
                return false;
            }
            else {
                int xCoord= getLocation().getKey();
                for(int yCoord = start; yCoord > end; --yCoord) {
                    Piece obstruction = field[xCoord][yCoord];
                    if((obstruction != null) && (obstruction != this)) throw new IllegalArgumentException();
                }
                Piece finalPiece = field[xCoord][end];
                if(finalPiece != null) throw new IllegalArgumentException();
                return false;
            }
        }
        else {
            if(iterateUp) {
                int yCoord= getLocation().getValue();
                for(int xCoord = start; xCoord < end; ++xCoord) {
                    Piece obstruction = field[xCoord][yCoord];
                    if((obstruction != null) && (obstruction != this)) throw new IllegalArgumentException();
                }
                Piece finalPiece = field[end][yCoord];
                if(finalPiece != null) {
                    if(finalPiece.getColor() == getColor())
                        throw new IllegalArgumentException();
                    else
                        return true;
                }
                return false;
            }
            else {
                int yCoord= getLocation().getValue();
                for(int xCoord = start; xCoord > end; --xCoord) {
                    Piece obstruction = field[xCoord][yCoord];
                    if((obstruction != null) && (obstruction != this)) throw new IllegalArgumentException();
                }
                Piece finalPiece = field[end][yCoord];
                if(finalPiece != null) throw new IllegalArgumentException();
                return false;
            }
        }
    }
}
