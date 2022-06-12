package co.chess.chess.move.general;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;

import java.util.Arrays;

public class GeneralMovePatternFinder {
    public static GeneralMovePattern find(Tile source, Tile target) {
        return Arrays.stream(GeneralMoveType.values())
                .filter(generalPattern -> generalPattern.isConform(source, target))
                .map(validPattern -> validPattern.toMovePattern(source, target))
                .findAny()
                .orElseThrow(InvalidMovePatternException::new);
    }
}
