package co.chess.chess.piece;

import co.chess.chess.chessboard.tile.File;
import co.chess.chess.chessboard.tile.Rank;
import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.InvalidMovePatternException;
import co.chess.chess.piece.config.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class PawnTest {
    private static final Map<Tile, Piece> board = new HashMap<>();

    /**
     * 체스판
     * 8
     * 7                bp
     * 6 bp
     * 5    WP             WP bp
     * 4             bp WP(jumped)
     * 3       bp
     * 2          WP
     * 1
     *   a  b  c  d  e  f  g  h
     */
    @BeforeEach
    void setUp() {
        board.put(new Tile(Rank.SEVEN, File.F), new Pawn(Team.BLACK));

        board.put(new Tile(Rank.SIX, File.A), new Pawn(Team.BLACK));

        board.put(new Tile(Rank.FIVE, File.B), new Pawn(Team.WHITE));
        board.put(new Tile(Rank.FIVE, File.G), new Pawn(Team.WHITE));
        board.put(new Tile(Rank.FIVE, File.H), new Pawn(Team.BLACK));

        board.put(new Tile(Rank.FOUR, File.E), new Pawn(Team.BLACK));
        board.put(new Tile(Rank.FOUR, File.F), new Pawn(Team.WHITE));

        board.put(new Tile(Rank.THREE, File.C), new Pawn(Team.BLACK));
        board.put(new Tile(Rank.TWO, File.D), new Pawn(Team.WHITE));
    }

    @DisplayName("이동 유효성 검사에 통과하는 경우")
    @ParameterizedTest
    @MethodSource("validateMoveProvider")
    void name(Team team, Tile source, Tile target) {
        Pawn pawn = new Pawn(team);
        pawn.findMovePattern(source, target, board, null);
    }

    static Stream<Arguments> validateMoveProvider() {
        return Stream.of(
                //백팀 위로 한칸이동
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.B), new Tile(Rank.SIX, File.B)),
                //흑팀 아래로 한칸이동
                Arguments.arguments(Team.BLACK, new Tile(Rank.SIX, File.A), new Tile(Rank.FIVE, File.A)),
                //백팀 위로 두칸 이동
                Arguments.arguments(Team.WHITE, new Tile(Rank.TWO, File.D), new Tile(Rank.FOUR, File.D)),
                //스타트 라인 흑팀 아래로 두칸 이동
                Arguments.arguments(Team.BLACK, new Tile(Rank.SEVEN, File.F), new Tile(Rank.FIVE, File.F)),
                //스타트 라인 흑팀 아래로 한칸 이동
                Arguments.arguments(Team.BLACK, new Tile(Rank.SEVEN, File.F), new Tile(Rank.SIX, File.F))
        );
    }

    @DisplayName("[예외] 이동 유효성 검사에 통과하지 못하는 경우")
    @ParameterizedTest
    @MethodSource("notValidateMoveProvider")
    void findMovePattern2(Team team, Tile source, Tile target) {
        Pawn pawn = new Pawn(team);
        Assertions.assertThatThrownBy(() -> pawn.findMovePattern(source, target, board, null))
                .isInstanceOf(InvalidMovePatternException.class);
    }

    static Stream<Arguments> notValidateMoveProvider() {
        return Stream.of(
                //백팀 아래로 이동
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.B), new Tile(Rank.FOUR, File.B)),
                //흑팀 위로 이동
                Arguments.arguments(Team.BLACK, new Tile(Rank.SIX, File.A), new Tile(Rank.SEVEN, File.A)),
                //점프를 하지 못하는 백팀 폰 점프
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.B), new Tile(Rank.SEVEN, File.B)),
                //점프를 하지 못하는 흑팀 폰 점프
                Arguments.arguments(Team.BLACK, new Tile(Rank.SIX, File.A), new Tile(Rank.FOUR, File.A))
        );
    }

    @DisplayName("공격 유효성 검사에 통과하는 경우")
    @ParameterizedTest
    @MethodSource("validateAttackProvider")
    void name2(Team team, Tile source, Tile target, Map<Tile, Piece> board) {
        //given
        Pawn pawn = new Pawn(team);

        //then
        pawn.findMovePattern(source, target, board, new Tile(Rank.FOUR, File.F));
    }

    static Stream<Arguments> validateAttackProvider() {
        return Stream.of(
                //d2 백팀의 공격
                Arguments.arguments(Team.WHITE, new Tile(Rank.TWO, File.D), new Tile(Rank.THREE, File.C), board),
                //a6 흑팀의 공격
                Arguments.arguments(Team.BLACK, new Tile(Rank.SIX, File.A), new Tile(Rank.FIVE, File.B), board),
                //a6 흑팀의 공격 안하고 내려오기
                Arguments.arguments(Team.BLACK, new Tile(Rank.SIX, File.A), new Tile(Rank.FIVE, File.A), board),
                //e4 흑팀의 앙파상 공격
                Arguments.arguments(Team.BLACK, new Tile(Rank.FOUR, File.E), new Tile(Rank.THREE, File.F), board)
        );
    }

    @DisplayName("[예외] 공격 유효성 검사에 통과하지 못하는 경우")
    @ParameterizedTest
    @MethodSource("notValidateAttackProvider")
    void name3(Team team, Tile source, Tile target, Map<Tile, Piece> board) {
        Pawn pawn = new Pawn(team);
        Assertions.assertThatThrownBy(() -> pawn.findMovePattern(source, target, board, null))
                .isInstanceOf(InvalidMovePatternException.class);
    }

    static Stream<Arguments> notValidateAttackProvider() {
        return Stream.of(
                //g5 백팀이 가로로 공격
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.G), new Tile(Rank.FIVE, File.H), board),
                //g5 백팀이 적이 없는 타일 공격
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.G), new Tile(Rank.SIX, File.F), board),
                //g5 백팀이 방금 점프하지 않는 폰 공격
                Arguments.arguments(Team.WHITE, new Tile(Rank.FIVE, File.G), new Tile(Rank.SIX, File.H), board)
        );
    }
}