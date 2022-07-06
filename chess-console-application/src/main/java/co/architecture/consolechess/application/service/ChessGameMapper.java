package co.architecture.consolechess.application.service;

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

class ChessGameMapper {
    static ChessGame toChessGame(ChessGameDto chessGameDto) {
        Map<Tile, Piece> board = BoardConverter.toTilePieceMap(chessGameDto.getBoard());
        ChessBoard chessBoard = new ChessBoard(board, TileFactory.from(chessGameDto.getJustNowPawnJumpedTile()));
        return new ChessGame(
                chessGameDto.getId(),
                GameState.valueOf(chessGameDto.getGameState()),
                Team.valueOf(chessGameDto.getTurn()),
                chessBoard,
                ChessRuleType.valueOf(chessGameDto.getChessRuleType())
        );
    }
}
