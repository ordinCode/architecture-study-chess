package co.architecture.application.port.in;

public interface ChessMoveUseCase {
    void movePiece(Long chessGameId, MovePieceCommand movePieceCommand);

    void promotion(Long chessGameId, PromotionCommand promotionCommand);
}
