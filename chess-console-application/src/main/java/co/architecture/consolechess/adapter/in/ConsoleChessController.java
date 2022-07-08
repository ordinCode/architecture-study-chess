package co.architecture.consolechess.adapter.in;

import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.LoadChessGame;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;

public class ConsoleChessController {
    private final ChessMoveUseCase chessMoveUseCase;
    private final LoadChessGame loadChessGame;

    public ConsoleChessController(ChessMoveUseCase chessMoveUseCase, LoadChessGame loadChessGame) {
        this.chessMoveUseCase = chessMoveUseCase;
        this.loadChessGame = loadChessGame;
    }

    public void moveChess(MovePieceCommand movePieceCommand) {
        chessMoveUseCase.movePiece(movePieceCommand);
    }

    public void promotion(PromotionCommand promotionCommand) {
        chessMoveUseCase.promotion(promotionCommand);
    }

    public void load() {
        try {
            loadChessGame.getChessGame();
        } catch (Exception e) {
            System.out.println("게임을 불러올 수 없습니다.");
        }
    }
}
