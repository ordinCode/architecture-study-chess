package co.architecture.consolechess.adapter.in;

import co.architecture.chess.ChessGame;
import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.GetChessGameQuery;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;
import co.architecture.consolechess.gamefacory.ConsoleChessGameFactory;

public class ConsoleChessController {
    private final ChessMoveUseCase chessMoveUseCase;
    private final GetChessGameQuery getChessGameQuery;

    public ConsoleChessController(ChessMoveUseCase chessMoveUseCase, GetChessGameQuery getChessGameQuery) {
        this.chessMoveUseCase = chessMoveUseCase;
        this.getChessGameQuery = getChessGameQuery;
    }

    public void moveChess(MovePieceCommand movePieceCommand) {
        chessMoveUseCase.movePiece(movePieceCommand);
    }

    public void promotion(PromotionCommand promotionCommand) {
        chessMoveUseCase.promotion(promotionCommand);
    }

    public void load() {
        try {
            ChessGame chessGame = getChessGameQuery.getChessGame();
            ConsoleChessGameFactory.updateChessGame(chessGame);
        } catch (Exception e) {
            System.out.println("게임을 불러올 수 없습니다.");
        }
    }
}
