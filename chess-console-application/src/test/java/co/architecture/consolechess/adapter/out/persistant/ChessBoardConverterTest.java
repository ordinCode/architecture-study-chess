package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.King;
import co.architecture.chess.piece.Pawn;
import co.architecture.chess.piece.Queen;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ChessBoardConverterTest {
    @DisplayName("ChessBoard로 변환")
    @Test
    void name() {
        Map<String, String> boardMap = ChessBoardConverter.toBoardMap("a1p,b2Q,c3K,d6k");

        //then
        Assertions.assertThat(boardMap).containsAllEntriesOf(Map.of(
                "a1", "p",
                "b2", "Q",
                "c3", "K",
                "d6", "K"
        ));
    }

    @DisplayName("boardPayload로 변환")
    @Test
    void name2() {
        //given
        Map<String, String> chessBoard = new HashMap<>();
        chessBoard.put("a1", "p");
        chessBoard.put("b2", "Q");
        chessBoard.put("c3", "K");
        chessBoard.put("d6", "K");

        //when
        String boardPayload = ChessBoardConverter.toBoardPayload(chessBoard);

        //then
        Assertions.assertThat(boardPayload.split(ChessBoardConverter.DELIMITER))
                .containsAll(Arrays.asList("a1p", "b2Q", "c3K", "d6k"));
    }
}