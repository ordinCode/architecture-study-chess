package co.chess.chess.piece.config;

import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.piece.PieceType;
import co.chess.chess.piece.Team;

public abstract class GeneralMovePiece extends Piece {
    public GeneralMovePiece(Team team, PieceType type) {
        super(team, type);
    }

    public abstract boolean isAvailablePattern(GeneralMovePattern generalMove);
}
