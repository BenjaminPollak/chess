import org.junit.Test;
import org.junit.Assert;

public class TestBoard {
    // nonsense test
    @Test
    public void exampleTest() {
        Board board = new Board();
        boolean boardIsTrue = board.returnsTrue();
        Assert.assertTrue(boardIsTrue);
    }
}
