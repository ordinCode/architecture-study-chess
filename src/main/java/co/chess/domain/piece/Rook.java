package co.chess.domain.piece;

import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.move.general.StraightMove;
import co.chess.domain.piece.config.GeneralMovePiece;

public class Rook extends GeneralMovePiece {
    public Rook(Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof StraightMove;
    }
}
