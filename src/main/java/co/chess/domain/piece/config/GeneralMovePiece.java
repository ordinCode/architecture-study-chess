package co.chess.domain.piece.config;

import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.piece.PieceType;
import co.chess.domain.piece.Team;

public abstract class GeneralMovePiece extends Piece {
    public GeneralMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract boolean isAvailablePattern(GeneralMovePattern generalMove);
}
