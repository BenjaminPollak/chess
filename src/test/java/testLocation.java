import org.junit.Assert;
import org.junit.Test;

public class testLocation {
    @Test
    public void happyTestEquals() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 2);
        Assert.assertTrue(firstLoc.equals(secondLoc));
    }

    @Test
    public void sadTestEquals() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 3);
        Assert.assertFalse(firstLoc == secondLoc);
    }
}
