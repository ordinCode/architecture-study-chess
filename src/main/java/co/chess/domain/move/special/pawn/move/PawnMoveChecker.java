package co.chess.domain.move.special.pawn.move;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.general.GeneralMovePatternFinder;
import co.chess.domain.move.general.StraightMove;
import co.chess.domain.piece.Pawn;
import co.chess.domain.piece.config.Piece;

import java.util.Map;

public class PawnMoveChecker {
    public static boolean isConformPawnMove(Tile source, Tile target, Map<Tile, Piece> board) {
        MovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        if (!(movePattern instanceof StraightMove)) {
            return false;
        }

        if (board.get(target) != null) {
            return false;
        }

        StraightMove straightMove = (StraightMove) movePattern;
        Pawn sourcePawn = (Pawn) board.get(source);
        return sourcePawn.getCanForwardDirection() == straightMove.getDirection()
                && sourcePawn.getCanMoveCount(source) >= straightMove.getMoveCount();
    }
}
