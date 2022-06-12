package co.chess.chess.piece;

import co.chess.chess.move.general.CrossMove;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.piece.config.GeneralMovePiece;

public class Bishop extends GeneralMovePiece {
    public Bishop(Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove;
    }
}
