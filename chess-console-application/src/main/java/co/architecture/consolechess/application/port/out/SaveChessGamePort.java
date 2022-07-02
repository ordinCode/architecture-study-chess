package co.architecture.consolechess.application.port.out;

import co.architecture.chess.ChessGame;

public interface SaveChessGamePort {
    void saveChessGame(ChessGame chessGame);
}
