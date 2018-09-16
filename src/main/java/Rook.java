// TODO: refactor to use location
public class Rook extends Piece {
    private boolean _yetToMove;

    public Rook(int xCoord, int yCoord, int boardWidth, int boardLength) throws IllegalArgumentException {
        super(xCoord, yCoord, boardWidth, boardLength);
         super.setLocation(new Location(xCoord, yCoord));
        _yetToMove = true;
    }

    MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        int boardWidth = board.getBoardWidth();
        int boardLength = board.getBoardLength();

        if((xCoord != boardWidth) && (yCoord != boardLength)) throw new IllegalArgumentException();
        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        // TODO: check nothing in the way
        return MoveType.MOVE;
    }

    public void checkValidMove(int xCoord, int yCoord, Piece[][] field) {
        // TODO
    }
}
