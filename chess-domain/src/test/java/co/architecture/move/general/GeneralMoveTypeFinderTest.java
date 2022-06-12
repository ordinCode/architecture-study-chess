package co.architecture.move.general;

import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.general.CrossMove;
import co.architecture.chess.move.general.GeneralMovePatternFinder;
import co.architecture.chess.move.general.StraightMove;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GeneralMoveTypeFinderTest {
    @DisplayName("straight 패턴 생성 테스트")
    @ParameterizedTest
    @MethodSource("straightPatternCreateProvider")
    void straightPatternCreate(Tile source, Tile target) {
        MovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        Assertions.assertThat(movePattern).isEqualTo(StraightMove.of(source, target));
    }

    static Stream<Arguments> straightPatternCreateProvider() {
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
         *   a  b  c  d  e  f  g  h
         */
        return Stream.of(
                Arguments.arguments(
                        new Tile(Rank.EIGHT, File.F),
                        new Tile(Rank.SIX, File.F)),
                Arguments.arguments(
                        new Tile(Rank.ONE, File.F),
                        new Tile(Rank.THREE, File.F)),
                Arguments.arguments(
                        new Tile(Rank.TWO, File.A),
                        new Tile(Rank.TWO, File.H)),
                Arguments.arguments(
                        new Tile(Rank.TWO, File.G),
                        new Tile(Rank.TWO, File.B))
        );
    }

    @DisplayName("cross 패턴 생성 테스트")
    @ParameterizedTest
    @MethodSource("crossPatternCreateProvider")
    void crossPatternCreate(Tile source, Tile target) {
        MovePattern movePattern = GeneralMovePatternFinder.find(source, target);
        Assertions.assertThat(movePattern).isEqualTo(CrossMove.of(source, target));
    }

    static Stream<Arguments> crossPatternCreateProvider() {
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
         *   a  b  c  d  e  f  g  h
         */
        return Stream.of(
                Arguments.arguments(
                        new Tile(Rank.ONE, File.A),
                        new Tile(Rank.SEVEN, File.G)),
                Arguments.arguments(
                        new Tile(Rank.EIGHT, File.H),
                        new Tile(Rank.THREE, File.C)),
                Arguments.arguments(
                        new Tile(Rank.SIX, File.B),
                        new Tile(Rank.TWO, File.F)),
                Arguments.arguments(
                        new Tile(Rank.ONE, File.E),
                        new Tile(Rank.FOUR, File.B))
        );
    }
}