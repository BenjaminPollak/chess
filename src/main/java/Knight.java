import static java.lang.Math.abs;

public class Knight extends Piece {

    public Knight(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.KNIGHT, color);
    }

    MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException{
        // TODO: update coordinates, NULL old location of this
        Location oldLocation = super.getLocation();
        int boardWidth = board.getBoardWidth();
        int boardLength = board.getBoardLength();
        Piece[][] field = board.getField();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        boolean isAnAttack = checkValidMove(xCoord, yCoord, field);

        field[xCoord][yCoord] = this;
        field[oldLocation.getKey()][oldLocation.getValue()] = null;

        if(isAnAttack) return MoveType.ATTACK;
        return MoveType.MOVE;
    }

    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException{
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
