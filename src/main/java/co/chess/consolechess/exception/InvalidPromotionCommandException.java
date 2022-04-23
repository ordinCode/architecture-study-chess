package co.chess.consolechess.exception;

public class InvalidPromotionCommandException extends InputException {

    public static final String MESSAGE = "프로모션 명령어 형식이 올바르지 않습니다. ex) promotion q";

    public InvalidPromotionCommandException() {
        super(MESSAGE);
    }
}
