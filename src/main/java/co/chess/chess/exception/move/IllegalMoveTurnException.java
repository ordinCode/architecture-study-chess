package co.chess.chess.exception.move;

import co.chess.chess.piece.Team;

public class IllegalMoveTurnException extends MoveException {

    public IllegalMoveTurnException(Team team) {
        super(String.format("지금은 %s 차례입니다.", team.name()));
    }
}
