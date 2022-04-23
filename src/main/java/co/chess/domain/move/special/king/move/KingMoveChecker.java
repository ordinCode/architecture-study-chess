package co.chess.domain.move.special.king.move;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.general.CrossMove;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.move.general.GeneralMovePatternFinder;
import co.chess.domain.move.general.StraightMove;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class KingMoveChecker {
    private static final int MAX_KING_MOVE_RANGE = 1;

    public static boolean isConformKingMove(Tile source, Tile target, Map<Tile, Piece> board) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        return (movePattern.getMoveCount() == MAX_KING_MOVE_RANGE &&
                (movePattern instanceof CrossMove || movePattern instanceof StraightMove));

    }
}
