import javafx.util.Pair;
import model.game.*;
import model.pieces.Bishop;
import model.pieces.Color;
import model.pieces.MoveType;
import model.pieces.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class TestBishop {
    @Test
    public void testCheckValidMoveHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

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
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

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
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

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
        PieceSpec whitePieces = new PieceSpec();
        Location bishopLoc = new Location(2, (_boardLength - 1));
        Location obstructionLoc = new Location(3, (_boardLength - 2));
        Location bishopLocations[] = {bishopLoc};
        Location obstructionLocations[] = {obstructionLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, bishopLocations));
        whitePieces.addElement(new Pair(PieceType.PAWN, obstructionLocations));

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

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(2, (_boardLength - 2));
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KNIGHT, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act & assert
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        try {
            MoveType move = bishop.move(blackLoc.getKey(), blackLoc.getValue(), board);
            Assert.fail();
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckUpAndRightHappy() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(4, 5);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        bishop.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckUpAndLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(1, 6);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        bishop.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckDownAndLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, 0);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(1, 1);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        bishop.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckDownAndRight() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(2, 0);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.BISHOP, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(4, 2);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Bishop bishop = (Bishop) board.retrievePiece(whiteLoc);
        bishop.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }
}
