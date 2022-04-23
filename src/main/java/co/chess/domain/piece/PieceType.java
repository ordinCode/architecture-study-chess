package co.chess.domain.piece;

import co.chess.domain.piece.config.Piece;

import java.util.function.Function;

public enum PieceType {
    KING(King::new),
    QUEEN(Queen::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    ROOK(Rook::new),
    PAWN(Pawn::new);

    private final Function<Team, Piece> createPiece;

    PieceType(Function<Team, Piece> createPiece) {
        this.createPiece = createPiece;
    }

    public Piece toObj(Team team) {
        return this.createPiece.apply(team);
    }
}
