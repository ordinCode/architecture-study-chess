package co.architecture.consolechess.adapter.out.persistant.entity;

import co.architecture.chess.ChessGame;
import co.architecture.consolechess.adapter.out.persistant.ChessGameDao;
import co.architecture.consolechess.adapter.out.persistant.ChessGameMapper;
import co.architecture.consolechess.application.port.out.LoadChessGamePort;
import co.architecture.consolechess.application.port.out.SaveChessGamePort;

public class ChessGamePersistenceAdaptor implements SaveChessGamePort, LoadChessGamePort {
    private final ChessGameDao chessGameDao;

    public ChessGamePersistenceAdaptor(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    @Override
    public void saveChessGame(ChessGame chessGame) {
        ChessGameJdbcEntity chessGameJdbcEntity = ChessGameMapper.toJdbcEntity(chessGame);
        if (chessGameDao.existSavedGame()) {
            chessGameDao.update(chessGameJdbcEntity);
            return;
        }
        chessGameDao.insert(chessGameJdbcEntity);
    }

    @Override
    public ChessGame loadGame() {
        ChessGameJdbcEntity jdbcEntity = chessGameDao.load();
        return ChessGameMapper.toDomainEntity(jdbcEntity);
    }
}
