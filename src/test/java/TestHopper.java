import javafx.util.Pair;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHopper {
    int _boardWidth = 8; int _boardLength = 8;

    @Test
    public void testMoveVertical() {
        // arrange
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.HOPPER, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        int blackX = 4; int blackY = 0;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.QUEEN, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Hopper hopper = (Hopper) board.retrievePiece(whiteLoc);
        try {
            MoveType move = hopper.move(whiteX, whiteY - 2, board);
            Assert.assertEquals(move, MoveType.MOVE);
        } catch(PieceCaptured e) {
            Assert.fail();
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testFriendlyFire() {
         // arrange
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = _boardWidth - 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location obstacleLoc = new Location(whiteX - 2, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.HOPPER, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Hopper hopper = (Hopper) board.retrievePiece(whiteLoc);
        hopper.move(whiteX - 2, whiteY, board);

        // assert - should throw exception
        Assert.fail();
    }

    @Test
    public void testCapture() {
        // arrange
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.HOPPER, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        int blackX = 2; int blackY = _boardLength - 3;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.QUEEN, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Hopper hopper = (Hopper) board.retrievePiece(whiteLoc);
        try {
            MoveType move = hopper.move(whiteX, whiteY - 2, board);
            Assert.fail();
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }
    @Test
    public void testKingInCheckHappyPath() {
        // arrange
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.HOPPER, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        int blackX = 2; int blackY = _boardLength - 3;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Hopper hopper = (Hopper) board.retrievePiece(whiteLoc);
        try {
            hopper.findIfKingInCheck(board.getField());
            Assert.fail();
        } catch(KingInCheck e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testKingInCheckSadPath() {
        // arrange
        PieceSpec whitePieces = new PieceSpec();
        int whiteX = 2; int whiteY = _boardLength - 1;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.HOPPER, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        int blackX = 2; int blackY = 0;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.QUEEN, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Hopper hopper = (Hopper) board.retrievePiece(whiteLoc);
        try {
            hopper.findIfKingInCheck(board.getField());
        } catch(KingInCheck e) {
            Assert.fail();
        }
    }
}
