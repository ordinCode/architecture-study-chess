package co.architecture.piece;

import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.move.config.MovePattern;
import co.architecture.chess.move.special.knight.KnightMove;
import co.architecture.chess.piece.Knight;
import co.architecture.chess.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class KnightTest {
    @DisplayName("knight 패턴 생성 테스트")
    @ParameterizedTest
    @MethodSource("knightPatternCreateProvider")
    void knightPatternCreate(Tile source, Tile target) {
        Knight knight = new Knight(Team.BLACK);
        MovePattern movePattern = knight.findMovePattern(source, target, null);
        Assertions.assertThat(movePattern instanceof KnightMove).isTrue();
    }

    static Stream<Arguments> knightPatternCreateProvider() {
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
                        new Tile(Rank.TWO, File.C)),
                Arguments.arguments(
                        new Tile(Rank.EIGHT, File.H),
                        new Tile(Rank.SEVEN, File.F)),
                Arguments.arguments(
                        new Tile(Rank.SIX, File.B),
                        new Tile(Rank.FOUR, File.C)),
                Arguments.arguments(
                        new Tile(Rank.ONE, File.E),
                        new Tile(Rank.THREE, File.D))
        );
    }
}