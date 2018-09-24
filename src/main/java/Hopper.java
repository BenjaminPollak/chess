import static java.lang.Math.abs;

public class Hopper extends Piece {
    /*
     * A hopper can move two spaces north, south, east, or west. It can jump
     * pieces, but only attacks when it lands on an enemy piece
     */
    Hopper(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation, boardParameters, PieceType.HOPPER, color);
    }

    public MoveType move(int xCoord, int yCoord, Board board) {
        boolean captureAttempted = checkValidMove(xCoord, yCoord, board.getField());
        if(captureAttempted) {
            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            else throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        return MoveType.MOVE;
    }

    /*
     * Checks that the movement made by the bishop is valid. Helper function for move()
     * @param int newX: the new horizontal position
     * @param int newY: the new vertical position
     * @param Piece[][] field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @returns a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException{
        int oldX = getLocation().getKey();
        int oldY = getLocation().getValue();

        int deltaX = oldX - newX;
        int deltaY = newY - oldY;

        if((abs(deltaX) == 2) && (abs(deltaY) != 0)) throw new IllegalArgumentException();
        if((abs(deltaX) == 0) && (abs(deltaY) != 2)) throw new IllegalArgumentException();

        Piece obstacle = field[oldX + deltaX][oldY + deltaY];

        if(obstacle == null) return false;
        else if(obstacle.getColor() == getColor()) throw new IllegalArgumentException();
        return true;

    }

    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        int oldX = getLocation().getKey(); int oldY = getLocation().getValue();
        Piece potentialKing;
        try {
            potentialKing = field[oldX + 2][oldY];
            if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(oldX + 2, oldY), getLocation());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            potentialKing = field[oldX - 2][oldY];
            if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(oldX + 2, oldY), getLocation());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            potentialKing = field[oldX][oldY + 2];
            if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(oldX + 2, oldY), getLocation());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            potentialKing = field[oldX][oldY - 2];
            if(potentialKing.getPieceType() == PieceType.KING) throw new KingInCheck(new Location(oldX + 2, oldY), getLocation());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
    };
}
