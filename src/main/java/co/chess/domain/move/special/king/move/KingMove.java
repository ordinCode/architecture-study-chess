package co.chess.domain.move.special.king.move;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.Direction;
import co.chess.domain.move.DirectionFactory;
import co.chess.domain.move.general.GeneralMovePattern;
import co.chess.domain.utils.TileUtil;

public class KingMove extends GeneralMovePattern {
    private final Direction direction;
    private final int moveCount;

    private KingMove(Direction direction, int moveCount) {
        this.direction = direction;
        this.moveCount = moveCount;
    }

    public static KingMove of(Tile source, Tile target) {
        return new KingMove(DirectionFactory.of(source, target), TileUtil.calculateCrossDistance(source, target));
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
