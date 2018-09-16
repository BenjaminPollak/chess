import javafx.util.Pair;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestBoard {
    @Test
    public void testPlacePieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Board board = new Board(boardWidth, boardLength);

        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];

        Location locations[] = {
                new Location((boardWidth - 1), (boardLength - 1)),
                new Location(0, (boardLength - 1))
        };
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        // act
        HashMap<PieceType, Piece[]> pieces = board.placePieces(unplacedPieces);

        // assert
        Piece rooks[] = pieces.get(PieceType.ROOK);

        // TODO: check location of rooks
        for(Piece rook: rooks) {

            for(Location location: locations) {

            }
        }
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
