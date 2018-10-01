package model.pieces;// TODO: are pieces moved around board correctly?
import model.game.Board;
import model.game.KingInCheck;
import model.game.Location;
import model.game.PieceCaptured;

import static java.lang.Math.abs;

/**
 * Bishop implementation
 * @author Benjamin Pollak
 */
public class Bishop extends Piece {
    /**
     * Constructor for bishop
     * @param pieceLocation: location for new bishop
     * @param boardParameters: size of board
     * @param color: color of new bishop
     */
    public Bishop(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation,boardParameters, PieceType.BISHOP, color);
    }

    /**
     * Finds if king is in check
     * @param field: field to look for check on
     * @throws KingInCheck if king is indeed in check
     * @return nothing
     */
    public void findIfKingInCheck(Piece[][] field) throws KingInCheck {
        int xCoord = getLocation().getKey();
        int yCoord = getLocation().getValue();

        lookUpAndLeftForKing(field, xCoord - 1, yCoord - 1);
        lookDownAndLeftForKing(field, xCoord - 1, yCoord + 1);
        lookUpAndRightForKing(field, xCoord + 1, yCoord - 1);
        lookDownAndRightForKing(field, xCoord + 1, yCoord + 1);
    }

    /**
     *  Handles moving and attacking with bishops
     * @param xCoord: horizontal position where piece should be moved
     * @param yCoord: vertical position where piece should be moved
     * @param board: board that piece should be moved on
     * @throws PieceCaptured
     * @throws IllegalArgumentException
     * @return: the type of move performed
     */
    public MoveType move(int xCoord, int yCoord, Board board) throws PieceCaptured, IllegalArgumentException{
        Location oldLocation = getLocation();
        checkCoordinates(xCoord, yCoord, board.getBoardWidth(), board.getBoardLength());
        boolean isAnAttack = checkValidMove(xCoord, yCoord, board.getField());

        Piece[][] field = board.getField();
        field[xCoord][yCoord] = this;
        field[oldLocation.getKey()][oldLocation.getValue()] = null;
        setLocation(new Location(xCoord, yCoord));

        if(isAnAttack) {
            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        else {
            return MoveType.MOVE;
        }
    }

    /**
     * Checks that the movement made by the bishop is valid. Helper function for move()
     * @param newX: the new horizontal position
     * @param newY: the new vertical position
     * @param field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @return a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        Location oldLocation = super.getLocation();
        int oldX = oldLocation.getKey();
        int oldY = oldLocation.getValue();

        checkCoordinates(newX, newY, field.length, field[0].length);


        int deltaX = oldX - newX;
        int deltaY = oldY - newY;

        if(abs(deltaX) == abs(deltaY)) {
            boolean moveRight = deltaX < 0;
            boolean moveDown = deltaY < 0;

            int iterations = abs(deltaX);
            while(iterations > 1) {
                if(moveRight) ++oldX;
                else --oldX;

                if(moveDown) ++oldY;
                else --oldY;

                Piece impediment = field[oldX][oldY];

                if(impediment != null) throw new IllegalArgumentException();
                --iterations;
            }
            Piece impediment = field[newX][newY];
            if(impediment != null) {
                if(impediment.getColor() != getColor()) return true;
                else throw new IllegalArgumentException();
            }
            return false;
        }
        else throw new IllegalArgumentException();
    }
}
