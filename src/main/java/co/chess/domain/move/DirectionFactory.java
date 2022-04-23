package co.chess.domain.move;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.general.GeneralMovePatternChecker;

public class DirectionFactory {
    public static Direction of(Tile source, Tile target) {
        if (GeneralMovePatternChecker.isCrossPattern(source, target)) {
            if (source.calculateFileDistance(target) < 0 && source.calculateRankDistance(target) < 0) {
                return Direction.UP_RIGHT;
            }
            if (source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) < 0) {
                return Direction.UP_LEFT;
            }
            if (source.calculateFileDistance(target) > 0 && source.calculateRankDistance(target) > 0) {
                return Direction.DOWN_LEFT;
            }
            return Direction.DOWN_RIGHT;
        }
        if (GeneralMovePatternChecker.isStraightPattern(source, target)) {
            if (source.isSameFile(target)) {
                if (source.calculateRankDistance(target) > 0) {
                    return Direction.DOWN;
                }
                return Direction.UP;
            }
            if (source.calculateFileDistance(target) > 0) {
                return Direction.LEFT;
            }
            return Direction.RIGHT;
        }
        throw new IllegalArgumentException("부합하는 Direction 을 찾을 수 없습니다.");
    }
}
