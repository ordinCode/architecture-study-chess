package co.chess.chess.move.special.pawn.generalattack;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.move.Direction;
import co.chess.chess.move.general.GeneralMovePattern;

public class PawnGeneralAttack extends GeneralMovePattern {
    private final Direction direction;

    public PawnGeneralAttack(Direction direction) {
        this.direction = direction;
    }

    public static PawnGeneralAttack of(Tile source, Tile target) {
        return new PawnGeneralAttack(Direction.from(source, target));
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMoveCount() {
        return 1;
    }
}
