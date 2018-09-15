public class Rook extends Piece {
    private int _x_coord;
    private int _y_coord;
    private boolean yet_to_move;

    public Rook(int x_coord, int y_coord, int board_width, int board_length) throws IllegalArgumentException {
        super(x_coord, y_coord, board_width, board_length);
        _x_coord = x_coord;
        _y_coord = y_coord;
        yet_to_move = true;
    }

    move_type move(int x_coord, int y_coord, Board board) throws IllegalArgumentException {
        int board_width = board.get_board_width();
        int board_length = board.get_board_length();

        if((x_coord != board_width) && (y_coord != board_length)) throw new IllegalArgumentException();
        checkCoordinates(x_coord, y_coord, board_width, board_length);
        return move_type.MOVE;
    }

    public int get_x_coord() {
        return _x_coord;
    }

    public int get_y_coord() {
        return _y_coord;
    }
}
