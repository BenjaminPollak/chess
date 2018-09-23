import javafx.util.Pair;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

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

    @Test
    public void testCheckValidMoveHappyPath() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];
        Location RookLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {RookLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        Board board = new Board(boardWidth, boardLength, unplacedPieces, null);
        Rook rook = (Rook) board.retrievePiece(RookLoc);

        // act
        boolean attackMove = rook.checkValidMove((boardWidth - 1), 0, board.getField());

        // assert
        Assert.assertFalse(attackMove);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveSadPath() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Pair<PieceType, Location[]> unplacedPieces[] = new Pair[1];
        Location expectedFirstLoc = new Location((boardWidth - 1), (boardLength - 2));
        Location expectedSecondLoc = new Location((boardWidth - 1), (boardLength - 1));
        Location locations[] = {expectedFirstLoc, expectedSecondLoc};
        unplacedPieces[0] = new Pair(PieceType.ROOK, locations);

        Board board = new Board(boardWidth, boardLength, unplacedPieces, null);
        Rook rook = (Rook) board.retrievePiece(expectedFirstLoc);

        // act
        boolean attackMove = rook.checkValidMove((boardWidth - 1), (boardLength - 1), board.getField());

        // assert - should throw an exception
        Assert.fail();
    }

    @Test
    public void testMoveRookUpValid() {
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

    @Test
    public void testMoveRookRightValid() {
        // arrange
        Board board = new Board(8,8,null,null);
        int xCoord = 0;
        int yCoord = _boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);

        // act
        MoveType moveType = rook.move(_boardWidth - 1, yCoord, _board);

        // assert
        Assert.assertEquals(moveType, MoveType.MOVE);
    }

    @Test
    public void testCheckDownwardHappy() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, 0);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        field[0][0] = rook;

        // act
        boolean attacking = rook.checkDownwards(0, 0, 7, field);

        // assert
        Assert.assertFalse(attacking);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckDownwardSad() {
         // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, 0);
        Location obstacleLocation = new Location(0, 5);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        Rook obstacle = new Rook(obstacleLocation, _boardParams, Color.WHITE);
        field[0][0] = rook;
        field[0][5] = obstacle;

        // act
        boolean attacking = rook.checkDownwards(0, 0, 7, field);

        // assert - should throw exception
    }

    @Test
    public void testCheckUpwardHappy() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, (_boardLength-1));
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        field[0][0] = rook;

        // act
        boolean attacking = rook.checkDownwards(0, (_boardLength-1), 3, field);

        // assert
        Assert.assertFalse(attacking);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckUpwardSad() {
         // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, (_boardLength-1));
        Location obstacleLocation = new Location(0, (_boardLength-2));
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        Rook obstacle = new Rook(obstacleLocation, _boardParams, Color.WHITE);
        field[0][_boardLength-1] = rook;
        field[0][_boardLength-2] = obstacle;

        // act
        boolean attacking = rook.checkDownwards(0, 0, 7, field);

        // assert - should throw exception
        Assert.fail();
    }

    @Test
    public void testCheckRightHappy() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, 0);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        field[0][0] = rook;

        // act
        boolean attacking = rook.checkRight(0, 0, 7, field);

        // assert
        Assert.assertFalse(attacking);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckRightSad() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(0, (_boardLength-1));
        Location obstacleLocation = new Location(1, (_boardLength-1));
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        Rook obstacle = new Rook(obstacleLocation, _boardParams, Color.WHITE);
        field[0][_boardLength-1] = rook;
        field[1][_boardLength-1] = obstacle;

        // act
        boolean attacking = rook.checkRight(0, _boardLength-1, 7, field);

        // assert - should throw exception
        Assert.fail();
    }

    @Test
    public void testCheckLeftHappy() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location(_boardWidth-1, 0);
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        field[_boardWidth-1][0] = rook;

        // act
        boolean attacking = rook.checkLeft((_boardWidth-1), 0, 0, field);

        // assert
        Assert.assertFalse(attacking);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckLeftSad() {
        // arrange
        Piece[][] field = new Piece[_boardWidth][_boardLength];
        Location pieceLocation = new Location((_boardWidth-1), (_boardLength-1));
        Location obstacleLocation = new Location(1, (_boardLength-1));
        Rook rook = new Rook(pieceLocation, _boardParams, Color.WHITE);
        Rook obstacle = new Rook(obstacleLocation, _boardParams, Color.WHITE);
        field[0][_boardLength-1] = rook;
        field[1][_boardLength-1] = obstacle;

        // act
        boolean attacking = rook.checkLeft(_boardWidth-1, _boardLength-1, 0, field);

        // assert - should throw exception
        Assert.fail("attacking is " + Boolean.toString(attacking));
    }

    @Test
    public void testAttack() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location((_boardWidth - 1), (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.ROOK, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location((_boardWidth - 1), (_boardLength - 2));
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.ROOK, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);
        Rook whiteRook = (Rook) board.retrievePiece(whiteLoc);

        // act
        try {
            MoveType move = whiteRook.move((_boardWidth - 1), (_boardLength - 2), board);
        } catch(PieceCaptured e) {
            Assert.assertEquals(Color.BLACK, e.getColor());
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    // TODO: what about check sad path?
    @Test(expected = KingInCheck.class)
    public void testCheckDownHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(0, 0);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.ROOK, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(0, 7);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Rook rook = (Rook) board.retrievePiece(whiteLoc);
        rook.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    @Test(expected = KingInCheck.class)
    public void testCheckUpHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(0, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.ROOK, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(0, 0);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Rook rook = (Rook) board.retrievePiece(whiteLoc);
        rook.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    @Test(expected = KingInCheck.class)
    public void testCheckLeftHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(7, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.ROOK, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(0, 7);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Rook rook = (Rook) board.retrievePiece(whiteLoc);
        rook.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    @Test(expected = KingInCheck.class)
    public void testCheckRightHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(0, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.ROOK, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(7, 7);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Rook rook = (Rook) board.retrievePiece(whiteLoc);
        rook.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }
}