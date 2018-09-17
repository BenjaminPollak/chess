// TODO: are pieces moved around board correctly?
import static java.lang.Math.abs;

public class Bishop extends Piece {
    public Bishop(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.BISHOP, color);
    }
    MoveType move(int xCoord, int yCoord, Board board) {
        Location oldLocation = getLocation();
        checkCoordinates(xCoord, yCoord, board.getBoardWidth(), board.getBoardLength());
        boolean isAnAttack = checkValidMove(xCoord, yCoord, board.getField());

        Piece[][] field = board.getField();
        field[xCoord][yCoord] = this;
        field[oldLocation.getKey()][oldLocation.getValue()] = null;
        setLocation(new Location(xCoord, yCoord));

        if(isAnAttack) return MoveType.ATTACK;
        else return MoveType.MOVE;
    }

    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException{
        Location oldLocation = super.getLocation();
        int oldX = oldLocation.getKey();
        int oldY = oldLocation.getValue();

        checkCoordinates(newX, newY, field.length, field[0].length);


        int deltaX = oldX - newX;
        int deltaY = oldY - newY;

        if(abs(deltaX) == abs(deltaY)) {
            boolean moveRight = deltaX < 0;
            boolean moveDown = deltaY < 0;

            int iterations = abs(deltaX);
            while(iterations > 1) {
                if(moveRight) ++oldX;
                else --oldX;

                if(moveDown) ++oldY;
                else --oldY;

                Piece impediment = field[oldX][oldY];

                if(impediment != null) throw new IllegalArgumentException();
                --iterations;
            }
            Piece impediment = field[newX][newY];
            if(impediment != null) {
                if(impediment.getColor() != getColor()) return true;
                else throw new IllegalArgumentException();
            }
            return false;
        }
        else throw new IllegalArgumentException();
    }
}
