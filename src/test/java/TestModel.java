import model.game.Game;
import model.game.KingInCheck;
import model.game.Model;
import model.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

public class TestModel {
    @Test
    public void testInstantiate() {
        Model model = new Model("alice", "bob", 8, 8);
        Assert.assertTrue(model != null);
    }

    @Test
    public void testDiscoverCheckStateNoCheck() {
        Model model = new Model("alice", "bob", 8, 8);
        Game game = model.getGame();
        Piece piece = game.retrievePiece(0, 0);
        try{
            model.discoverCheckState(piece);
        } catch(KingInCheck e) {
            Assert.fail();
        }


        Assert.assertTrue(true);
    }
}
