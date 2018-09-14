public class Rook extends Piece {
    private int _x_coord;
    private int _y_coord;
    private boolean yet_to_move;

    public Rook(int x_coord, int y_coord, int board_width, int board_length) throws IllegalArgumentException {
        super(x_coord, y_coord, board_width, board_length);
        yet_to_move = true;
    }

    move_type move(int x_coord, int y_coord, int board_width, int board_length) throws IllegalArgumentException {
        if((x_coord != board_width) && (y_coord != board_length)) throw new IllegalArgumentException();
        return move_type.MOVE;
    }
}
