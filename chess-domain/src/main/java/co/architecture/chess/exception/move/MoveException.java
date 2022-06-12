package co.architecture.chess.exception.move;

import co.architecture.chess.exception.ChessException;

public class MoveException extends ChessException {
    public MoveException(String message) {
        super(message);
    }
}
