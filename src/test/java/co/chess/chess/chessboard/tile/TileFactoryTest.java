package co.chess.chess.chessboard.tile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TileFactoryTest {
    @DisplayName("string 으로 Tile 생성 테스트")
    @ParameterizedTest
    @MethodSource("fromProvider")
    void from(String string, Tile tile) {
        Assertions.assertThat(TileFactory.from(string)).isEqualTo(tile);
    }

    static Stream<Arguments> fromProvider() {
        return Stream.of(
                Arguments.arguments("a1", new Tile(Rank.ONE, File.A)),
                Arguments.arguments("b2", new Tile(Rank.TWO, File.B)),
                Arguments.arguments("c3", new Tile(Rank.THREE, File.C)),
                Arguments.arguments("d4", new Tile(Rank.FOUR, File.D)),
                Arguments.arguments("e5", new Tile(Rank.FIVE, File.E)),
                Arguments.arguments("f6", new Tile(Rank.SIX, File.F)),
                Arguments.arguments("g7", new Tile(Rank.SEVEN, File.G)),
                Arguments.arguments("h8", new Tile(Rank.EIGHT, File.H))
        );
    }

    @DisplayName("[예외] string 으로 Tile생성시 예외 발생하는 경우" +
            "- null 입력" +
            "- 2글자가 아닌 경우" +
            "- 매칭되는 tile 이 없는 경우")
    @ParameterizedTest
    @CsvSource(value = {"null", "a", "a12", "z1"}, nullValues = "null")
    void name(String input) {
        Assertions.assertThatThrownBy(() -> TileFactory.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}