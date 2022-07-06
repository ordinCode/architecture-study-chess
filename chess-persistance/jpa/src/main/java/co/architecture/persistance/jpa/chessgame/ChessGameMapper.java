package co.architecture.persistance.jpa.chessgame;

import co.architecture.application.port.out.dto.ChessGameDto;

public class ChessGameMapper {
    public static ChessGameDto toChessGameDto(ChessGameJpaEntity chessGameJdbcEntity) {
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

    public static ChessGameJpaEntity toJdbcEntity(ChessGameDto chessGame) {
        return new ChessGameJpaEntity(
                chessGame.getId(),
                chessGame.getChessRuleType(),
                chessGame.getGameState(),
                chessGame.getTurn(),
                ChessBoardConverter.toBoardPayload(chessGame.getBoard()),
                chessGame.getJustNowPawnJumpedTile()
        );
    }
}
