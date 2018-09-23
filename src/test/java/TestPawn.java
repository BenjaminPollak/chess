import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestPawn {
    @Test
    public void testCheckWhiteMoveOneForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = boardLength - 2;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean attack = pawn.checkWhiteMove(0, boardLength - 3, field);

        // assert
        Location newLocation = pawn.getLocation();
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckWhiteMoveTwoForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = boardLength - 2;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean attacked = pawn.checkWhiteMove(0, boardLength - 4, field);

        // assert
        Assert.assertFalse(attacked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckWhiteMoveTwoForwardSad() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location pawnLoc = new Location((boardWidth - 1), (boardLength - 2));
        Location obstructionLoc = new Location((boardWidth - 1), (boardLength - 4));
        Location whiteLocations[] = {pawnLoc, obstructionLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, null);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(pawnLoc);
        pawn.checkWhiteMove(boardWidth - 1, boardLength - 4, board.getField());

        // assert - should throw error
        Assert.fail();
    }

    @Test
    public void testCheckWhiteAttackLeft() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(1, (boardLength - 2));
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(0, (boardLength - 3));
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        boolean attacking = attacker.checkWhiteMove(0, (boardLength - 3), board.getField());

        // assert
        Assert.assertTrue(attacking);
    }

    @Test
    public void testCheckWhiteAttackRight() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(0, (boardLength - 2));
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(1, (boardLength - 3));
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        boolean attacking = attacker.checkWhiteMove(1, (boardLength - 3), board.getField());

        // assert
        Assert.assertTrue(attacking);
    }

    @Test
    public void testCheckBlackMoveOneForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean attack = pawn.checkBlackMove(0, 2, field);

        // assert
        Assert.assertFalse(attack);
    }

    @Test
    public void testCheckBlackMoveTwoForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean attacked = pawn.checkBlackMove(0, 3, field);

        // assert
        Assert.assertFalse(attacked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckBlackMoveTwoForwardSad() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location pawnLoc = new Location(0, 1);
        Location obstructionLoc = new Location(0, 3);
        Location whiteLocations[] = {pawnLoc, obstructionLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Board board = new Board(boardWidth, boardLength, blackPieces, null);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(pawnLoc);
        pawn.checkBlackMove(0, 3, board.getField());

        // assert - should throw error
        Assert.fail();
    }

    @Test
    public void testCheckBlackAttackLeft() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(0, 2);
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(1, 1);
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        boolean attacking = attacker.checkBlackMove(0, 2, board.getField());

        // assert
        Assert.assertTrue(attacking);
    }

    @Test
    public void testCheckBlackAttackRight() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(1, 2);
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(0, 1);
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        boolean attacking = attacker.checkBlackMove(1, 2, board.getField());

        // assert
        Assert.assertTrue(attacking);
    }

    @Test
    public void testCheckValidMoveOneForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = boardLength - 2;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean isValid = pawn.checkValidMove((0), (boardLength - 3), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckValidMoveTwoForwardHappy() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = boardLength - 2;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean isValid = pawn.checkValidMove((0), (boardLength - 4), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test
    public void testCheckValidMoveTwoForwardSad() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 0; int yCoord = boardLength - 2;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Pawn pawn = new Pawn(pieceLocation, boardParams, Color.WHITE);
        pawn.setYetToMove(false);

        // act
        boolean isValid = pawn.checkValidMove((0), (boardLength - 4), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhiteAttackRight() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(0, (boardLength - 2));
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(1, (boardLength - 3));
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        MoveType move = attacker.move(1, (boardLength - 3), board);

        // assert
        Piece[][] field = board.getField();
        Piece expectedPawn = field[1][boardLength - 3];
        Assert.assertEquals(MoveType.ATTACK, move);
        Assert.assertEquals(null, field[1][boardLength - 2]);
        Assert.assertEquals(PieceType.PAWN, expectedPawn.getPieceType());
        Assert.assertTrue(expectedPawn.getLocation().equals(enemyLoc));    }

    @Test
    public void testWhiteAttackLeft() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location attackerLoc = new Location(1, (boardLength - 2));
        Location whiteLocations[] = {attackerLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location enemyLoc = new Location(0, (boardLength - 3));
        Location blackLocations[] = {enemyLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        MoveType move = attacker.move(0, (boardLength - 3), board);

        // assert
        Piece[][] field = board.getField();
        Piece expectedPawn = field[0][boardLength - 3];
        Assert.assertEquals(MoveType.ATTACK, move);
        Assert.assertEquals(null, field[1][boardLength - 2]);
        Assert.assertEquals(PieceType.PAWN, expectedPawn.getPieceType());
        Assert.assertTrue(expectedPawn.getLocation().equals(enemyLoc));
    }

    @Test
    public void testBlackAttackRight() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location enemyLoc = new Location(1, (boardLength - 2));
        Location whiteLocations[] = {enemyLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location attackerLoc = new Location(0, (boardLength - 3));
        Location blackLocations[] = {attackerLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        MoveType move = attacker.move(0, (boardLength - 3), board);

        // assert
        Piece[][] field = board.getField();
        Piece expectedPawn = field[1][boardLength - 2];
        Assert.assertEquals(MoveType.ATTACK, move);
        Assert.assertEquals(null, field[0][boardLength - 3]);
        Assert.assertEquals(PieceType.PAWN, expectedPawn.getPieceType());
        Assert.assertTrue(expectedPawn.getLocation().equals(enemyLoc));
    }

    @Test
    public void testBlackAttackLeft() {
        // arrange
        int boardWidth = 8; int boardLength = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location enemyLoc = new Location(0, (boardLength - 2));
        Location whiteLocations[] = {enemyLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location attackerLoc = new Location(1, (boardLength - 3));
        Location blackLocations[] = {attackerLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(boardWidth, boardLength, whitePieces, blackPieces);

        // act
        Pawn attacker = (Pawn) board.retrievePiece(attackerLoc);
        MoveType move = attacker.move(0, (boardLength - 2), board);

        // assert
        Piece[][] field = board.getField();
        Piece expectedPawn = field[0][boardLength - 2];
        Assert.assertEquals(MoveType.ATTACK, move);
        Assert.assertEquals(null, field[1][boardLength - 3]);
        Assert.assertEquals(PieceType.PAWN, expectedPawn.getPieceType());
        Assert.assertTrue(expectedPawn.getLocation().equals(enemyLoc));
    }

    // TODO: what if no check?
    @Test(expected = Piece.KingInCheck.class)
    public void testWhiteCheckRight() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(1, 1);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(2, 0);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(whiteLoc);
        pawn.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if no check?
    @Test(expected = Piece.KingInCheck.class)
    public void testWhiteCheckLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(3, 1);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.PAWN, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(2, 0);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.KING, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(whiteLoc);
        pawn.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();    }

    // TODO: what if no check?
    @Test(expected = Piece.KingInCheck.class)
    public void testBlackCheckLeft() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(1, 2);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.KING, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(2, 1);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(blackLoc);
        pawn.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();
    }

    // TODO: what if no check?
    @Test(expected = Piece.KingInCheck.class)
    public void testBlackCheckRight() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        Pair<PieceType, Location[]> whitePieces[] = new Pair[1];
        Location whiteLoc = new Location(3, 2);
        Location whiteLocations[] = {whiteLoc};
        whitePieces[0] = new Pair(PieceType.KING, whiteLocations);

        Pair<PieceType, Location[]> blackPieces[] = new Pair[1];
        Location blackLoc = new Location(2, 1);
        Location blackLocations[] = {blackLoc};
        blackPieces[0] = new Pair(PieceType.PAWN, blackLocations);

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Pawn pawn = (Pawn) board.retrievePiece(blackLoc);
        pawn.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();    }
}
