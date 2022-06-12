package co.architecture.chess.piece;

import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.move.general.StraightMove;
import co.architecture.chess.piece.config.GeneralMovePiece;

public class Rook extends GeneralMovePiece {
    public Rook(Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof StraightMove;
    }
}
