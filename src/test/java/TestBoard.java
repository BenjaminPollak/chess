import javafx.util.Pair;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Vector;

public class TestBoard {
    @Test
    public void testHappyInstantiatePieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Board board = new Board(boardWidth, boardLength, null, null);

        PieceSpec unplacedPieces = new PieceSpec();

        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location expectedSecondLoc = new Location((0), (boardLength - 1));
        Location locations[] = {expectedFirstLoc, expectedSecondLoc};
        unplacedPieces.addElement(new Pair(PieceType.ROOK, locations));

        // act
        HashMap<PieceType, Vector<Piece>> pieces = board.createAndPlacePiecesOnBoard(unplacedPieces, Color.WHITE);

        // assert
        Vector<Piece> rooks = pieces.get(PieceType.ROOK);
        Location actualFirstLoc = rooks.elementAt(0).getLocation();
        Location actualSecondLoc = rooks.elementAt(1).getLocation();
        Piece[][] field = board.getField();

        Assert.assertEquals(rooks.size(), locations.length);
        Assert.assertEquals(actualFirstLoc, expectedFirstLoc);
        Assert.assertEquals(actualSecondLoc, expectedSecondLoc);
        Assert.assertEquals(rooks.elementAt(0), field[boardWidth - 1][boardLength - 1]);
        Assert.assertEquals(rooks.elementAt(1), field[0][boardLength - 1]);
    }
    @Test(expected = Board.PositionAlreadyTakenException.class)
    public void testSadInstantiatePieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Board board = new Board(boardWidth, boardLength, null, null);

        PieceSpec unplacedPieces = new PieceSpec();

        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location expectedSecondLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {expectedFirstLoc, expectedSecondLoc};
        unplacedPieces.addElement(new Pair(PieceType.ROOK, locations));

        // act
        HashMap<PieceType, Vector<Piece>> pieces = board.createAndPlacePiecesOnBoard(unplacedPieces, Color.WHITE);

        // assert
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadWidth() {
        new Board(3, 8, null, null);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadLength() {
        new Board(8, -2, null, null);
        Assert.fail();
    }

    @Test
    public void instantiateBoardWithGoodArgs() {
        new Board(8, 8, null, null);
        Assert.assertTrue(true);
    }

    @Test
    public void testRetrievePieceThatExists() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        PieceSpec unplacedPieces = new PieceSpec();
        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {expectedFirstLoc};
        unplacedPieces.addElement(new Pair(PieceType.ROOK, locations));

        Board board = new Board(boardWidth, boardLength, unplacedPieces, null);

        // act
        Location loc = new Location((boardWidth - 1), (boardLength - 1));
        Piece piece = board.retrievePiece(loc);

        // assert
        Assert.assertEquals(PieceType.ROOK, piece.getPieceType());
    }

    @Test
    public void testRetrievePieceThatDoesntExists() {
        //
    }
}
