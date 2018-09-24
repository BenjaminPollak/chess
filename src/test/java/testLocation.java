import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class testLocation {
    @Test
    public void happyTestEquals() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 2);
        Assert.assertTrue(firstLoc.equals(secondLoc));
    }

    @Test
    public void sadTestEqualsDifferentPoints() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 3);
        Assert.assertFalse(firstLoc.equals(secondLoc));
    }

    @Test
    public void sadTestEqualsNull() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = null;
        Assert.assertFalse(firstLoc.equals(secondLoc));
    }

    @Test
    public void sadTestWrongObject() {
        Location firstLoc = new Location(1, 2);
        HashMap secondLoc = new HashMap<String, String>();
        Assert.assertFalse(firstLoc.equals(secondLoc));
    }
}
