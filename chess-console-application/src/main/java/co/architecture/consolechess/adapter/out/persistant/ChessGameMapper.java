package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.chess.ChessGame;
import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.consolechess.adapter.out.persistant.entity.ChessGameJdbcEntity;
import com.google.gson.Gson;

public class ChessGameMapper {
    public static ChessGame toDomainEntity(ChessGameJdbcEntity chessGameJdbcEntity) {
        String boardPayload = chessGameJdbcEntity.getBoardPayload();
        String justNowPawnJumpedTilePayload = chessGameJdbcEntity.getJustNowPawnJumpedTilePayload();
        ChessBoard chessBoard = ChessBoardConverter.toChessBoard(boardPayload, justNowPawnJumpedTilePayload);
        return new ChessGame(
                chessGameJdbcEntity.getId(),
                chessGameJdbcEntity.getGameState(),
                chessGameJdbcEntity.getTurn(),
                chessBoard,
                chessGameJdbcEntity.getChessRuleType()
        );
    }

    public static ChessGameJdbcEntity toJdbcEntity(ChessGame chessGame) {
        return new ChessGameJdbcEntity(
                chessGame.getId(),
                chessGame.getChessRuleType(),
                chessGame.getGameState(),
                chessGame.getTurn(),
                ChessBoardConverter.toBoardPayload(chessGame.getChessBoard()),
                new Gson().toJson(chessGame.getChessBoard().getJustNowPawnJumpedTile())
        );
    }
}
