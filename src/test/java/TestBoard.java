import javafx.util.Pair;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class TestBoard {
    @Test
    public void testPlacePieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Board board = new Board(boardWidth, boardLength);

        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];

        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location expectedSecondLoc = new Location((0), (boardLength - 1));
        Location locations[] = {expectedFirstLoc, expectedSecondLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        // act
        HashMap<PieceType, Vector<Piece>> pieces = board.instantiatePieces(unplacedPieces);

        // assert
        Vector<Piece> rooks = pieces.get(PieceType.ROOK);

        Assert.assertEquals(rooks.size(), locations.length);

        Location actualFirstLoc = rooks.elementAt(0).getLocation();
        Location actualSecondLoc = rooks.elementAt(1).getLocation();

        Assert.assertEquals(actualFirstLoc, expectedFirstLoc);
        Assert.assertEquals(actualSecondLoc, expectedSecondLoc);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadWidth() {
        new Board(3, 8);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadLength() {
        new Board(8, -2);
        Assert.fail();
    }

    @Test
    public void instantiateBoardWithGoodArgs() {
        new Board(8, 8);
        Assert.assertTrue(true);
    }
}
