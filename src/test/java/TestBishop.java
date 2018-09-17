import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestBishop {
    @Test
    public void testCheckValidMoveHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.BISHOP, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        boolean attack = bishop.checkValidMove((whiteX + 1), (whiteY - 1), board.getField());

        // assert
        Assert.assertFalse(attack);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveOutOfBounds() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.BISHOP, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        boolean attack = bishop.checkValidMove(8, (_boardLength - 7), board.getField());

        // assert - should throw an error
        Assert.fail();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveIllegalMovement() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.BISHOP, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        boolean attack = bishop.checkValidMove(2, (_boardLength - 4), board.getField());

        // assert - should throw an error
        Assert.fail();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveObstructed() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        Pair<PieceType, Location[]> whitePieces[] = new Pair[2];
        Location bishopLoc = new Location(2, (_boardLength - 1));
        Location obstructionLoc = new Location(3, (_boardLength - 2));
        Location bishopLocations[] = {bishopLoc};
        Location obstructionLocations[] = {obstructionLoc};
        whitePieces[0] = new Pair(PieceType.BISHOP, bishopLocations);
        whitePieces[1] = new Pair(PieceType.PAWN, obstructionLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(bishopLoc);
        boolean attack = bishop.checkValidMove(4, (_boardLength - 3), board.getField());

        // assert - should throw an error
        Assert.fail();

    }

    @Test
    public void testAttack() {
                // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.BISHOP, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(2, (_boardLength - 2));
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KNIGHT, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        MoveType move = bishop.move(blackLoc.getKey(), blackLoc.getValue(), board);

        // assert
        Piece[][] field = board.getField();
        Piece expectedBishop = field[2][_boardLength - 2];
        Assert.assertEquals(MoveType.ATTACK, move);
        Assert.assertEquals(null, field[1][_boardLength - 1]);
        Assert.assertEquals(PieceType.BISHOP, expectedBishop.getPieceType());
        Assert.assertTrue(expectedBishop.getLocation().equals(blackLoc));
    }
}
