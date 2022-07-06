package co.architecture.application.port.in;

import co.architecture.application.port.out.dto.ChessGameDto;

public interface GetChessGameQuery {
    ChessGameDto getChessGame(Long chessGameId);
}
