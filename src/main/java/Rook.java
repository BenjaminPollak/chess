// TODO: refactor to use class Location?
public class Rook extends Piece {
    private boolean _yetToMove;
    private Location _location;

    /*
     * Rook constructor
     * @param xCoord: starting horizontal position. Starts with 0 on the left and increases to the right
     * @param yCoord: starting vertical position. Starts with 0 on the top and increases down
     * @param boardWidth: horizontal size of the board
     * @param boardLength: vertical size of the board
     */
    public Rook(Location pieceLocation, Location boardParameters, Color color) throws IllegalArgumentException {
        super(pieceLocation, boardParameters, PieceType.ROOK, color);
        _location = pieceLocation;
        _yetToMove = true;
    }

    /*
     *  Moves rook to given coordinates
     *  @param xCoord: horizontal position where piece should be moved
     *  @param yCoord: vertical position where piece should be moved
     *  @param board: board that piece should be moved on
     *  @return: the type of move performed
     *  @exception IllegalArgumentException: Thrown if the coordinates given violate a rule of chess
     */
    MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        int boardWidth = board.getBoardWidth();
        int boardLength = board.getBoardLength();
        Piece[][] field = board.getField();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        boolean isAnAttack = checkValidMove(xCoord, yCoord, field);

        if(isAnAttack) return MoveType.ATTACK;
        return MoveType.MOVE;
    }

    /*
     * Makes sure (1) no pieces are in path of movement and (2) not attacking friendly pieces
     * @param xCoord: horizontal position where piece is being moved
     * @param yCoord: vertical position where piece is being moved
     * @param board: board on which piece is being moved
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        int oldX = _location.getKey();
        int oldY = _location.getValue();

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
