package co.architecture.consolechess.application.service;

import co.architecture.chess.ChessGame;
import co.architecture.chess.chessboard.tile.TileFactory;
import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;
import co.architecture.consolechess.application.port.out.SaveChessGamePort;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;

public class ConsoleChessService implements ChessMoveUseCase {
    private final SaveChessGamePort saveChessGamePort;

    public ConsoleChessService(SaveChessGamePort saveChessGamePort) {
        this.saveChessGamePort = saveChessGamePort;
    }

    @Override
    public void movePiece(MovePieceCommand movePieceCommand) {
        String source = movePieceCommand.getSource();
        String target = movePieceCommand.getTarget();

        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.move(TileFactory.from(source), TileFactory.from(target));

        saveChessGamePort.saveChessGame(chessGame);
    }

    @Override
    public void promotion(PromotionCommand promotionCommand) {
        ChessGame chessGame = ConsoleChessGameFactory.getInstance();
        chessGame.promotion(promotionCommand.getPieceType());
    }
}
