package control;

import model.game.Game;
import model.game.PieceCaptured;
import model.pieces.Color;
import model.pieces.Piece;
import view.Gui;
import view.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Game _game;
    private int _numMoves = 0;
    private boolean _needToSelectPiece = true;
    private Piece _pieceToMove;
    private Square _originatingSq;
    private Square _targetSq;

    public Controller(Game game) {
        _game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square sq = (Square) e.getSource();
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();

        if(_needToSelectPiece) {
            _originatingSq = sq;
            _pieceToMove = _game.retrievePiece(xCoord, yCoord);
            if(_pieceToMove == null) return;
            else {
                if((_numMoves % 2) == 0) {
                    _needToSelectPiece = selectWhitePiece();
                }
                else {
                    _needToSelectPiece = selectBlackPiece();
                }
            }
        }
        else {
            _targetSq = sq;
            System.out.println("move");
            try {
                _pieceToMove.move(xCoord, yCoord, _game.getBoard()); // TODO: exceptions?
            } catch (PieceCaptured pieceException) {
            } catch (IllegalArgumentException argException) {
                _needToSelectPiece = true;
                return;
            }
            _targetSq.setText(_originatingSq.getText());
            _originatingSq.setText("");
            _needToSelectPiece = true;
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
