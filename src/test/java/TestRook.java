import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

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
    public void instantiateRookXCoordTooSmall() {
        new Rook(-1,0,_board_width,_board_length);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateRookXCoordTooBig() {
        new Rook(_board_width,0,_board_width,_board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateRookYCoordTooSmall() {
        new Rook(3,-2,_board_width,_board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateRookYCoordTooBig() {
        new Rook(7,9,_board_width,_board_length);
    }

    @Test
    // TODO: check results of args
    public void instantiateRookGoodParams() {
        new Rook(1,5,_board_width, _board_length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRookBelowBoundsLength() {
        // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(x_coord, -1, _board_width, _board_length);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRookAboveBoundsLength() {
        // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(x_coord, _board_length, _board_width, _board_length);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRookBelowBoundsWidth() {
         // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(-1, y_coord, _board_width, _board_length);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRookAboveBoundsWidth() {
         // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(_board_width, y_coord, _board_width, _board_length);

        // assert - none, move() should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRookDiagonally() {
        // arrange
        int x_coord = 0;
        int y_coord = _board_length - 1;
        Rook rook = new Rook(x_coord, y_coord, _board_width, _board_length);

        // act
        rook.move(x_coord + 1, y_coord - 1, _board_width, _board_length);

        // assert - none, move() should throw an exception
    }

    // TODO
    @Test
    public void moveRookRightValid() {

    }

    // TODO
    @Test
    public void moveRookLeftValid() {

    }

    // TODO
    @Test
    public void moveRookUpValid() {

    }

    // TODO
    @Test
    public void moveRookDownValid() {

    }
}