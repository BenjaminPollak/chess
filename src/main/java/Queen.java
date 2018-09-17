import static java.lang.Math.abs;

public class Queen extends Piece{
    public Queen(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.QUEEN, color);
    }

    MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
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
            return MoveType.ATTACK;
        }
        else return MoveType.MOVE;
    }

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
            // TODO: diagonal attacks?
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
