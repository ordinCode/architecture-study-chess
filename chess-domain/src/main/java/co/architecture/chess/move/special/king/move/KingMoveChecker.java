package co.architecture.chess.move.special.king.move;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.general.CrossMove;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.move.general.GeneralMovePatternFinder;
import co.architecture.chess.move.general.StraightMove;
import co.architecture.chess.piece.config.Piece;

import java.util.Map;

public class KingMoveChecker {
    private static final int MAX_KING_MOVE_RANGE = 1;

    public static boolean isConformKingMove(Tile source, Tile target, Map<Tile, Piece> board) {
        GeneralMovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        return (movePattern.getMoveCount() == MAX_KING_MOVE_RANGE &&
                (movePattern instanceof CrossMove || movePattern instanceof StraightMove));

    }
}
