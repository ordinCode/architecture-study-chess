package co.architecture.consolechess.application.service;

import co.architecture.chess.ChessGame;
import co.architecture.consolechess.adapter.out.persistant.entity.ChessGamePersistenceAdaptor;
import co.architecture.consolechess.application.port.in.GetChessGameQuery;

public class ConsoleChessQueryService implements GetChessGameQuery {
    private final ChessGamePersistenceAdaptor chessGamePersistenceAdaptor;

    public ConsoleChessQueryService(ChessGamePersistenceAdaptor chessGamePersistenceAdaptor) {
        this.chessGamePersistenceAdaptor = chessGamePersistenceAdaptor;
    }

    @Override
    public ChessGame getChessGame() {
        return chessGamePersistenceAdaptor.loadGame();
    }
}
