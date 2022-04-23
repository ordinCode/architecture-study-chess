package co.chess.domain.move.general;

import co.chess.domain.chessboard.tile.Tile;
import co.chess.domain.move.config.MovePatternCreator;

import java.util.function.BiFunction;

public enum GeneralMoveType {
    STRAIGHT(GeneralMovePatternChecker::isStraightPattern, StraightMove::of),
    CROSS(GeneralMovePatternChecker::isCrossPattern, CrossMove::of);

    private final BiFunction<Tile, Tile, Boolean> movePatternValidator;
    private final MovePatternCreator patternCreator;

    GeneralMoveType(BiFunction<Tile, Tile, Boolean> movePatternValidator, MovePatternCreator patternCreator) {
        this.movePatternValidator = movePatternValidator;
        this.patternCreator = patternCreator;
    }

    public boolean isConform(Tile source, Tile target) {
        return this.movePatternValidator.apply(source, target);
    }

    public GeneralMovePattern toMovePattern(Tile source, Tile target) {
        return (GeneralMovePattern) this.patternCreator.create(source, target);
    }
}
