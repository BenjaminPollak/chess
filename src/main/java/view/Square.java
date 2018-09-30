package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton implements ActionListener {
    private int _xCoord;
    private int _yCoord;

    public Square(int xCoord, int yCoord) {
        _xCoord = xCoord;
        _yCoord = yCoord;
        Insets buttonMargin = new Insets(0,0,0,0);
        setMargin(buttonMargin);

        ActionListener al = new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.print(_xCoord + ", ");
                    System.out.println(_yCoord);
                }
        };
        addActionListener(al);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print(_xCoord + ", ");
        System.out.println(_yCoord);
    }
}
