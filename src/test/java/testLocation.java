import org.junit.Assert;

public class testLocation {
    public void happyTestEquals() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 2);
        Assert.assertTrue(firstLoc == secondLoc);
    }

    public void sadTestEquals() {
        Location firstLoc = new Location(1, 2);
        Location secondLoc = new Location(1, 3);
        Assert.assertFalse(firstLoc == secondLoc);
    }
}
