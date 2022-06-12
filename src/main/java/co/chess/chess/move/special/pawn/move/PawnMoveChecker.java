package co.chess.chess.move.special.pawn.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.MovePattern;
import co.chess.chess.move.general.GeneralMovePatternFinder;
import co.chess.chess.move.general.StraightMove;
import co.chess.chess.piece.Pawn;
import co.chess.chess.piece.config.Piece;

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
