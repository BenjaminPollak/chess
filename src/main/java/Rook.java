public class Rook extends Piece {
    private boolean _yetToMove;

    /*
     * Rook constructor
     * @param xCoord: starting horizontal position. Starts with 0 on the left and increases to the right
     * @param yCoord: starting vertical position. Starts with 0 on the top and increases down
     * @param boardWidth: horizontal size of the board
     * @param boardLength: vertical size of the board
     */
    public Rook(Location pieceLocation, Location boardParameters, Color color) throws IllegalArgumentException {
        super(pieceLocation, boardParameters, PieceType.ROOK, color);
        _yetToMove = true;
    }

    @Override
    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        int xCoord = getLocation().getKey();
        int yCoord = getLocation().getValue();

        lookUpForKing(field, xCoord, yCoord - 1);
        lookDownForKing(field, xCoord, yCoord + 1);
        lookLeftForKing(field, xCoord - 1, yCoord);
        lookRightForKing(field, xCoord + 1, yCoord);
    }

    /*
     *  Handles moving and attacking with rooks
     *  @param xCoord: horizontal position where piece should be moved
     *  @param yCoord: vertical position where piece should be moved
     *  @param board: board that piece should be moved on
     *  @return: the type of move performed
     *  @exception IllegalArgumentException: Thrown if the coordinates given violate a rule of chess
     */
    public MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        int boardWidth = board.getBoardWidth();
        int boardLength = board.getBoardLength();
        Piece[][] field = board.getField();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        boolean isAnAttack = checkValidMove(xCoord, yCoord, field);

        _yetToMove = false;

        Location oldLocation = getLocation();
        setLocation(new Location(xCoord, yCoord));
        field[oldLocation.getKey()][oldLocation.getValue()] = null;
        field[xCoord][yCoord] = this;
        if(isAnAttack) {
            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        return MoveType.MOVE;
    }

    /*
     * Makes sure (1) no pieces are in path of movement and (2) not attacking friendly pieces. Helper
     *  function for move()
     * @param xCoord: horizontal position where piece is being moved
     * @param yCoord: vertical position where piece is being moved
     * @param board: board on which piece is being moved
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = super.getLocation();
        int oldX = oldLocation.getKey();
        int oldY = oldLocation.getValue();

        if((oldX != newX) && (oldY != newY)) throw new IllegalArgumentException();
        checkCoordinates(newX, newY, field.length, field[0].length);

        if((oldX == newX) && (oldY < newY)) { // if piece is moving down
            return checkDownwards(oldX, oldY, newX, field);
        }
        else if((oldX == newX) && (oldY > newY)) { // if piece is moving up
            return checkUpwards(oldX, oldY, newY, field);
        }
        else if((oldY == newY) && (oldX < newX)) { // if piece is moving right
            return checkRight(oldX, oldY, newX, field);
        }
        else /*if((oldY == newY) && (oldX > newX))*/ { // if piece is moving left
            return checkRight(oldX, oldY, newX, field);
        }
    }

    /*
     * Looks downwards for valid moves
     * @param int oldX: the previous horizontal position
     * @param int oldY: the previous vertical position
     * @param Piece[][] field: where the piece is being moved
     */
    public boolean checkDownwards(int oldX, int oldY, int newY, Piece[][] field) {
        for(int yCoord = oldY+1; yCoord < newY; ++yCoord) {
            if(field[oldX][yCoord] != null)
                throw new IllegalArgumentException();
        }
        if(field[oldX][newY] != null) {
            if (field[oldX][newY].getColor() == super.getColor())
                throw new IllegalArgumentException();
            else return true;
        }
        return false;
    }

    /*
     * Looks upwards for valid moves
     * @param int oldX: the previous horizontal position
     * @param int oldY: the previous vertical position
     * @param Piece[][] field: where the piece is being moved
     */
    public boolean checkUpwards(int oldX, int oldY, int newY, Piece[][] field) {
        for(int yCoord = oldY-1; yCoord > newY; --yCoord) {
            if(field[oldX][yCoord] != null)
                throw new IllegalArgumentException();
        }
        Piece pieceOfInterest = field[oldX][newY];
        if(pieceOfInterest != null) {
            if(pieceOfInterest.getColor() == super.getColor())
                throw new IllegalArgumentException();
            else return true;
        }
        return false;
    }

    /*
     * Looks right for valid moves
     * @param int oldX: the previous horizontal position
     * @param int oldY: the previous vertical position
     * @param Piece[][] field: where the piece is being moved
     */
    public boolean checkRight(int oldX, int oldY, int newX, Piece[][] field) {
        for(int xCoord = oldX+1; xCoord < newX; ++xCoord) {
            Piece pieceInTheWay = field[xCoord][oldY];
            if(pieceInTheWay != null) {
                throw new IllegalArgumentException();
            }
        }
        if(field[newX][oldY] != null) {
            if (field[newX][oldY].getColor() == super.getColor())
                throw new IllegalArgumentException();
            else return true;
        }
        return false;
    }

    /*
     * Looks left for valid moves
     * @param int oldX: the previous horizontal position
     * @param int oldY: the previous vertical position
     * @param Piece[][] field: where the piece is being moved
     */
    public boolean checkLeft(int oldX, int oldY, int newX, Piece[][] field) {
        for(int xCoord = oldX-1; xCoord > newX; --xCoord) {
            Piece pieceInTheWay = field[xCoord][oldY];
            if(pieceInTheWay != null) {
                if(pieceInTheWay.getColor() == super.getColor())
                    throw new IllegalArgumentException();
                else return true;
            }
        }
        if(field[newX][oldY] != null) {
            if (field[newX][oldY].getColor() == super.getColor())
                throw new IllegalArgumentException();
            else return true;
        }
        return false;
    }
}
