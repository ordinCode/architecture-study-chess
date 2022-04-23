package co.chess.domain;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePattern;
import co.chess.domain.move.special.pawn.move.PawnMove;

import java.util.Optional;

public class PawnJumpRecorder {
    private static Tile justNowPawnJumpedTile = null;

    public static void recordPawnJumpedTileLatest(Tile target, MovePattern movePattern) {
        if (movePattern instanceof PawnMove) {
            PawnMove pawnForwardMove = (PawnMove) movePattern;
            if (pawnForwardMove.getMoveCount() == 2) {
                justNowPawnJumpedTile = target;
                return;
            }
            justNowPawnJumpedTile = null;
        }
    }

    public static Optional<Tile> getJustNowPawnJumpedTile() {
        return Optional.ofNullable(justNowPawnJumpedTile);
    }
}
