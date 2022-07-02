package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.chess.chessboard.tile.File;
import co.architecture.chess.chessboard.tile.Rank;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.piece.King;
import co.architecture.chess.piece.Pawn;
import co.architecture.chess.piece.Queen;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

class ChessBoardConverterTest {
    @DisplayName("ChessBoard로 변환")
    @Test
    void name() {
        //given
        Tile tile = new Tile(Rank.ONE, File.G);
        String tileJson = new Gson().toJson(tile);

        //when
        ChessBoard chessBoard = ChessBoardConverter.toChessBoard("a1p,b2Q,c3K,d6k", tileJson);

        //then
        Map<Tile, Piece> expectedBoardMap = getTilePieceMap();
        Assertions.assertThat(chessBoard).isEqualTo(new ChessBoard(expectedBoardMap, tile));
    }

    private Map<Tile, Piece> getTilePieceMap() {
        return Map.of(
                new Tile(Rank.ONE, File.A), new Pawn(Team.BLACK),
                new Tile(Rank.TWO, File.B), new Queen(Team.WHITE),
                new Tile(Rank.THREE, File.C), new King(Team.WHITE),
                new Tile(Rank.SIX, File.D), new King(Team.BLACK)
        );
    }

    @DisplayName("boardPayload로 변환")
    @Test
    void name2() {
        //given
        ChessBoard chessBoard = new ChessBoard(getTilePieceMap(), new Tile(Rank.ONE, File.F));

        //when
        String boardPayload = ChessBoardConverter.toBoardPayload(chessBoard);

        //then
        Assertions.assertThat(boardPayload.split(ChessBoardConverter.DELIMITER))
                .containsAll(Arrays.asList("a1p", "b2Q", "c3K", "d6k"));
    }
}