import static java.lang.Math.abs;

public class King extends Piece {
    boolean _yetToMove;

    public King(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation, boardParameters, PieceType.KING, color);
        _yetToMove = true;
    }

    MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
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
            // TODO
            return MoveType.ATTACK;
        }
        return MoveType.MOVE;
    }

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
