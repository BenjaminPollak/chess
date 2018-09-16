import javafx.util.Pair;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Vector;

public class TestRook {
    private static Board _board;
    private static int _boardLength;
    private static int _boardWidth;

    @BeforeClass
    public static void setUp() {
        _boardLength = 8; _boardWidth = 8;
        _board = new Board(_boardWidth, _boardLength, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooSmall() {
        new Rook(-1,0,_boardWidth,_boardLength);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooBig() {
        new Rook(_boardWidth,0,_boardWidth,_boardLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooSmall() {
        new Rook(3,-2,_boardWidth,_boardLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooBig() {
        new Rook(7,9,_boardWidth,_boardLength);
    }

    @Test
    public void testInstantiateRookGoodParams() {
        // arrange & act

        Rook rook = new Rook(1,5,_boardWidth, _boardLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookOutOfBounds() {
         // arrange
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Rook rook = new Rook(xCoord, yCoord, _boardWidth, _boardLength);

        // act
        rook.move(_boardWidth, yCoord, _board);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookDiagonally() {
        // arrange
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Rook rook = new Rook(xCoord, yCoord, _boardWidth, _boardLength);

        // act
        rook.move(xCoord + 1, yCoord - 1, _board);

        // assert - none, move() should throw an exception
    }

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
        int x_coord = 0;
        int y_coord = _boardLength - 1;
        Rook rook = new Rook(x_coord, y_coord, _boardWidth, _boardLength);

        // act
        MoveType moveType = rook.move(x_coord, (_boardLength - 2), _board);

        // assert
        Assert.assertEquals(moveType, MoveType.MOVE);
    }

    // TODO
    @Test
    public void testMoveRookHorizontallyValid() {

    }
}