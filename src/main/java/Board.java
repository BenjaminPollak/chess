import javax.swing.*;

public class Board extends JFrame{
    // NOTE: top left corner is (0, 0).
    //       bottom right corner is (_board_width - 1, _board_length - 1)
    private int _board_width;
    private int _board_length;

    public Board(int board_width, int board_length) {
        if(board_width < 8) {
            throw new IllegalArgumentException("Cannot have board width < 8");
        }
        if(board_length < 5) {
            throw new IllegalArgumentException("Cannot have board length < 5");
        }

        _board_width = board_width;
        _board_length = board_length;
    }

    // getters
    public int get_board_width() {
        return _board_width;
    }

    public int get_board_length() {
        return _board_length;
    }
}