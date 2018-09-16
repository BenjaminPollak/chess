import javafx.util.Pair;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Vector;

public class TestRook {
    private static Board _board;
    private static int _board_length;
    private static int _board_width;

    @BeforeClass
    public static void setUp() {
        _board_length = 8; _board_width = 8;
        _board = new Board(_board_width, _board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooSmall() {
        new Rook(-1,0,_board_width,_board_length);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookXCoordTooBig() {
        new Rook(_board_width,0,_board_width,_board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooSmall() {
        new Rook(3,-2,_board_width,_board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateRookYCoordTooBig() {
        new Rook(7,9,_board_width,_board_length);
    }

    @Test
    public void testInstantiateRookGoodParams() {
        // arrange & act

        Rook rook = new Rook(1,5,_board_width, _board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookOutOfBounds() {
         // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(_board_width, y_coord, _board);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveRookDiagonally() {
        // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(x_coord + 1, y_coord - 1, _board);

        // assert - none, move() should throw an exception
    }

    @Test
    public void testCheckValidMoveHappyPath() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Board board = new Board(boardWidth, boardLength);

        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];

        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {expectedFirstLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);
        HashMap<PieceType, Vector<Piece>> pieces = board.createAndPlacePiecesOnBoard(unplacedPieces);

        // act
        // assert
    }

    @Test
    public void testCheckValidMoveSadPath() {

    }

    @Test
    public void testMoveRookVerticallyValid() {
        // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        //Piece.move_type move_type = rook.move(x_coord, (_board_length - 2), _board);
        // assert
    }

    // TODO
    @Test
    public void testMoveRookHorizontallyValid() {

    }
}