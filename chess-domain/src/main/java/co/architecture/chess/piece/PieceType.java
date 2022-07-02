package co.architecture.chess.piece;

import co.architecture.chess.piece.config.Piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    KING(King::new, "k"),
    QUEEN(Queen::new, "q"),
    BISHOP(Bishop::new, "b"),
    KNIGHT(Knight::new, "n"),
    ROOK(Rook::new, "r"),
    PAWN(Pawn::new, "p");

    private final Function<Team, Piece> createPiece;
    private final String symbol;

    PieceType(Function<Team, Piece> createPiece, String symbol) {
        this.createPiece = createPiece;
        this.symbol = symbol;
    }

    public Piece toObj(Team team) {
        return this.createPiece.apply(team);
    }

    public static PieceType ofSymbol(String symbol) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.getSymbol().equalsIgnoreCase(symbol))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getSymbol() {
        return symbol;
    }
}
