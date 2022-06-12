package co.chess.chess.piece;

import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.StraightMove;
import co.chess.chess.piece.config.GeneralMovePiece;

public class Rook extends GeneralMovePiece {
    public Rook(Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof StraightMove;
    }
}
