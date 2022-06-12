package co.chess.chess.move.special.king.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.general.CrossMove;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.GeneralMovePatternFinder;
import co.chess.chess.move.general.StraightMove;
import co.chess.chess.piece.config.Piece;

import java.util.Map;

public class KingMoveChecker {
    private static final int MAX_KING_MOVE_RANGE = 1;

    public static boolean isConformKingMove(Tile source, Tile target, Map<Tile, Piece> board) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        return (movePattern.getMoveCount() == MAX_KING_MOVE_RANGE &&
                (movePattern instanceof CrossMove || movePattern instanceof StraightMove));

    }
}
