package co.chess.domain.piece;

import co.chess.domain.move.general.CrossMove;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.move.general.StraightMove;
import co.chess.domain.piece.config.GeneralMovePiece;

public class Queen extends GeneralMovePiece {
    public Queen(Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove || movePattern instanceof StraightMove;
    }
}
