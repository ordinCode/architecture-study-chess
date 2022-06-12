package co.architecture.chess.piece;

import co.architecture.chess.move.general.CrossMove;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.move.general.StraightMove;
import co.architecture.chess.piece.config.GeneralMovePiece;

public class Queen extends GeneralMovePiece {
    public Queen(Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove || movePattern instanceof StraightMove;
    }
}
