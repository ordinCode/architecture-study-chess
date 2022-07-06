package co.architecture.application.service;

import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.chess.ChessGame;
import co.architecture.chess.GameState;
import co.architecture.chess.chessboard.ChessBoard;
import co.architecture.chess.chessboard.tile.Tile;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.chess.piece.PieceType;
import co.architecture.chess.piece.Team;
import co.architecture.chess.piece.config.Piece;
import co.architecture.chess.rule.ChessRuleType;
import co.architecture.chess.utils.BoardConverter;
import co.architecture.common.StringUtil;

import java.util.HashMap;
import java.util.Map;

class ChessGameDtoConverter {
    static ChessGame toChessGame(ChessGameDto chessGameDto) {
        Map<Tile, Piece> boardWithTile = BoardConverter.toTilePieceMap(chessGameDto.getBoard());
        String jumpedTile = chessGameDto.getJustNowPawnJumpedTile();
        ChessBoard chessBoard = new ChessBoard(boardWithTile, TileFactory.from(jumpedTile));
        return new ChessGame(
                chessGameDto.getId(),
                GameState.valueOf(chessGameDto.getGameState()),
                Team.valueOf(chessGameDto.getTurn()),
                chessBoard,
                ChessRuleType.valueOf(chessGameDto.getChessRuleType())
        );
    }

    static ChessGameDto toDto(ChessGame chessGame) {
        Map<String, String> board = BoardConverter.toStringStringMap(chessGame.getChessBoard().getBoard());
        return new ChessGameDto(
                chessGame.getId(),
                chessGame.getChessRuleType().name(),
                chessGame.getGameState().name(),
                chessGame.getTurn().name(),
                board,
                chessGame.getChessBoard().getJustNowPawnJumpedTile().toString()
        );
    }

}
