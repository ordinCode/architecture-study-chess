package co.architecture.persistance.jpa.chessgame;

import co.architecture.application.port.out.LoadChessGamePort;
import co.architecture.application.port.out.SaveChessGamePort;
import co.architecture.application.port.out.dto.ChessGameDto;
import org.springframework.stereotype.Component;

@Component
public class ChessGamePersistenceAdaptor implements SaveChessGamePort, LoadChessGamePort {
    private final ChessGameRepository chessGameRepository;

    public ChessGamePersistenceAdaptor(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    @Override
    public void saveChessGame(ChessGameDto chessGamedto) {
        ChessGameJpaEntity chessGameJdbcEntity = ChessGameMapper.toJdbcEntity(chessGamedto);
        chessGameRepository.save(chessGameJdbcEntity);
    }

    @Override
    public ChessGameDto loadGame(Long id) {
        ChessGameJpaEntity jdbcEntity = chessGameRepository.getById(id);
        return ChessGameMapper.toChessGameDto(jdbcEntity);
    }
}
