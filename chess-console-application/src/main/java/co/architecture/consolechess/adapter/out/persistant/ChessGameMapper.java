package co.architecture.consolechess.adapter.out.persistant;

import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.consolechess.adapter.out.persistant.entity.ChessGameJdbcEntity;

public class ChessGameMapper {
    public static ChessGameDto toChessGameDto(ChessGameJdbcEntity chessGameJdbcEntity) {
        String boardPayload = chessGameJdbcEntity.getBoardPayload();
        return new ChessGameDto(
                chessGameJdbcEntity.getId(),
                chessGameJdbcEntity.getChessRuleType(),
                chessGameJdbcEntity.getGameState(),
                chessGameJdbcEntity.getTurn(),
                ChessBoardConverter.toBoardMap(boardPayload),
                chessGameJdbcEntity.getJustNowPawnJumpedTilePayload()
        );
    }

    public static ChessGameJdbcEntity toJdbcEntity(ChessGameDto chessGame) {
        return new ChessGameJdbcEntity(
                chessGame.getId(),
                chessGame.getChessRuleType(),
                chessGame.getGameState(),
                chessGame.getTurn(),
                ChessBoardConverter.toBoardPayload(chessGame.getBoard()),
                chessGame.getJustNowPawnJumpedTile()
        );
    }
}
