package co.architecture.consolechess.adapter.out.persistant.entity;

import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.consolechess.adapter.out.persistant.ChessGameDao;
import co.architecture.consolechess.adapter.out.persistant.ChessGameMapper;
import co.architecture.application.port.out.LoadChessGamePort;
import co.architecture.application.port.out.SaveChessGamePort;

public class ChessGamePersistenceAdaptor implements SaveChessGamePort, LoadChessGamePort {
    private final ChessGameDao chessGameDao;

    public ChessGamePersistenceAdaptor(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    @Override
    public void saveChessGame(ChessGameDto chessGamedto) {
        ChessGameJdbcEntity chessGameJdbcEntity = ChessGameMapper.toJdbcEntity(chessGamedto);
        if (chessGameDao.existSavedGame()) {
            chessGameDao.update(chessGameJdbcEntity);
            return;
        }
        chessGameDao.insert(chessGameJdbcEntity);
    }

    @Override
    public ChessGameDto loadGame() {
        ChessGameJdbcEntity jdbcEntity = chessGameDao.load();
        return ChessGameMapper.toChessGameDto(jdbcEntity);
    }
}
