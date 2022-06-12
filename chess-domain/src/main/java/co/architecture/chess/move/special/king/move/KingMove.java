package co.architecture.chess.move.special.king.move;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.Direction;
import co.architecture.chess.move.general.GeneralMovePattern;
import co.architecture.chess.utils.TileUtil;

public class KingMove extends GeneralMovePattern {
    private final Direction direction;
    private final int moveCount;

    private KingMove(Direction direction, int moveCount) {
        this.direction = direction;
        this.moveCount = moveCount;
    }

    public static KingMove of(Tile source, Tile target) {
        return new KingMove(Direction.from(source, target), TileUtil.calculateCrossDistance(source, target));
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }
}
