package co.architecture.consolechess.application.port.in;

public interface ChessMoveUseCase {
    void movePiece(MovePieceCommand movePieceCommand);
    void promotion(PromotionCommand promotionCommand);
}
