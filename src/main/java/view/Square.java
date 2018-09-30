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

    public Square(int xCoord, int yCoord, Game game, Controller clr) {
        _xCoord = xCoord;
        _yCoord = yCoord;
        Insets buttonMargin = new Insets(0,0,0,0);
        setMargin(buttonMargin);

        //clr = new Controller(_xCoord, _yCoord, game);
        addActionListener(clr);
    }

    public int getXCoord() {
        return _xCoord;
    }

    public int getYCoord() {
        return _yCoord;
    }
}
