package co.architecture.move.special.king.casting;

import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.move.special.king.casting.CastlingType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CastlingTypeTest {
    @DisplayName("캐슬링 타입 생성 테스트")
    @ParameterizedTest
    @MethodSource("ofProvider")
    void of(Tile source, Tile target, CastlingType castlingType) {
        Assertions.assertThat(CastlingType.of(source, target)).isEqualTo(castlingType);
    }

    static Stream<Arguments> ofProvider() {
        return Stream.of(
                Arguments.of(TileFactory.from("e1"), TileFactory.from("g1"), CastlingType.WHITE_RIGHT),
                Arguments.of(TileFactory.from("e1"), TileFactory.from("c1"), CastlingType.WHITE_LEFT),
                Arguments.of(TileFactory.from("e8"), TileFactory.from("g8"), CastlingType.BLACK_RIGHT),
                Arguments.of(TileFactory.from("e8"), TileFactory.from("c8"), CastlingType.BLACK_LEFT)
        );
    }

    @DisplayName("캐슬링 타입 생성시 어디에서 부합하지 않으면 예외발생")
    @Test
    void ofWithException() {
        Assertions.assertThatThrownBy(()-> CastlingType.of(TileFactory.from("e2"), TileFactory.from("g1")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}