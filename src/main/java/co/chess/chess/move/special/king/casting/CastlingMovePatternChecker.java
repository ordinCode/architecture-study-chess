package co.chess.chess.move.special.king.casting;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.piece.King;
import co.chess.chess.piece.Rook;
import co.chess.chess.piece.config.Piece;

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
