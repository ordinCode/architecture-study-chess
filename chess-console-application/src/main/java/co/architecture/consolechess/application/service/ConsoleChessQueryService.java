package co.architecture.consolechess.application.service;

import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.chess.ChessGame;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;
import co.architecture.jdbc.chessgame.ChessGamePersistenceAdaptor;
import co.architecture.consolechess.application.port.in.LoadChessGame;

public class ConsoleChessQueryService implements LoadChessGame {
    private final ChessGamePersistenceAdaptor chessGamePersistenceAdaptor;

    public ConsoleChessQueryService(ChessGamePersistenceAdaptor chessGamePersistenceAdaptor) {
        this.chessGamePersistenceAdaptor = chessGamePersistenceAdaptor;
    }

    @Override
    public void getChessGame() {
        ChessGameDto chessGameDto = chessGamePersistenceAdaptor.loadGame();
        ChessGame chessGame = ChessGameMapper.toChessGame(chessGameDto);
        ConsoleChessGameFactory.updateChessGame(chessGame);
    }
}
