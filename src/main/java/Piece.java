abstract public class Piece {
    private int _x_coord;
    private int _y_coord;

    /*
     * things I could return:
     * N - tuple representing peice location
     * ? - bool representing if the piece attacked or not
     * Y - enum representing typical move, attack, en_passant, pawn_upgrade, castle, check, checkmate
     *                       0             1       2           3             4       5      6
     */
    public static enum move_type {
        MOVE,
        ATTACK,
        EN_PASSANT,
        PAWN_UPGRADE,
        CASTLE,
        CHECK,
        CHECKMATE
    }

    public Piece(int x_coord, int y_coord, int board_length, int board_width) {
        if((x_coord < 0) || (y_coord < 0)) {
            throw new IllegalArgumentException();
        }

        if((x_coord >= board_width) || (y_coord >= board_length)) {
            throw new IllegalArgumentException();
        }

        _x_coord = x_coord;
        _y_coord = y_coord;
    }

    abstract move_type move(int x_coord, int y_coord, int board_width, int board_length);
}
