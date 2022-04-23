package co.chess.domain.move.special.king.casting;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.piece.King;
import co.chess.domain.piece.Rook;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class CastlingMovePatternChecker {
    public static boolean isConformCastling(Tile source, Tile target, Map<Tile, Piece> board) {
        if (!CastlingType.anyMatch(source, target)) {
            return false;
        }
        CastlingType castlingType = CastlingType.of(source, target);

        Piece king = board.get(castlingType.getKingSource());
        Piece rook = board.get(castlingType.getRookSource());
        return king instanceof King && king.isHaveNotEverMoved()
                && rook instanceof Rook && rook.isHaveNotEverMoved();
    }
}
