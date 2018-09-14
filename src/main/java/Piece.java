abstract public class Piece {
    private int _x_coord;
    private int _y_coord;
    private boolean yet_to_move;

    public Piece(int x_coord, int y_coord, int board_length, int board_width) {
        if((x_coord < 0) || (y_coord < 0)) {
            throw new IllegalArgumentException();
        }

        if((x_coord >= board_width) || (y_coord >= board_length)) {
            throw new IllegalArgumentException();
        }

        _x_coord = x_coord;
        _y_coord = y_coord;
        yet_to_move = true;
    }

    abstract boolean move(int new_x_coord, int new_y_coord);
}
