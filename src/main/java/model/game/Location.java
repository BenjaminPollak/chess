package model.game;

import javafx.util.Pair;

/**
 * A type that is essential a cartesian tuple,
 * describing location on a two-dimensional grid
 */
public class Location extends Pair<Integer, Integer> {

    /**
     * location constructor
     * @param x: new x position
     * @param y: new y position
     */
    public Location(int x, int y) {
        super(x, y);
    }

    /**
     * Overrides equality operator for location
     * @param object: object being compared
     * @return: true if objects are "equal", false otherwise
     * @citation: https://www.sitepoint.com/implement-javas-equals-method-correctly/
     */
    @Override
    public boolean equals (Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        Location outsidePair = (Location) object;

        if(outsidePair.getKey() != this.getKey()) return false;
        if(outsidePair.getValue() != this.getValue()) return false;
        return true;
    }
}
