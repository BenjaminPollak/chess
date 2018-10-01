import control.Controller;
import model.game.Model;
import org.junit.Assert;
import org.junit.Test;
import view.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class TestController {
    @Test
    public void testInstantiate() {
        Model ml = new Model("white", "black", 8, 8);
        JLabel currentTurn = new JLabel("WHITE'S MOVE");
        Controller clr = new Controller(ml, currentTurn, new Vector(), new Vector());

        Assert.assertTrue(true);
    }

    @Test
    public void testSelectBlackHappyPath() {
        Model ml = new Model("white", "black", 8, 8);
        JLabel currentTurn = new JLabel("WHITE'S MOVE");
        Controller clr = new Controller(ml, currentTurn, new Vector(), new Vector());

        boolean noPiece = clr.selectBlackPiece();
        Assert.assertTrue(noPiece);
    }

    @Test
    public void testSelectWhiteSadPath() {
        Model ml = new Model("white", "black", 8, 8);
        JLabel currentTurn = new JLabel("WHITE'S MOVE");
        Controller clr = new Controller(ml, currentTurn, new Vector(), new Vector());
        Gui gui = new Gui(8, 8);
        gui.guiRunner.run();

        boolean noPiece = clr.selectBlackPiece();
        Assert.assertTrue(noPiece);
    }
}
