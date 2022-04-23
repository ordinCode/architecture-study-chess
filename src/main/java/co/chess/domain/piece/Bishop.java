package co.chess.domain.piece;

import co.chess.domain.move.general.CrossMove;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.piece.config.GeneralMovePiece;

public class Bishop extends GeneralMovePiece {
    public Bishop(Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove;
    }
}
