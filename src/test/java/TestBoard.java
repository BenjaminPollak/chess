import org.junit.Test;
import org.junit.Assert;

import static org.hamcrest.core.Is.is;

public class TestBoard {
    @Test
    public void badBoardLength() {
        try {
            Board board = new Board(8, -2);
            Assert.assertTrue(false);
        } catch(IllegalArgumentException e) {
            Assert.assertThat(e.getMessage(), is("Cannot have length < 5"));
        }
    }
    @Test
    public void badBoardWidth() {
        try {
            Board board = new Board(3, 8);
            Assert.assertTrue(false);
        } catch(IllegalArgumentException e) {
            Assert.assertThat(e.getMessage(), is("Cannot have width < 8"));
        }
    }
}
