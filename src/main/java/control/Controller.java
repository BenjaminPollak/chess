package control;

import model.game.Game;
import model.pieces.Color;
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
            _pieceToMove = _game.retrievePiece(xCoord, yCoord);
            if(_pieceToMove == null) return;
            else {
                if((_numMoves % 2) == 0) {
                    _selectPiece = selectWhitePiece();
                }
                else {
                    _selectPiece = selectBlackPiece();
                }
            }
        }
        else {
            // TODO
            System.out.println("move");
            _pieceToMove.move(xCoord, yCoord, _game.getBoard()); // TODO: exceptions?
            _selectPiece = true;
            ++_numMoves;
        }
    }

    public boolean selectWhitePiece() {
        if(_pieceToMove.getColor() == Color.WHITE) {
            return false;
        }
        return true;
    }

    public boolean selectBlackPiece() {
        if(_pieceToMove.getColor() == Color.BLACK) {
            return false;
        }
        return true;
    }
}
