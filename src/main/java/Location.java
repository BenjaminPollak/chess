import javafx.util.Pair;

public class Location extends Pair<Integer, Integer> {
    public Location(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals (Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        Pair<Integer, Integer> outsidePair = (Location) object;

        if(outsidePair.getKey() != this.getKey()) return false;
        if(outsidePair.getValue() != this.getValue()) return false;
        return true;
    }
}
