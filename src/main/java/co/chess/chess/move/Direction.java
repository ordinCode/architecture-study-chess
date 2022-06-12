package co.chess.chess.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.config.DirectionPredicate;
import co.chess.chess.move.general.GeneralMovePatternChecker;

import java.util.Arrays;

public enum Direction {
    UP(0, 1, ((source, target) -> GeneralMovePatternChecker.isStraightPattern(source, target) && source.isSameFile(target) && source.calculateRankDistance(target) < 0)),
    DOWN(0, -1, ((source, target) -> GeneralMovePatternChecker.isStraightPattern(source, target) && source.isSameFile(target) && source.calculateRankDistance(target) > 0)),
    LEFT(-1, 0, ((source, target) -> GeneralMovePatternChecker.isStraightPattern(source, target) && source.isSameRank(target) && source.calculateFileDistance(target) > 0)),
    RIGHT(1, 0, ((source, target) -> GeneralMovePatternChecker.isStraightPattern(source, target) && source.isSameRank(target) && source.calculateFileDistance(target) < 0)),
    UP_RIGHT(1, 1, ((source, target) -> GeneralMovePatternChecker.isCrossPattern(source, target) && source.calculateFileDistance(target) < 0 && source.calculateRankDistance(target) < 0)),
    UP_LEFT(-1, 1, ((source, target) -> GeneralMovePatternChecker.isCrossPattern(source, target) && source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) < 0)),
    DOWN_RIGHT(1, -1, ((source, target) -> GeneralMovePatternChecker.isCrossPattern(source, target) && source.calculateFileDistance(target) < 0 && source.calculateRankDistance(target) > 0)),
    DOWN_LEFT(-1, -1, ((source, target) -> GeneralMovePatternChecker.isCrossPattern(source, target) && source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) > 0));

    private final int xDegree;
    private final int yDegree;
    private final DirectionPredicate directionPredicate;

    Direction(int xDegree, int yDegree, DirectionPredicate directionPredicate) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        this.directionPredicate = directionPredicate;
    }

    public static Direction from(Tile source, Tile target) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.directionPredicate.test(source, target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("부합하는 Direction 을 찾을 수 없습니다."));
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }
}
