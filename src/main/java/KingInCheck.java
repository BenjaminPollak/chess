public class KingInCheck extends RuntimeException {
    public KingInCheck(String message) {
        super(message);
    }
    public KingInCheck() {
        super("KING IN CHECK");
    }
}

