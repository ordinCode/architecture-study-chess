package co.architecture.chess.move.general;

import co.architecture.chess.move.Direction;
import co.architecture.chess.chessboard.tile.Tile;

import java.util.Objects;

public class StraightMove extends GeneralMovePattern {
    private final Direction direction;
    private final int moveCount;

    private StraightMove(Direction direction, int moveCount) {
        this.direction = direction;
        this.moveCount = moveCount;
    }

    public static StraightMove of(Tile source, Tile target) {
        if (!GeneralMovePatternChecker.isStraightPattern(source, target)) {
            throw new IllegalArgumentException();
        }
        return new StraightMove(Direction.from(source, target), findCount(source, target));
    }

    private static int findCount(Tile source, Tile target) {
        return Math.abs(source.calculateFileDistance(target) + source.calculateRankDistance(target));
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
        StraightMove that = (StraightMove) o;
        return getMoveCount() == that.getMoveCount() && getDirection() == that.getDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirection(), getMoveCount());
    }
}
