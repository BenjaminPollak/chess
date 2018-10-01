package control;

import model.game.Game;
import model.game.Model;
import model.game.PieceCaptured;
import model.pieces.Color;
import model.pieces.Piece;
import view.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Model _model;
    private int _numMoves = 0;
    private boolean _needToSelectPiece = true;
    private Piece _pieceToMove;
    private Square _originatingSq;
    private Square _targetSq;
    private JLabel _currentTurn;

    public final String _whiteMove = "WHITE'S MOVE";
    public final String _blackMove = "BLACK'S MOVE";

    public Controller(Model model, JLabel currentTurn) {
        _model = model;
        _currentTurn = currentTurn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square sq = (Square) e.getSource();
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();
        Game game = _model.getGame();

        if(_needToSelectPiece) {
            _originatingSq = sq;
            _pieceToMove = game.retrievePiece(xCoord, yCoord);
            if(_pieceToMove == null) return;
            else {
                if((_numMoves % 2) == 0) {
                    if(_pieceToMove.getColor() != Color.WHITE) return;
                    _needToSelectPiece = selectWhitePiece();
                }
                else {
                    if(_pieceToMove.getColor() != Color.BLACK) return;
                    _needToSelectPiece = selectBlackPiece();
                }
            }
        }
        else {
            _targetSq = sq;
            try {
                _pieceToMove.move(xCoord, yCoord, game.getBoard());
            } catch (PieceCaptured pieceException) {
            } catch (IllegalArgumentException argException) {
                _needToSelectPiece = true;
                return;
            }
            _targetSq.setText(_originatingSq.getText());
            _targetSq.setFont(new Font("Code2000", Font.PLAIN, 36));
            _originatingSq.setText("");
            _needToSelectPiece = true;
            ++_numMoves;
        }
        System.out.println(_numMoves);
        String display = (_numMoves % 2 == 0) ? _whiteMove : _blackMove;
        _currentTurn.setText(display);
    }

    public boolean selectWhitePiece() {
        if(_pieceToMove == null) {
            return true;
        }
        else if(_pieceToMove.getColor() == Color.WHITE) {
            return false;
        }
        return true;
    }

    public boolean selectBlackPiece() {
        if(_pieceToMove == null) {
            return true;
        }
        else if(_pieceToMove.getColor() == Color.BLACK) {
            return false;
        }
        return true;
    }
}
