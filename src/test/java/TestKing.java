import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestKing {

    @Test
    public void testCheckKingHappyPath() {
        // assert
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        int xCoord =  1; int yCoord = _boardLength - 1;
        Location whiteLoc = new Location(xCoord, yCoord);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        Boolean attacking = king.checkValidMove(xCoord + 1, yCoord, board.getField());

        // assert
        Assert.assertFalse(attacking);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckKingIllegalMove() {
        // assert
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        int xCoord =  1; int yCoord = _boardLength - 1;
        Location whiteLoc = new Location(xCoord, yCoord);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        Boolean attacking = king.checkValidMove(xCoord + 1, yCoord - 2, board.getField());

        // assert - should throw an exception
        Assert.fail();
    }

    @Test
    public void testCheckKingAttacking() {
        // assert
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        int xKing =  1; int yKing = _boardLength - 1;
        Location whiteLoc = new Location(xKing, yKing);
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        int xEnemy =  1; int yEnemy = _boardLength - 2;
        Location blackLoc = new Location(xEnemy, yEnemy);
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.PAWN, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        Boolean attacking = king.checkValidMove(xKing, yKing - 1, board.getField());

        // assert - should throw an exception
        Assert.assertTrue(attacking);
    }

    @Test
    public void testMoveKingLinearly() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        MoveType move = king.move(2, _boardLength - 1, board);

        // assert
        Piece[][] field = board.getField();
        Piece supposedKing = field[2][_boardLength - 1];
        Location expectedLoc = new Location(2, _boardLength - 1);
        Assert.assertEquals(MoveType.MOVE, move);
        Assert.assertEquals(null, field[1][_boardLength - 1]);
        Assert.assertEquals(PieceType.KING, supposedKing.getPieceType());
    }

    @Test
    public void testMoveKingDiagonally() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        MoveType move = king.move(2, _boardLength - 2, board);

        // assert
        Piece[][] field = board.getField();
        Piece supposedKing = field[2][_boardLength - 2];
        Location expectedLoc = new Location(2, _boardLength - 1);
        Assert.assertEquals(MoveType.MOVE, move);
        Assert.assertEquals(null, field[1][_boardLength - 1]);
        Assert.assertEquals(PieceType.KING, supposedKing.getPieceType());
    }

    @Test
    public void testKingAttack() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KING, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(1, (_boardLength - 2));
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KNIGHT, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        King king = (King) board.retrievePiece(whiteLoc);
        try {
            MoveType move = king.move(1, _boardLength - 2, board);
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }

    }
}
