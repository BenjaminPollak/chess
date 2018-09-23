import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestGame {
    @Test
    public void testCreateWhitePawns() {
       // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

       // act
        Location [] whitePawns = game.createPawnSchematics(Color.WHITE);

       // assert
       Assert.assertEquals(whitePawns.length, 8);
       for(int i = 0; i < 8; ++i) {
           Location pawnLoc = whitePawns[i];
           int xCoord = pawnLoc.getKey();
           int yCoord = pawnLoc.getValue();
           Assert.assertEquals(i, xCoord);
           Assert.assertEquals(boardLength - 2, yCoord );
       }

    }

    @Test
    public void testCreateBlackPawns() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackPawns = game.createPawnSchematics(Color.BLACK);

        // assert
        Assert.assertEquals(blackPawns.length, 8);
        for(int i = 0; i < 8; ++i) {
            Location pawnLoc = blackPawns[i];
            int xCoord = pawnLoc.getKey();
            int yCoord = pawnLoc.getValue();
            Assert.assertEquals(i, xCoord);
            Assert.assertEquals(1, yCoord );
        }

    }

    @Test
    public void testCreateWhiteRooks() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] whiteRooks = game.createRookSchematics(Color.WHITE);

        // assert
        Location firstRook = whiteRooks[0];
        Location secondRook = whiteRooks[1];
        Assert.assertEquals(whiteRooks.length, 2);
        Assert.assertTrue(firstRook.equals(new Location(0, boardLength - 1)));
        Assert.assertTrue(secondRook.equals(new Location(boardLength - 1, boardLength - 1)));
    }

    @Test
    public void testCreateBlackRooks() {

        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackRooks = game.createRookSchematics(Color.BLACK);

        // assert
        Location firstRook = blackRooks[0];
        Location secondRook = blackRooks[1];
        Assert.assertEquals(blackRooks.length, 2);
        Assert.assertTrue(firstRook.equals(new Location(0, 0)));
        Assert.assertTrue(secondRook.equals(new Location(boardWidth - 1, 0)));
    }

    @Test
    public void testCreateWhiteKnights() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackKnights = game.createKnightSchematics(Color.WHITE);

        // assert
        Location firstKnight = blackKnights[0];
        Location secondKnight = blackKnights[1];
        Assert.assertEquals(blackKnights.length, 2);
        Assert.assertTrue(firstKnight.equals(new Location(1, boardLength -1)));
        Assert.assertTrue(secondKnight.equals(new Location(6, boardLength -1)));
    }

    @Test
    public void testCreateBlackKnights() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackKnights = game.createKnightSchematics(Color.BLACK);

        // assert
        Location firstKnight = blackKnights[0];
        Location secondKnight = blackKnights[1];
        Assert.assertEquals(blackKnights.length, 2);
        Assert.assertTrue(firstKnight.equals(new Location(1, 0)));
        Assert.assertTrue(secondKnight.equals(new Location(boardWidth - 2, 0)));
    }

    @Test
    public void testCreateWhiteBishops() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] whiteBishops = game.createBishopSchematics(Color.WHITE);

        // assert
        Location firstKnight = whiteBishops[0];
        Location secondKnight = whiteBishops[1];
        Assert.assertEquals(whiteBishops.length, 2);
        Assert.assertTrue(firstKnight.equals(new Location( 2, boardLength - 1)));
        Assert.assertTrue(secondKnight.equals(new Location(boardWidth - 3, boardLength - 1)));
    }

    @Test
    public void testCreateBlackBishops() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackBishops = game.createBishopSchematics(Color.BLACK);

        // assert
        Location firstBishop = blackBishops[0];
        Location secondBishop = blackBishops[1];
        Assert.assertEquals(blackBishops.length, 2);
        Assert.assertTrue(firstBishop.equals(new Location(2, 0)));
        Assert.assertTrue(secondBishop.equals(new Location(0, boardWidth - 3)));
    }

    @Test
    public void testCreateWhiteQueen() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] whiteQueens = game.createQueenSchematics(Color.WHITE);

        // assert
        Location firstQueen = whiteQueens[0];
        Assert.assertEquals(whiteQueens.length, 1);
        Assert.assertTrue(firstQueen.equals(new Location(3, boardLength - 1)));
    }

    @Test
    public void testCreateBlackQueen() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackQueens = game.createQueenSchematics(Color.BLACK);

        // assert
        Location firstQueen = blackQueens[0];
        Assert.assertEquals(blackQueens.length, 1);
        Assert.assertTrue(firstQueen.equals(new Location(3, 0)));
    }

    @Test
    public void testCreateWhiteKing() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] whiteKings = game.createKingSchematics(Color.WHITE);

        // assert
        Location firstQueen = whiteKings[0];
        Assert.assertEquals(whiteKings.length, 1);
        Assert.assertTrue(firstQueen.equals(new Location(4, boardLength - 1)));
    }

    @Test
    public void testCreateBlackKing() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        Location [] blackKings = game.createKingSchematics(Color.BLACK);

        // assert
        Location blackKingLocation = blackKings[0];
        Assert.assertEquals(blackKings.length, 1);
        Assert.assertTrue(blackKingLocation.equals(new Location(4, 0)));
    }

    @Test
    public void testGatherWhitePieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        game.gatherPieces(Color.WHITE);

        // assert
        Pair<PieceType, Location[]> whitePieces[] = game.getWhitePieces();
        Assert.assertEquals(whitePieces.length, 6);
    }

    @Test
    public void testGatherBlackPieces() {
        // arrange
        int boardWidth = 8; int boardLength = 8;
        Game game = new Game(boardWidth, boardLength, null, null);

        // act
        game.gatherPieces(Color.BLACK);

        // assert
        Pair<PieceType, Location[]> blackPieces[] = game.getBlackPieces();
        Assert.assertEquals(blackPieces.length, 6);
    }
}
