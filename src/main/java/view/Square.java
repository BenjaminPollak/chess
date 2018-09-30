package view;

import control.Controller;
import model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private int _xCoord;
    private int _yCoord;

    public Square(int xCoord, int yCoord, ActionListener acl, Game game) {
        _xCoord = xCoord;
        _yCoord = yCoord;
        Insets buttonMargin = new Insets(0,0,0,0);
        setMargin(buttonMargin);

        Controller clr = new Controller(_xCoord, _yCoord, game);
        addActionListener(clr);
    }
}
