package co.chess.domain.move.special.pawn.generalattack;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.Direction;
import co.chess.domain.move.DirectionFactory;
import co.chess.domain.move.general.GeneralMovePattern;

public class PawnGeneralAttack extends GeneralMovePattern {
    private final Direction direction;

    public PawnGeneralAttack(Direction direction) {
        this.direction = direction;
    }

    public static PawnGeneralAttack of(Tile source, Tile target) {
        return new PawnGeneralAttack(DirectionFactory.of(source, target));
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMoveCount() {
        return 1;
    }
}
