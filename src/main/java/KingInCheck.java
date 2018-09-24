public class KingInCheck extends RuntimeException {
    private Location _kingPosition;
    private Location _attackerPosition;
    private int _deltaX;
    private int _deltaY;
    private Direction _directionOfThreat;

    public KingInCheck(String message, Location kingPos, Location attackerPos) {
        super(message);
        _kingPosition = kingPos;
        _attackerPosition = attackerPos;
    }
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

    public Direction getDirectionOfThreat() {
        return _directionOfThreat;
    }

    public int getDeltaX() {
        return _deltaX;
    }

    public int getDeltaY() {
        return _deltaY;
    }

    public Location getKingLocation() {
        return _kingPosition;
    }

    public Location getAtkLocation() {
        return _attackerPosition;
    }
}

