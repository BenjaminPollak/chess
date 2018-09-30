package control;

import model.game.Game;
import model.pieces.Piece;
import view.Gui;
import view.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Game _game;
    private int _xCoord;
    private int _yCoord;

    public Controller(int xCoord, int yCoord, Game game) {
        _xCoord = xCoord; _yCoord = yCoord;
        _game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square sq = (Square) e.getSource();
        Piece pc = _game.retrievePiece(_xCoord, _yCoord);
        if(pc != null) {
            System.out.println(((Square) e.getSource()).getText() + " " + pc.getPieceType());
        }
    }
}
