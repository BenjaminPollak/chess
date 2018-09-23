import static java.lang.Math.abs;

public class Pawn extends Piece {
    private boolean _yetToMove;

    /*
     * Creates a pawn.
     * @param Location pieceLocation: Where the piece is to be placed
     * @param Location boardParameters: Size of the board
     * @param Color color: The color or "side" of the piece
     * @throws IllegalArgumentException: where a piece would be placed "out of bounds'
     */
    public Pawn(Location pieceLocation, Location boardParameters, Color color) throws IllegalArgumentException {
        super(pieceLocation, boardParameters, PieceType.PAWN, color);
        _yetToMove = true;
    }

    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        int xCoord = getLocation().getKey();
        int yCoord = getLocation().getValue();

        if(getColor() == Color.WHITE) {
            Piece potentialKing;
            try {
                potentialKing = field[xCoord + 1][yCoord - 1];
                if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck();
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {/*do nothing*/}
            try {
                potentialKing = field[xCoord - 1][yCoord - 1];
                if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck();
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {/*do nothing*/}
        }
        else {
            Piece potentialKing;
            try {
                potentialKing = field[xCoord + 1][yCoord + 1];
                if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck();
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {/*do nothing*/}
            try {
                potentialKing = field[xCoord - 1][yCoord + 1];
                if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck();
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {/*do nothing*/}
        }
    }

    /*
     * Handles moving and attacking with pawns
     * @param int xCord: where the piece is to be moved horizontally
     * @param int yCord: where the piece is to be moved vertically
     * @param Board board: the space on which the piece is to be moved
     * @returns the MoveType, an enum describing what the move did
     */
    public MoveType move(int xCoord, int yCoord, Board board) {
        Piece[][] field = board.getField();
        Location oldLocation = getLocation();
        checkCoordinates(xCoord, yCoord, board.getBoardWidth(), board.getBoardLength());

        boolean attacking = checkValidMove(xCoord, yCoord, board.getField());
        field[xCoord][yCoord] = null;
        field[xCoord][yCoord] = this;
        field[oldLocation.getKey()][oldLocation.getValue()] = null;
        setLocation(new Location(xCoord, yCoord));

        if(attacking) {
            return MoveType.ATTACK;
        }
        else return  MoveType.MOVE;
    }

    /*
     * Checks that the movement made by the pawn is valid. Helper function for move()
     * @param int newX: the new horizontal position
     * @param int newY: the new vertical position
     * @param Piece[][] field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @returns a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldPosition = getLocation();
        int oldX = oldPosition.getKey();
        int oldY = oldPosition.getValue();
        int deltaX = abs(oldX - newX);
        int deltaY = abs(oldY - newY);

        if(getColor() == Color.WHITE) {
            return checkWhiteMove(newX, newY, field);
        }
        else { // color is BLACK
            return checkBlackMove(newX, newY, field);
        }
    }

    /*
     * Checks that a white pawn's move is valid
     * @param int newX: the new horizontal position
     * @param int newY: the new vertical position
     * @param Piece[][] field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     */
    public boolean checkWhiteMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = this.getLocation();
        int deltaX = oldLocation.getKey() - newX;
        int deltaY = oldLocation.getValue() - newY;

        if(deltaX == 0) {
            if(deltaY == 1) {
                Piece obstruction = field[newX][newY];
                if(obstruction != null) throw new IllegalArgumentException();
                else return false;
            }
            else if(deltaY == 2) {
                Piece firstObstruction = field[newX][newY-1];
                Piece secondObstruction = field[newX][newY];
                if((firstObstruction != null) || (secondObstruction != null))
                    throw new IllegalArgumentException();
                else return false;
            }
        }
        else if (abs(deltaX) == 1) {
            if(deltaY == 1) return true;
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();
        return true;
    }

    /*
     * Checks that a white pawn's move is valid
     * @param int newX: the new horizontal position
     * @param int newY: the new vertical position
     * @param Piece[][] field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     */
    public boolean checkBlackMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = this.getLocation();
        int deltaX = oldLocation.getKey() - newX;
        int deltaY = oldLocation.getValue() - newY;

        if(deltaX == 0) {
            if(deltaY == -1) {
                Piece obstruction = field[newX][newY];
                if(obstruction != null) throw new IllegalArgumentException();
                else return false;
            }
            else if(deltaY == -2) {
                Piece firstObstruction = field[newX][newY+1];
                Piece secondObstruction = field[newX][newY];
                if((firstObstruction != null) || (secondObstruction != null))
                    throw new IllegalArgumentException();
                else return false;
            }
        }
        else if (abs(deltaX) == 1) {
            if(deltaY == -1) return true;
            else throw new IllegalArgumentException();        }
        else throw new IllegalArgumentException();
        return true;
    }


    /*
     * setter for the _yetToMove variable
     */
    public void setYetToMove(boolean yetToMove) {
        _yetToMove = yetToMove;
    }

    /*
     * getter for the _yetToMove variable
     */
    public boolean getYetToMove() {
        return _yetToMove;
    }

}
