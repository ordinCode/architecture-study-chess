package co.chess.chess.chessboard;

import co.chess.chess.chessboard.tile.File;
import co.chess.chess.chessboard.tile.Rank;
import co.chess.chess.chessboard.tile.Tile;
import co.chess.chess.exception.move.MoveException;
import co.chess.chess.move.general.CrossMove;
import co.chess.chess.move.general.GeneralMovePattern;
import co.chess.chess.move.general.StraightMove;
import co.chess.chess.piece.Bishop;
import co.chess.chess.piece.King;
import co.chess.chess.piece.Knight;
import co.chess.chess.piece.Pawn;
import co.chess.chess.piece.config.Piece;
import co.chess.chess.piece.Queen;
import co.chess.chess.piece.Rook;
import co.chess.chess.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class MovePathValidatorTest {
    private static final Map<Tile, Piece> board = new HashMap<>();

    /**
     * 체스판
     * 8 r  n  b  k     b     r
     * 7    p        p
     * 6          q
     * 5
     * 4
     * 3 P                    B
     * 2          P
     * 1 R        K  Q        R
     * - a  b  c  d  e  f  g  h
     */
    @BeforeEach
    void setUp() {
        board.put(new Tile(Rank.EIGHT, File.A), new Rook(Team.BLACK));
        board.put(new Tile(Rank.EIGHT, File.B), new Knight(Team.BLACK));
        board.put(new Tile(Rank.EIGHT, File.C), new Bishop(Team.BLACK));
        board.put(new Tile(Rank.EIGHT, File.D), new King(Team.BLACK));
        board.put(new Tile(Rank.EIGHT, File.F), new Bishop(Team.BLACK));
        board.put(new Tile(Rank.EIGHT, File.H), new Rook(Team.BLACK));

        board.put(new Tile(Rank.SEVEN, File.B), new Pawn(Team.BLACK));
        board.put(new Tile(Rank.SEVEN, File.E), new Pawn(Team.BLACK));

        board.put(new Tile(Rank.SIX, File.D), new Queen(Team.BLACK));

        board.put(new Tile(Rank.THREE, File.A), new Pawn(Team.WHITE));
        board.put(new Tile(Rank.THREE, File.H), new Bishop(Team.WHITE));

        board.put(new Tile(Rank.TWO, File.D), new Pawn(Team.WHITE));

        board.put(new Tile(Rank.ONE, File.A), new Rook(Team.WHITE));
        board.put(new Tile(Rank.ONE, File.D), new King(Team.WHITE));
        board.put(new Tile(Rank.ONE, File.E), new Queen(Team.WHITE));
        board.put(new Tile(Rank.ONE, File.H), new Rook(Team.WHITE));
    }

    @DisplayName("이동 경로에 다른 말이 없으면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("validatePathProvider")
    void validatePath(Tile source, GeneralMovePattern movePattern) {
        MovePathValidator.validatePath(source, movePattern, board);
    }

    static Stream<Arguments> validatePathProvider() {
        return Stream.of(
                //e1 퀸 g3 이동
                Arguments.of(new Tile(Rank.ONE, File.E), CrossMove.of(new Tile(Rank.ONE, File.E), new Tile(Rank.THREE, File.G))),
                //h8 룩 h3 이동
                Arguments.of(new Tile(Rank.EIGHT, File.H), StraightMove.of(new Tile(Rank.EIGHT, File.H), new Tile(Rank.THREE, File.H)))
        );
    }

    @DisplayName("[예외] 이동 경로에 다른 말이 존재하면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("notValidatePathProvider")
    void notValidatePath(Tile source, GeneralMovePattern movePattern) {
        Assertions.assertThatThrownBy(() -> MovePathValidator.validatePath(source, movePattern, board))
                .isInstanceOf(MoveException.class);
    }

    static Stream<Arguments> notValidatePathProvider() {
        return Stream.of(
                // a1 룩이 a5로 이동시 예외
                Arguments.of(new Tile(Rank.ONE, File.A), StraightMove.of(new Tile(Rank.ONE, File.A), new Tile(Rank.FIVE, File.A))),
                // d6 퀸이 d1로 이동시 얘외
                Arguments.of(new Tile(Rank.SIX, File.D), StraightMove.of(new Tile(Rank.SIX, File.D), new Tile(Rank.ONE, File.D))),
                // e1 퀸이 b4로 이동시 얘외
                Arguments.of(new Tile(Rank.ONE, File.E), CrossMove.of(new Tile(Rank.ONE, File.E), new Tile(Rank.FOUR, File.B)))
        );
    }


}