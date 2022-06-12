package co.architecture.chess.piece.config;

import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;

public abstract class GeneralMovePiece extends Piece {
    public GeneralMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract boolean isAvailablePattern(GeneralMovePattern generalMove);
}
