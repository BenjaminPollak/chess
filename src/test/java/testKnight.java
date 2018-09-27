import javafx.util.Pair;
import model.game.*;
import model.pieces.*;
import org.junit.Assert;
import org.junit.Test;

public class testKnight {

    @Test
    public void testCheckValidMoveHappyPath() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 1; int yCoord = boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Knight knight = new Knight(pieceLocation, boardParams, Color.WHITE);

        // act
        boolean isValid = knight.checkValidMove((2), (boardLength - 3), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveInvalid() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 1; int yCoord = boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Knight knight = new Knight(pieceLocation, boardParams, Color.WHITE);

        field[xCoord][yCoord] = knight;

        // act
        boolean isValid = knight.checkValidMove((2), (boardLength - 1), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveOutOfBounds() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);
        int xCoord = 1; int yCoord = boardLength - 1;
        Location pieceLocation = new Location(xCoord, yCoord);
        Piece[][] field = new Piece[boardWidth][boardLength];
        Knight knight = new Knight(pieceLocation, boardParams, Color.WHITE);

        field[xCoord][yCoord] = knight;

        // act
        boolean isValid = knight.checkValidMove((xCoord + 2), (boardLength), field);

        // assert
        Assert.assertFalse(isValid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckValidMoveFriendlyFire() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);

        int xCoord = 1; int yCoord = boardLength - 1;

        Location knightLocation = new Location(xCoord, yCoord);
        Location obstacleLocation = new Location((xCoord + 1), (yCoord - 2));

        Piece[][] field = new Piece[boardWidth][boardLength];
        Knight knight = new Knight(knightLocation, boardParams, Color.WHITE);
        Knight obstacle = new Knight(obstacleLocation, boardParams, Color.WHITE);

        field[xCoord][yCoord] = knight;
        field[xCoord+1][yCoord-2] = obstacle;

        // act
        knight.checkValidMove((xCoord + 2), (yCoord -  2), field);

        // assert - should throw exception
        Assert.fail();
    }

    @Test
    public void testCheckValidMoveAttack() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Location boardParams = new Location(boardWidth, boardLength);

        int xCoord = 1; int yCoord = boardLength - 1;

        Location knightLocation = new Location(xCoord, yCoord);
        Location obstacleLocation = new Location((xCoord + 1), (yCoord - 2));

        Piece[][] field = new Piece[boardWidth][boardLength];
        Knight knight = new Knight(knightLocation, boardParams, Color.WHITE);
        Knight obstacle = new Knight(obstacleLocation, boardParams, Color.BLACK);

        field[xCoord][yCoord] = knight;
        field[xCoord+1][yCoord-2] = obstacle;

        // act
        boolean attack = knight.checkValidMove((xCoord + 1), (yCoord -  2), field);

        // assert
        Assert.assertTrue(attack);
    }

    @Test
    public void testMoveKnightHappyPath() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Knight knight = (Knight) board.retrievePiece(whiteLoc);
        MoveType move = knight.move(2, _boardLength - 3, board);

        // assert
        Piece[][] field = board.getField();
        Piece supposedKnight = field[2][_boardLength - 3];
        Location expectedLoc = new Location(2, _boardLength - 3);
        Assert.assertEquals(MoveType.MOVE, move);
        Assert.assertEquals(null, field[1][_boardLength - 1]);
        Assert.assertEquals(PieceType.KNIGHT, supposedKnight.getPieceType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveKnightOutOfBounds() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Knight knight = (Knight) board.retrievePiece(whiteLoc);
        MoveType move = knight.move(3, _boardLength, board);

        // assert - should return
        Assert.fail("Should've thrown excpetion");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveKnightIllegally() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;
        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, null);

        // act
        Knight knight = (Knight) board.retrievePiece(whiteLoc);
        MoveType move = knight.move(2, _boardLength, board);

        // assert - should return
        Assert.fail("Should've thrown excpetion");
    }

    @Test
    public void testKnightAttack() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(2, (_boardLength - 3));
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KNIGHT, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act & assert
        Knight knight = (Knight) board.retrievePiece(whiteLoc);
        try {
            MoveType move = knight.move(2, _boardLength - 3, board);
            Assert.fail();
        } catch(PieceCaptured e) {
            Assert.assertEquals(e.getColor(), Color.BLACK);
            Assert.assertTrue(e.getLocation().equals(blackLoc));
        }
    }

    @Test(expected = KingInCheck.class)
    public void detectInCheck() {
        // arrange
        int _boardLength = 8; int _boardWidth = 8;

        PieceSpec whitePieces = new PieceSpec();
        Location whiteLoc = new Location(1, (_boardLength - 1));
        Location whiteLocations[] = {whiteLoc};
        whitePieces.addElement(new Pair(PieceType.KNIGHT, whiteLocations));

        PieceSpec blackPieces = new PieceSpec();
        Location blackLoc = new Location(2, (_boardLength - 3));
        Location blackLocations[] = {blackLoc};
        blackPieces.addElement(new Pair(PieceType.KING, blackLocations));

        Board board = new Board(_boardWidth, _boardLength, whitePieces, blackPieces);

        // act
        Knight knight = (Knight) board.retrievePiece(whiteLoc);
        knight.findIfKingInCheck(board.getField());

        // assert - should raise an exception
        Assert.fail();

    }
}
