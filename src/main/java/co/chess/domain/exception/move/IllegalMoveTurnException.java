package co.chess.domain.exception.move;

import co.chess.domain.exception.MoveException;
import co.chess.domain.piece.Team;

public class IllegalMoveTurnException extends MoveException {

    public IllegalMoveTurnException(Team team) {
        super(String.format("지금은 %s 차례입니다.", team.name()));
    }
}
