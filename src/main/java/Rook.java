public class Rook extends Piece {
    private int _x_coord;
    private int _y_coord;
    private boolean yet_to_move;

    public Rook(int x_coord, int y_coord, int board_width, int board_length) throws IllegalArgumentException {
        super(x_coord, y_coord, board_width, board_length);
    }
    boolean move(int new_x_coord, int new_y_coord) {
        return true;
    }
}
