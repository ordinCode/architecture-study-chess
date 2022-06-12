package co.architecture.chess.piece;

import co.architecture.chess.move.general.CrossMove;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.piece.config.GeneralMovePiece;

public class Bishop extends GeneralMovePiece {
    public Bishop(Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove;
    }
}
