import javax.swing.*;

public class Board extends JFrame{
    private int width;
    private int length;

    public Board(int _width, int _length) {
        if(_width < 8) {
            throw new IllegalArgumentException("Cannot have width < 8");
        }
        if(_length < 5) {
            throw new IllegalArgumentException("Cannot have length < 5");
        }

        width = _width;
        length = _length;
    }

    public static void main(String[] args) {
        Board board = new Board(5, 8);
        System.out.println("Hello world");
    }
}