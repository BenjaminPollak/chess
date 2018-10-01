package control;

import model.game.*;
import model.pieces.Color;
import model.pieces.Piece;
import model.pieces.PieceType;
import view.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * The C in MVC
 */
public class Controller implements ActionListener {
    private Model _model;
    private int _numMoves = 0;
    private boolean _needToSelectPiece = true;
    private boolean _kingInCheck = false;
    private Piece _pieceToMove;
    private Square _originatingSq;
    private Square _targetSq;
    private JLabel _currentTurn;
    private Vector<Component> _whiteComponents;
    private Vector<Component> _blackComponents;

    public final String _whiteMove = "WHITE'S MOVE";
    public final String _blackMove = "BLACK'S MOVE";

    /**
     * Constructor for the controller
     * @param model: game model, M in MVC
     * @param currentTurn: integer representing turn number
     * @param whiteInfoComponents: metadata about white player
     * @param blackInfoComponents: metadata about black player
     */
    public Controller(Model model, JLabel currentTurn, Vector<Component> whiteInfoComponents, Vector<Component> blackInfoComponents) {
        _model = model;
        _currentTurn = currentTurn;
        _whiteComponents = whiteInfoComponents;
        _blackComponents = blackInfoComponents;
    }

    /**
     * Selects a piece to be moved
     * @param sq: square that was clicked
     * @param game: the game that is being played, part of the model
     */
    private void selectPiece(Square sq, Game game) {
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();
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

    /**
     * Handles logic of moving piece
     * @param sq: square to move to
     * @param game: game being played, part of the model
     */
    private void movePiece(Square sq, Game game) {
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();
        _targetSq = sq;
        try {
            if(_kingInCheck) {
                if(_pieceToMove.getPieceType() != PieceType.KING)
                    return;
                else {
                    int kingX = _pieceToMove.getLocation().getKey();
                    int kingY = _pieceToMove.getLocation().getValue();
                    boolean stillInCheck = Game.canMovePieceTo(kingX, kingY, game.getBoard(), (PieceCollection)game.getBoard().getBlackPieces());
                    if(stillInCheck) {
                        _needToSelectPiece = true;
                        return;
                    }
                }
            }
            _pieceToMove.move(xCoord, yCoord, game.getBoard());
            _model.discoverCheckState(_pieceToMove);
        } catch (PieceCaptured pieceException) {
        } catch (KingInCheck checkException) {
            _kingInCheck = true;
            Color colorInCheck = (_numMoves % 2 == 0) ? Color.BLACK : Color.WHITE;
            if(colorInCheck == Color.WHITE) {
                for(Component c: _whiteComponents) {
                    if(c.getName().equals("WCHECK")) {
                        JTextArea ca = (JTextArea)c;
                        ca.setText("TRUE");
                    }
                }
            }
            else {
                // TODO: make note of check
            }
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

    /**
     *  Action performed by controller
     * @param e: ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Square sq = (Square) e.getSource();
        int xCoord = sq.getXCoord();
        int yCoord = sq.getYCoord();
        Game game = _model.getGame();

        if(_needToSelectPiece) {
            selectPiece(sq, game);
        }
        else {
            if(xCoord == _originatingSq.getXCoord()) {
                if(yCoord == _originatingSq.getYCoord()) {
                    _needToSelectPiece = true;
                    return;
                }
            }
            movePiece(sq, game);
        }
        String display = (_numMoves % 2 == 0) ? _whiteMove : _blackMove;
        _currentTurn.setText(display);
    }

    /**
     * Indicates whether a white piece can be selected
     * @return whether or not the piece to move exists & is of the right color
     */
    public boolean selectWhitePiece() {
        if(_pieceToMove == null) {
            return true;
        }
        else if(_pieceToMove.getColor() == Color.WHITE) {
            return false;
        }
        return true;
    }

    /**
     * Indicates whether a black piece can be selected
     * @return whether or not the piece to move exists & is of the right color
     */
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
