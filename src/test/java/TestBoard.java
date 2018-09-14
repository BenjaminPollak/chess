import org.junit.Test;
import org.junit.Assert;

public class TestBoard {
    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadWidth() {
        new Board(3, 8);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateBoardWithBadLength() {
        new Board(8, -2);
        Assert.fail();
    }

    @Test
    public void instantiateBoardWithGoodArgs() {
        new Board(8, 8);
        Assert.assertTrue(true);
    }
}
