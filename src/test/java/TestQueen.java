import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestQueen {
    int _boardLength; int _boardWidth;
    Location _boardParameters;

    Pair<PieceType, Location[]> _whitePieces[];
    Pair<PieceType, Location[]> _blackPieces[];
    Board _board;

    @Before
    public void setUp() {
        _boardLength = 8; _boardWidth = 8;
        _boardParameters = new Location(_boardWidth, _boardLength);

        _whitePieces = new Pair[2];
        _blackPieces = new Pair[2];
    }

    @Test
    public void testDetectObstructionsVerticalUp() {
        // arrange
        Location queenLoc = new Location(3, _boardLength- 1);
        Location whiteLocations[] = new Location[] {queenLoc};

        Pair queenPair = new Pair(PieceType.QUEEN, whiteLocations);
        _whitePieces = new Pair[] {queenPair};

        _board = new Board(_boardWidth, _boardLength, _whitePieces, null);

        // act
        Queen queen = (Queen) _board.retrievePiece(queenLoc);
        boolean obstructionsExist = queen.detectObstructions(true, true, 3, 5, _board.getField());

        // assert
        Assert.assertFalse(obstructionsExist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetectObstructionsVerticalDown() {
        // arrange
        Location queenLoc = new Location(3, 6);
        Location queenLocations[] = new Location[] {queenLoc};
        Pair queenPair = new Pair(PieceType.QUEEN, queenLocations);

        Location pawnLoc = new Location(3, 4);
        Location pawnLocations[] = new Location[] {pawnLoc};
        Pair pawnPair = new Pair(PieceType.PAWN, pawnLocations);

        _whitePieces = new Pair[] {queenPair, pawnPair};

        _board = new Board(_boardWidth, _boardLength, _whitePieces, null);

        // act
        Queen queen = (Queen) _board.retrievePiece(queenLoc);
        queen.detectObstructions(true, false, 6, 3, _board.getField());

        // assert - should throw exception
        Assert.fail();
    }

    @Test
    public void testDetectObstructionsHorizontalUp() {
        // arrange
        Location queenLoc = new Location(3, _boardLength- 1);
        Location whiteLocations[] = new Location[] {queenLoc};

        Pair queenPair = new Pair(PieceType.QUEEN, whiteLocations);
        _whitePieces = new Pair[] {queenPair};

        _board = new Board(_boardWidth, _boardLength, _whitePieces, null);

        // act
        Queen queen = (Queen) _board.retrievePiece(queenLoc);
        boolean obstructionsExist = queen.detectObstructions(false, true, 5, _boardLength - 1, _board.getField());

        // assert
        Assert.assertFalse(obstructionsExist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetectObstructionsHorizontalDown() {
        // arrange
        Location queenLoc = new Location(3, 4);
        Location queenLocations[] = new Location[] {queenLoc};
        Pair queenPair = new Pair(PieceType.QUEEN, queenLocations);

        Location pawnLoc = new Location(5, 4);
        Location pawnLocations[] = new Location[] {pawnLoc};
        Pair pawnPair = new Pair(PieceType.PAWN, pawnLocations);

        _whitePieces = new Pair[] {queenPair, pawnPair};

        _board = new Board(_boardWidth, _boardLength, _whitePieces, null);

        // act
        Queen queen = (Queen) _board.retrievePiece(queenLoc);
        queen.detectObstructions(false, false, 6, 3, _board.getField());

        // assert - should throw exception
        Assert.fail();

    }

    @Test
    public void testCheckMoveUpHappy() {
        // arrange
        Location whiteLoc = new Location(3, _boardLength - 1);
        Location whiteLocs[] = new Location[] {whiteLoc};

        _whitePieces = new Pair[1];
        _whitePieces[0] = new Pair<PieceType, Location[]>(PieceType.QUEEN, whiteLocs);

        _board = new Board(_boardWidth, _boardLength, _whitePieces, null);

        // act
        Queen queen = (Queen) _board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteLoc.getKey(), _boardLength - 3, _board.getField());

        // assert
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckMoveDownHappy() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteX, _boardLength - 1, board.getField());

        // assert
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckMoveLeftHappy() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteX - 1, _boardLength - 2, board.getField());

        // assert
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckMoveRightHappy() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteX + 1, _boardLength - 2, board.getField());

        // assert
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckMoveDiagonalHappy() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteX + 1, _boardLength - 3, board.getField());

        // assert
        Assert.assertFalse(attack);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckMoveInvalid() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        boolean attack = queen.checkValidMove(whiteX + 4, whiteY - 1, board.getField());

        // assert
        Assert.fail();
    }

    @Test
    public void testAttackHorizontal() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        int blackX = 4; int blackY = _boardLength - 2;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.QUEEN, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        try {
            MoveType move = queen.move(blackX, blackY, board);
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    @Test
    public void testAttackVertical() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        int blackX = 3; int blackY = _boardLength - 4;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.QUEEN, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        try {
            MoveType move = queen.move(blackX, blackY, board);
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    @Test
    public void testAttackDiagonal() {
        // arrange
        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        int whiteX = 3; int whiteY = _boardLength - 2;
        Location whiteLoc = new Location(whiteX, whiteY);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        int blackX = 4; int blackY = _boardLength - 3;
        Location blackLoc = new Location(blackX, blackY);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.QUEEN, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        try {
            MoveType move = queen.move(blackX, blackY, board);
            Assert.fail();
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    /*
     * TODO: Below is problematic
     */
    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckUpAndRightHappy() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(4, 5);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        queen.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckUpAndLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, 7);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(1, 6);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        queen.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckDownAndLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, 0);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(1, 1);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        queen.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if piece in the way, OOB error?
    @Test(expected = KingInCheck.class)
    public void testCheckDownAndRight() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(2, 0);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.QUEEN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(4, 2);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Queen queen = (Queen) board.retrievePiece(whiteLoc);
        queen.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }
}
