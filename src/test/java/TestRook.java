import javafx.util.Pair;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Vector;

public class TestRook {
    private static Board _board;
    private static int _boardLength;
    private static int _boardWidth;
    private static Location _boardParams;

    @BeforeClass
    public static void setUp() {
        _boardLength = 8; _boardWidth = 8;
        _board = new Board(_boardWidth, _boardLength, null, null);
        _boardParams = new Location(_boardWidth, _boardLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooSmall() {
        Location pieceLocatoin = new Location(-1, 0);
        new Rook(pieceLocatoin, _boardParams, Color.WHITE);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooBig() {
        Location pieceLocation = new Location(_boardWidth, 0);
        new Rook(pieceLocation, _boardParams, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooSmall() {
        Location pieceLocation = new Location(3, -2);
        new Rook(pieceLocation, _boardParams, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooBig() {
        Location pieceLocation = new Location(7,9);
        new Rook(pieceLocation, _boardParams, Color.WHITE);
    }

    @Test
    public void testInstantiateRookGoodParams() {
        // arrange & act
        Location pieceLocation = new Location(1,5);
        new Rook(pieceLocation, _boardParams, Color.WHITE);
        Assert.assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookOutOfBounds() {
         // arrange
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);

        // act
        rook.move(_boardWidth, yCoord, _board);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookDiagonally() {
        // arrange
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);

        // act
        rook.move(xCoord + 1, yCoord - 1, _board);

        // assert - none, move() should throw an exception
    }

    @Ignore
    @Test
    public void testCheckValidMoveHappyPath() {
        // TODO: write this NOW
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];
        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {expectedFirstLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        Board board = new Board(boardWidth, boardLength, unplacedPieces, null);
        Rook rook = (Rook) board.retrievePiece(expectedFirstLoc);

        // act
        boolean attackMove = rook.checkValidMove((boardWidth - 1), 0, board.getField());

        // assert
        Assert.assertFalse(attackMove);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveSadPath() {
        // TODO: write this NOW
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];
        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location expectedSecondLoc = new Location((boardWidth - 1), (boardLength - 2));
        Location locations[] = {expectedFirstLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        Board board = new Board(boardWidth, boardLength, unplacedPieces, null);
        Rook rook = (Rook) board.retrievePiece(expectedFirstLoc);

        // act
        boolean attackMove = rook.checkValidMove((boardWidth - 1), 0, board.getField());

        // assert - should throw an exception
        Assert.fail();
    }

    @Test
    public void testMoveRookVerticallyValid() {
        // arrange
        Board board = new Board(8,8,null,null);
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);

        // act
        MoveType moveType = rook.move(xCoord, (_boardLength - 2), _board);

        // assert
        Assert.assertEquals(moveType, MoveType.MOVE);
    }

    // TODO
    @Test
    public void testMoveRookHorizontallyValid() {

    }
}