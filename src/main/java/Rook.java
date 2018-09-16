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
    public Rook(int xCoord, int yCoord, int boardWidth, int boardLength) throws IllegalArgumentException {
        super(xCoord, yCoord, boardWidth, boardLength);
        super.setLocation(new Location(xCoord, yCoord));
        _yetToMove = true;
        _location = super.getLocation();
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

        if((xCoord != boardWidth) && (yCoord != boardLength)) throw new IllegalArgumentException();
        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        checkValidMove(xCoord, yCoord, field);
        return MoveType.MOVE;
    }

    /*
     * Makes sure (1) no pieces are in path of movement and (2) not attacking friendly pieces
     * @param xCoord: horizontal position where piece is being moved
     * @param yCoord: vertical position where piece is being moved
     * @param board: board on which piece is being moved
     */
    public void checkValidMove(int newX, int newY, Piece[][] field) {
        int oldX = _location.getKey();
        int oldY = _location.getValue();
        if(oldX == newX) {
            // TODO: look along y-axis
        }
        else { // oldY == newY
            // TODO: look along x-axis
        }
    }
}
