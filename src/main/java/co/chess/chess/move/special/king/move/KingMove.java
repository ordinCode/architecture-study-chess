package co.chess.chess.move.special.king.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.Direction;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.utils.TileUtil;

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
