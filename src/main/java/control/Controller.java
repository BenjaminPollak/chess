package control;

import model.game.Game;
import model.pieces.Piece;
import view.Gui;
import view.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Game _game;
    private int _numMoves = 0;
    private boolean _selectPiece = true;
    private Piece _pieceToMove;

    public Controller(Game game) {
        _game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square sq = (Square) e.getSource();
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();

        if(_selectPiece) {
            System.out.println("select");
            _pieceToMove = _game.retrievePiece(xCoord, yCoord);
            if(_pieceToMove == null) return;
            else {
                if((_numMoves % 2) == 0) {
                    selectWhitePiece();
                }
                else {
                    selectBlackPiece();
                }
            }
            _selectPiece = false;
        }
        else {
            System.out.println("move");
            _selectPiece = true;
            ++_numMoves;
        }
    }

    public void selectWhitePiece() {
        // TODO
        System.out.println("white");
    }

    public void selectBlackPiece() {
        // TODO
        System.out.println("black");
    }
}
