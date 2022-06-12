package co.chess.chess.piece;

import co.chess.chess.move.general.CrossMove;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.StraightMove;
import co.chess.chess.piece.config.GeneralMovePiece;

public class Queen extends GeneralMovePiece {
    public Queen(Team team) {
        super(team, PieceType.QUEEN);
    }

    @Override
    public boolean isAvailablePattern(GeneralMovePattern movePattern) {
        return movePattern instanceof CrossMove || movePattern instanceof StraightMove;
    }
}
