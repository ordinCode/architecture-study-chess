package co.chess.chess.move.special.pawn.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.Direction;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.utils.TileUtil;

public class PawnMove extends GeneralMovePattern {
    private final Direction direction;
    private final int moveCount;

    private PawnMove(Direction direction, int moveCount) {
        this.direction = direction;
        this.moveCount = moveCount;
    }

    public static PawnMove of(Tile source, Tile target) {
        return new PawnMove(
                Direction.from(source, target),
                TileUtil.calculateStraightDistance(source, target)
        );
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
