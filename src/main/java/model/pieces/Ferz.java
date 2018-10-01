package model.pieces;

import model.game.Board;
import model.game.KingInCheck;
import model.game.Location;
import model.game.PieceCaptured;

import static java.lang.Math.abs;

// TODO: disappearing from board?
/**
 * Ferz implementation. Like a bishop, but only goes one square
 * @author Benjamin Pollak
 */
public class Ferz extends Piece {
    /**
     * Constuctor for model.pieces.Ferz
     * @param pieceLocation: location for new ferz
     * @param boardParameters: size of board
     * @param color: color of piece
     */
    public Ferz(Location pieceLocation, Location boardParameters, Color color) {
        super(pieceLocation, boardParameters, PieceType.FERZ,color);
    }

    /**
     * Controls movement for ferz
     * @param xCoord: new x position
     * @param yCoord: new y position
     * @param board: board to move model.pieces.Ferz on
     */
    public MoveType move(int xCoord, int yCoord, Board board) throws IllegalArgumentException {
        boolean attacking =  checkValidMove(xCoord, yCoord, board.getField());
        if(attacking) {
            if(getColor() == Color.WHITE) throw new PieceCaptured(Color.BLACK, xCoord, yCoord);
            else throw new PieceCaptured(Color.WHITE, xCoord, yCoord);
        }
        return MoveType.MOVE;
    }

    /**
     * Checks that the movement made by the king is valid. Helper function for move()
     * @param newX: the new horizontal position
     * @param newY: the new vertical position
     * @param field: Where the piece is to be moved
     * @throws IllegalArgumentException wherea piece tries to go somewhere "out of bounds"
     * @return a boolean describing whether or not a piece is taken
     */
    public boolean checkValidMove(int newX, int newY, Piece[][] field) throws IllegalArgumentException {
        int oldX = getLocation().getKey(); int oldY = getLocation().getValue();
        int deltaX = newX - oldX; int deltaY = newY - oldY;

        if((abs(deltaX) != 1) || (abs(deltaY) != 1)) throw new IllegalArgumentException();

        if(field[newX][newY] != null) return true;
        return false;
    }

    /**
     * Checks that the movement made by the king is valid. Helper function for move()
     * @param field: Where the piece is to be moved
     * @return nothing
     */
    public void findIfKingInCheck(Piece[][] field) {
        int oldX = getLocation().getKey(); int oldY = getLocation().getValue();
        Piece potentialKing;
        Location kingLoc;
        try {
            kingLoc = new Location(oldX - 1, oldY - 1);
            potentialKing = field[oldX - 1][oldY - 1];
            if(potentialKing.getPieceType() == PieceType.KING) {
                if(potentialKing.getColor() == Color.BLACK) throw new KingInCheck(getLocation(), kingLoc);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            kingLoc = new Location(oldX - 1, oldY + 1);
            potentialKing = field[oldX - 1][oldY + 1];
            if(potentialKing.getPieceType() == PieceType.KING) {
                if(potentialKing.getColor() == Color.BLACK) throw new KingInCheck(getLocation(), kingLoc);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            kingLoc = new Location(oldX + 1, oldY - 1);
            potentialKing = field[oldX + 1][oldY - 1];
            if(potentialKing.getPieceType() == PieceType.KING) {
                if(potentialKing.getColor() == Color.BLACK) throw new KingInCheck(getLocation(), kingLoc);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
        try {
            kingLoc = new Location(oldX + 1, oldY + 1);
            potentialKing = field[oldX + 1][oldY + 1];
            if(potentialKing.getPieceType() == PieceType.KING) {
                if(potentialKing.getColor() == Color.BLACK) throw new KingInCheck(getLocation(), kingLoc);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {}
    }
}
