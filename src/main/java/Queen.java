public class Queen extends Piece{
    public Queen(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.KNIGHT, color);
    }

    MoveType move(int xCoord, int yCoord, Board board) {
        return null;
    }
}
