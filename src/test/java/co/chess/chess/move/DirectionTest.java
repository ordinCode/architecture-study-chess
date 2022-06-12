package co.chess.chess.move;

import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.chessboard.tile.TileFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DirectionTest {
    @DisplayName("direction 생성 테스트")
    @ParameterizedTest
    @MethodSource("formProvider")
    void from(Tile source, Tile target, Direction direction) {
        Assertions.assertThat(Direction.from(source, target)).isEqualTo(direction);
    }

    /**
     * 체스판
     * 8
     * 7
     * 6
     * 5
     * 4
     * 3
     * 2
     * 1
     * - a  b  c  d  e  f  g  h
     */
    static Stream<Arguments> formProvider() {
        return Stream.of(
                Arguments.of(TileFactory.from("d4"), TileFactory.from("d6"), Direction.UP),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("f6"), Direction.UP_RIGHT),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("f4"), Direction.RIGHT),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("f2"), Direction.DOWN_RIGHT),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("d1"), Direction.DOWN),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("b2"), Direction.DOWN_LEFT),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("b4"), Direction.LEFT),
                Arguments.of(TileFactory.from("d4"), TileFactory.from("b6"), Direction.UP_LEFT)
        );
    }
}