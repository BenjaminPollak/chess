// TODO: needs more testing
import static java.lang.Math.abs;

public class Pawn extends Piece {
    private boolean _yetToMove;

    public Pawn(Location pieceLocation, Location boardParameters, Color color) throws IllegalArgumentException {
        super(pieceLocation, boardParameters, PieceType.PAWN, color);
        _yetToMove = true;
    }

    MoveType move(int xCoord, int yCoord, Board board) {
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
        return true;    }

    public void setYetToMove(boolean yetToMove) {
        _yetToMove = yetToMove;
    }

    public boolean getYetToMove() {
        return _yetToMove;
    }

}
