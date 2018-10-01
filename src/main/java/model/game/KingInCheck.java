package model.game;

import model.pieces.Direction;

/**
 * KingInCheck runtime exception, for detecting a check situation
 * @author Benjamin Pollak
 */

public class KingInCheck extends RuntimeException {
    private Location _kingPosition;
    private Location _attackerPosition;
    private Direction _directionOfThreat;

    /**
     * Constructor for model.game.KingInCheck exception
     * @param kingPos: position of king
     * @param attackerPos: position of attacker
     */
    public KingInCheck(Location kingPos, Location attackerPos) {
        super("KING IN CHECK");
        _kingPosition = kingPos;
        _attackerPosition = attackerPos;

        int kingX = kingPos.getKey();
        int kingY = kingPos.getValue();
        int attackerX = attackerPos.getKey();
        int attackerY = attackerPos.getValue();

        int deltaX = kingX - attackerX;
        int deltaY = kingY - attackerY;

        if((deltaX != 0) && (deltaY != 0)) {
            if(deltaX < 0) _directionOfThreat = (deltaY < 0) ? Direction.SOUTHEAST : Direction.NORTHEAST;
            else _directionOfThreat = (deltaY < 0) ? Direction.SOUTHWEST : Direction.NORTHWEST;
        }
        else {
            if(deltaX == 0) _directionOfThreat = (deltaY < 0) ? Direction.SOUTH: Direction.NORTH;
            else _directionOfThreat = (deltaX < 0) ? Direction.EAST: Direction.WEST;
        }
    }

    /**
     * gets _directionOfThreat member variable
     */
    public Direction getDirectionOfThreat() {
        return _directionOfThreat;
    }

    /**
     * gets _kingPosition member variable
     */
    public Location getKingLocation() {
        return _kingPosition;
    }

    /**
     * gets _attackerPosition member variable
     */
    public Location getAtkLocation() {
        return _attackerPosition;
    }
}

