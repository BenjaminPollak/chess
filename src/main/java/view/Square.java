package view;

import control.Controller;
import model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents one square on a chess board
 * @author Benjamin Pollak
 */
public class Square extends JButton {
    private int _xCoord;
    private int _yCoord;

    /**
     *  Square constructor
     * @param xCoord: horizontal position of square
     * @param yCoord: vertical position of square
     * @param game: game being played. Part of Model.
     * @param clr: controller, the C in MVC
     */
    public Square(int xCoord, int yCoord, Game game, Controller clr) {
        _xCoord = xCoord;
        _yCoord = yCoord;
        Insets buttonMargin = new Insets(0,0,0,0);
        setMargin(buttonMargin);

        addActionListener(clr);
    }

    public int getXCoord() {
        return _xCoord;
    }

    public int getYCoord() {
        return _yCoord;
    }
}
