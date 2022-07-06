package co.architecture.application.service;

import co.architecture.application.port.in.ChessMoveUseCase;
import co.architecture.application.port.in.MovePieceCommand;
import co.architecture.application.port.in.PromotionCommand;
import co.architecture.application.port.out.LoadChessGamePort;
import co.architecture.application.port.out.SaveChessGamePort;
import co.architecture.application.port.out.dto.ChessGameDto;
import co.architecture.chess.ChessGame;
import co.architecture.chess.chessboard.tile.TileFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChessCommandService implements ChessMoveUseCase {
    private final SaveChessGamePort saveChessGamePort;
    private final LoadChessGamePort loadChessGamePort;

    public ChessCommandService(SaveChessGamePort saveChessGamePort, LoadChessGamePort loadChessGamePort) {
        this.saveChessGamePort = saveChessGamePort;
        this.loadChessGamePort = loadChessGamePort;
    }

    @Override
    @Transactional
    public void movePiece(Long chessGameId, MovePieceCommand movePieceCommand) {
        String source = movePieceCommand.getSource();
        String target = movePieceCommand.getTarget();

        ChessGameDto chessGameDto = loadChessGamePort.loadGame(chessGameId);

        ChessGame chessGame = ChessGameDtoConverter.toChessGame(chessGameDto);
        chessGame.move(TileFactory.from(source), TileFactory.from(target));

        saveChessGamePort.saveChessGame(ChessGameDtoConverter.toDto(chessGame));
    }

    @Override
    @Transactional
    public void promotion(Long chessGameId, PromotionCommand promotionCommand) {
        ChessGameDto chessGameDto = loadChessGamePort.loadGame(chessGameId);
        ChessGame chessGame = ChessGameDtoConverter.toChessGame(chessGameDto);
        chessGame.promotion(promotionCommand.getPieceType());
        saveChessGamePort.saveChessGame(ChessGameDtoConverter.toDto(chessGame));
    }
}
