package co.architecture.consolechess.acceptance;

import co.architecture.chess.ChessGame;
import co.architecture.chess.GameState;
import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.exception.move.ExistPieceOnPathException;
import co.architecture.chess.exception.move.IllegalMoveTurnException;
import co.architecture.chess.exception.move.InvalidMovePatternException;
import co.architecture.chess.piece.Bishop;
import co.architecture.chess.piece.King;
import co.architecture.chess.piece.Knight;
import co.architecture.chess.piece.Pawn;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Queen;
import co.architecture.chess.piece.Rook;
import co.architecture.chess.piece.Team;
import co.architecture.chess.rule.defaultrule.DefaultChessRule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessAcceptanceTest {

    @TestFactory
    Stream<DynamicTest> dynamicTestStream() {
        /**
         * 체스판 결과
         * 8 r  n  b  q  k  b  n  r
         * 7 p  p  p  p  p  p  p  p
         * 6
         * 5
         * 4
         * 3
         * 2 P  P  P  P  P  P  P  P
         * 1 R  N  B  Q  K  B  N  R
         *   a  b  c  d  e  f  g  h
         */
        ChessGame chessGame = ChessGame.init(new DefaultChessRule());
        체크보드초기화됨(chessGame);

        return Stream.of(
                DynamicTest.dynamicTest("흑팀 먼저 시작시 턴 예외 발생", () ->
                        assertThatThrownBy(() -> chessGame.move(TileFactory.from("c7"), TileFactory.from("c6")))
                                .isInstanceOf(IllegalMoveTurnException.class)),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p  p  p  p  p  p  p  p
                 * 6
                 * 5
                 * 4       P
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 폰 점프", () -> {
                    String source = "c2";
                    String target = "c4";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    Piece sourcePiece = chessGame.getChessBoard().getBoard().get(TileFactory.from(source));
                    assertThat(sourcePiece).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p  p  p     p  p  p  p
                 * 6
                 * 5          p
                 * 4       P
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 폰 점프", () -> {
                    String source = "d7";
                    String target = "d5";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p  p  p     p  p  p  p
                 * 6
                 * 5       P  p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 폰 한칸전진", () -> {
                    String source = "c4";
                    String target = "c5";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p     p     p  p  p  p
                 * 6
                 * 5    p  P  p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 폰 새로 점프", () -> {
                    String source = "b7";
                    String target = "b5";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p     p     p  p  p  p
                 * 6
                 * 5    p  P  p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 오래전 jump한 pawn에게 앙파상 시도시 실패", () ->
                        assertThatThrownBy(() -> chessGame.move(TileFactory.from("c5"), TileFactory.from("d6")))
                                .isInstanceOf(InvalidMovePatternException.class)),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p     p     p  p  p  p
                 * 6    P
                 * 5          p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 방금 jump한 pawn에게 앙파상 공격 성공", () -> {
                    String source = "c5";
                    String target = "b6";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from("b5"))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 p           p  p  p  p
                 * 6    P  p
                 * 5          p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / 공격하지 않고 폰 전진", () -> {
                    String source = "c7";
                    String target = "c6";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7 P           p  p  p  p
                 * 6       p
                 * 5          p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 일반 공격", () -> {
                    String source = "b6";
                    String target = "a7";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    Piece pawn = chessGame.getChessBoard().getBoard().get(TileFactory.from(target));
                    assertThat(pawn instanceof Pawn).isTrue();
                    assertThat(pawn.getTeam()).isEqualTo(Team.WHITE);
                }),

                DynamicTest.dynamicTest("흑팀 / 룩 이동 / 경로에 기물이 있는 경우 예외발생", () ->
                        assertThatThrownBy(() -> chessGame.move(TileFactory.from("a8"), TileFactory.from("a6")))
                                .isInstanceOf(ExistPieceOnPathException.class)),

                DynamicTest.dynamicTest("흑팀 / 룩 이동 / 대각선으로 이동시 예외 발생", () ->
                        assertThatThrownBy(() -> chessGame.move(TileFactory.from("a8"), TileFactory.from("b7")))
                                .isInstanceOf(InvalidMovePatternException.class)),

                /**
                 * 체스판 결과
                 * 8    n  b  q  k  b  n  r
                 * 7 r           p  p  p  p
                 * 6       p
                 * 5          p
                 * 4
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B  Q  K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / 룩 공격", () -> {
                    String source = "a8";
                    String target = "a7";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Rook).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8    n  b  q  k  b  n  r
                 * 7 r           p  p  p  p
                 * 6       p
                 * 5          p
                 * 4 Q
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 퀸 이동", () -> {
                    String source = "d1";
                    String target = "a4";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Queen).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8    n  b  q  k  b  n  r
                 * 7 r           p  p  p  p
                 * 6
                 * 5       p  p
                 * 4 Q
                 * 3
                 * 2 P  P     P  P  P  P  P
                 * 1 R  N  B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / 폰 이동", () -> {
                    String source = "c6";
                    String target = "c5";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),


                /**
                 * 체스판 결과
                 * 8    n  b  q  k  b  n  r
                 * 7 r           p  p  p  p
                 * 6
                 * 5       p  p
                 * 4 Q
                 * 3       N
                 * 2 P  P     P  P  P  P  P
                 * 1 R     B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 나이트 이동", () -> {
                    String source = "b1";
                    String target = "c3";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Knight).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7             p  p  p  p
                 * 6
                 * 5       p  p
                 * 4 Q
                 * 3       N
                 * 2 P  P     P  P  P  P  P
                 * 1 R     B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / 룩이 다시 원위치", () -> {
                    String source = "a7";
                    String target = "a8";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Rook).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r  n  b  q  k  b  n  r
                 * 7             p  p  p  p
                 * 6
                 * 5       p  p
                 * 4 Q
                 * 3    P  N
                 * 2 P        P  P  P  P  P
                 * 1 R     B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / b2 폰 이동", () -> {
                    String source = "b2";
                    String target = "b3";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Pawn).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r     b  q  k  b  n  r
                 * 7             p  p  p  p
                 * 6 n
                 * 5       p  p
                 * 4 Q
                 * 3    P  N
                 * 2 P        P  P  P  P  P
                 * 1 R     B     K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / b8 나이트 이동", () -> {
                    String source = "b8";
                    String target = "a6";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Knight).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r     b  q  k  b  n  r
                 * 7             p  p  p  p
                 * 6 n
                 * 5       p  p
                 * 4 Q
                 * 3    P  N
                 * 2 P  B     P  P  P  P  P
                 * 1 R           K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / c1 비숍 이동", () -> {
                    String source = "c1";
                    String target = "b2";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Bishop).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r        q  k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n
                 * 5       p  p
                 * 4 Q
                 * 3    P  N
                 * 2 P  B     P  P  P  P  P
                 * 1 R           K  B  N  R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / c8 비숍 이동", () -> {
                    String source = "c8";
                    String target = "b7";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Bishop).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r        q  k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n
                 * 5       p  p
                 * 4 Q
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1 R           K  B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / g1 나이트 이동", () -> {
                    String source = "g1";
                    String target = "f3";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Knight).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n        q
                 * 5       p  p
                 * 4 Q
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1 R           K  B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / d8 퀸 이동", () -> {
                    String source = "d8";
                    String target = "d6";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Queen).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n        q
                 * 5       p  p
                 * 4 Q
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1 R           K  B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 중간에 기물이 있는 상태에서 캐슬링 시도시 예외발생", () -> {
                    String source = "e1";
                    String target = "g1";
                    assertThatThrownBy(() ->
                            chessGame.move(TileFactory.from(source), TileFactory.from(target)))
                            .isInstanceOf(ExistPieceOnPathException.class);
                }),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n        q
                 * 5       p  p
                 * 4 Q
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1       K  R     B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 캐슬링", () -> {
                    String source = "e1";
                    String target = "c1";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    Piece king = chessGame.getChessBoard().getBoard().get(TileFactory.from("c1"));
                    Piece rook = chessGame.getChessBoard().getBoard().get(TileFactory.from("d1"));
                    assertThat(king instanceof King).isTrue();
                    assertThat(rook instanceof Rook).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6 n        q
                 * 5       p  p
                 * 4 Q
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1       K  R     B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / 룩이 이미 한칸 움직이 상태에서 캐슬링 시도시 예외발생", () ->
                        assertThatThrownBy(() -> chessGame.move(TileFactory.from("e8"), TileFactory.from("c8")))
                                .isInstanceOf(InvalidMovePatternException.class)),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6          q
                 * 5       p  p
                 * 4 Q  n
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1       K  R     B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("흑팀 / a6 나이트 이동", () -> {
                    String source = "a6";
                    String target = "b4";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Knight).isTrue();
                }),

                /**
                 * 체스판 결과
                 * 8 r           k  b  n  r
                 * 7    b        p  p  p  p
                 * 6          q
                 * 5       p  p
                 * 4 Q  n
                 * 3    P  N        N
                 * 2 P  B     P  P  P  P  P
                 * 1       K  R     B     R
                 *   a  b  c  d  e  f  g  h
                 */
                DynamicTest.dynamicTest("백팀 / 승리", () -> {
                    String source = "a4";
                    String target = "e8";
                    chessGame.move(TileFactory.from(source), TileFactory.from(target));

                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(source))).isNull();
                    assertThat(chessGame.getChessBoard().getBoard().get(TileFactory.from(target)) instanceof Queen).isTrue();
                    assertThat(chessGame.isEnd()).isTrue();
                })
        );
    }

    private void 체크보드초기화됨(ChessGame chessGame) {
        Assertions.assertThat(chessGame.getGameState()).isEqualTo(GameState.RUNNING);
        Assertions.assertThat(chessGame.getTurn()).isEqualTo(Team.WHITE);

        Map<Tile, Piece> board = chessGame.getChessBoard().getBoard();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.A)).equalsBy(Team.WHITE, PieceType.ROOK)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.B)).equalsBy(Team.WHITE, PieceType.KNIGHT)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.C)).equalsBy(Team.WHITE, PieceType.BISHOP)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.D)).equalsBy(Team.WHITE, PieceType.QUEEN)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.E)).equalsBy(Team.WHITE, PieceType.KING)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.F)).equalsBy(Team.WHITE, PieceType.BISHOP)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.G)).equalsBy(Team.WHITE, PieceType.KNIGHT)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.ONE, File.H)).equalsBy(Team.WHITE, PieceType.ROOK)).isTrue();
        for (File file : File.values()) {
            Assertions.assertThat(board.get(new Tile(Rank.TWO, file)).equalsBy(Team.WHITE, PieceType.PAWN)).isTrue();
        }

        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.A)).equalsBy(Team.BLACK, PieceType.ROOK)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.B)).equalsBy(Team.BLACK, PieceType.KNIGHT)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.C)).equalsBy(Team.BLACK, PieceType.BISHOP)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.D)).equalsBy(Team.BLACK, PieceType.QUEEN)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.E)).equalsBy(Team.BLACK, PieceType.KING)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.F)).equalsBy(Team.BLACK, PieceType.BISHOP)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.G)).equalsBy(Team.BLACK, PieceType.KNIGHT)).isTrue();
        Assertions.assertThat(board.get(new Tile(Rank.EIGHT, File.H)).equalsBy(Team.BLACK, PieceType.ROOK)).isTrue();
        for (File file : File.values()) {
            Assertions.assertThat(board.get(new Tile(Rank.SEVEN, file)).equalsBy(Team.BLACK, PieceType.PAWN)).isTrue();
        }
    }
}
