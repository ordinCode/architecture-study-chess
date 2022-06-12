package co.architecture.chess.move.special.king.casting;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.King;
import co.architecture.chess.piece.Rook;
import co.architecture.chess.piece.config.Piece;

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
