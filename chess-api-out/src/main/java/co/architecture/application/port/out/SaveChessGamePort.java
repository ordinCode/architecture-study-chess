package co.architecture.application.port.out;

import co.architecture.application.port.out.dto.ChessGameDto;

public interface SaveChessGamePort {
    void saveChessGame(ChessGameDto chessGame);
}
