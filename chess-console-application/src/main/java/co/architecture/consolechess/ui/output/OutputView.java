package co.architecture.consolechess.ui.output;

import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.common.StringUtil;
import co.architecture.consolechess.dto.ChessBoardDto;
import co.architecture.consolechess.dto.PieceDto;
import co.architecture.consolechess.dto.TileDto;
import co.architecture.consolechess.utils.PieceSymbolConverter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String INTRO = "> 체스 게임을 시작합니다.\n" +
            "종료는 end 명령을 입력하세요.";
    public static final String GAME_MOVE_GUIDE = "게임 이동 : move source위치 target위치 - 예. move b2 b3\n" +
            "**캐슬링 : move kingSource kingTarget - 예. move e8 g8";
    public static final String PROMOTION_GUIDE = "프로모션을 진행해주세요\n" +
            "바꿀 기물을 'q, r, b, n' 중에서 선택해주세요.\n" +
            "명령어 예시) promotion q";

    private static final String NONE_PIECE_MARK = ".";
    private static final String PADDING = " ";

    public static void printIntro() {
        System.out.println(INTRO);
    }

    public static void printMoveGuide() {
        System.out.println(GAME_MOVE_GUIDE);
    }

    public static void promotionGuide() {
        System.out.println(PROMOTION_GUIDE);
    }

    public static void printBoardState(ChessBoardDto chessBoardDto) {
        Map<TileDto, PieceDto> tilePieceMap = chessBoardDto.getTilePieceMap();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .forEach(rank -> stringBuilder.append(toStringRankState(tilePieceMap, rank)));
        System.out.println(stringBuilder);
    }

    private static String toStringRankState(Map<TileDto, PieceDto> tilePieceMap, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            String tilePieceValue = Optional.ofNullable(tilePieceMap.get(new TileDto(rank, file)))
                    .map(PieceSymbolConverter::toSymbol)
                    .orElse(NONE_PIECE_MARK);
            stringBuilder.append(tilePieceValue).append(PADDING);
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static void printGameOver(String winner) {
        if (StringUtil.isBlank(winner)) {
            System.out.printf("우승자는 %s팀\n", winner);
        }
        System.out.println("게임을 종료합니다.");
    }
}
