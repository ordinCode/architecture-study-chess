package co.chess.chess.exception.move;

public class InvalidMovePatternException extends MoveException {

    public static final String MESSAGE = "유효하지 않은 이동방식입니다.";

    public InvalidMovePatternException() {
        super(MESSAGE);
    }
}
