package co.chess.consolechess.adapter.in;

import co.chess.consolechess.port.in.ChessMoveUseCase;
import co.chess.consolechess.port.in.MovePieceCommand;
import co.chess.consolechess.port.in.PromotionCommand;

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
