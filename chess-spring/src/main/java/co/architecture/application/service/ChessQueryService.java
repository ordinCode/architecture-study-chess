package co.architecture.application.service;

import co.architecture.application.port.in.GetChessGameQuery;
import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.persistance.jpa.chessgame.ChessGamePersistenceAdaptor;
import org.springframework.stereotype.Service;

@Service
public class ChessQueryService implements GetChessGameQuery {
    private final ChessGamePersistenceAdaptor chessGamePersistenceAdaptor;

    public ChessQueryService(ChessGamePersistenceAdaptor chessGamePersistenceAdaptor) {
        this.chessGamePersistenceAdaptor = chessGamePersistenceAdaptor;
    }

    @Override
    public ChessGameDto getChessGame(Long chessGameId) {
        return chessGamePersistenceAdaptor.loadGame(chessGameId);
    }
}
