import org.junit.Assert;
import org.junit.Test;
import view.Gui;

import javax.swing.*;
import java.awt.*;

public class TestGui {
    @Test
    public void createGuiTest() {
        Gui gui = new Gui(8, 8);
        Assert.assertTrue(true);
    }
}
