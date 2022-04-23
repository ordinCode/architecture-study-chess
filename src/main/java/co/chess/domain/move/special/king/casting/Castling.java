package co.chess.domain.move.special.king.casting;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.special.config.SpecialMovePattern;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class Castling extends SpecialMovePattern {
    private final CastlingType castlingType;

    private Castling(CastlingType castlingType) {
        this.castlingType = castlingType;
    }

    public static Castling of(Tile source, Tile target) {
        return new Castling(CastlingType.of(source, target));
    }

    @Override
    public void applySpecialMoveResult(Map<Tile, Piece> board) {
        this.castlingType.applySpecialMoveResult(board);
    }

    @Override
    public void validatePath(Tile source, Map<Tile, Piece> board) {
        castlingType.validatePath(board);
    }
}
