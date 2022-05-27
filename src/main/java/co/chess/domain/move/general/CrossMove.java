package co.chess.domain.move.general;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.Direction;
import co.chess.domain.utils.TileUtil;

import java.util.Objects;

public class CrossMove extends GeneralMovePattern {
    private final Direction direction;
    private final int moveCount;

    private CrossMove(Direction direction, int moveCount) {
        this.direction = direction;
        this.moveCount = moveCount;
    }

    public static CrossMove of(Tile source, Tile target) {
        if (!GeneralMovePatternChecker.isCrossPattern(source, target)) {
            throw new IllegalArgumentException();
        }
        return new CrossMove(Direction.from(source, target), TileUtil.calculateCrossDistance(source, target));
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossMove that = (CrossMove) o;
        return getMoveCount() == that.getMoveCount() && getDirection() == that.getDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirection(), getMoveCount());
    }
}
