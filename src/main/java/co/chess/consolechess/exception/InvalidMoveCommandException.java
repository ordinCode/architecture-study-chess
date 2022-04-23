package co.chess.consolechess.exception;

public class InvalidMoveCommandException extends InputException {

    public static final String MESSAGE = "이동 명령어 형식이 올바르지 않습니다. ex) move a1 a3";

    public InvalidMoveCommandException() {
        super(MESSAGE);
    }
}
