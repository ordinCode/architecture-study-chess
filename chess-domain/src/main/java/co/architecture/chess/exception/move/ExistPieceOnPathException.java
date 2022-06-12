package co.architecture.chess.exception.move;

public class ExistPieceOnPathException extends MoveException {

    public static final String MESSAGE = "경로에 다른 말이 존재합니다.";

    public ExistPieceOnPathException() {
        super(MESSAGE);
    }
}
