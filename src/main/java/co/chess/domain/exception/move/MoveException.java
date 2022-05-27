package co.chess.domain.exception.move;

import co.chess.domain.exception.ChessException;

public class MoveException extends ChessException {
    public MoveException(String message) {
        super(message);
    }
}
