package co.architecture.consolechess.adapter.in;

import co.architecture.consolechess.application.port.in.ChessMoveUseCase;
import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;

public class ConsoleChessController {
    private final ChessMoveUseCase chessMoveUseCase;

    public ConsoleChessController(ChessMoveUseCase chessMoveUseCase) {
        this.chessMoveUseCase = chessMoveUseCase;
    }

    public void moveChess(MovePieceCommand movePieceCommand) {
        chessMoveUseCase.movePiece(movePieceCommand);
    }

    public void promotion(PromotionCommand promotionCommand) {
        chessMoveUseCase.promotion(promotionCommand);
    }
}
