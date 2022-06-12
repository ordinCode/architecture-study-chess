package co.chess.consolechess.port.in;

public interface ChessMoveUseCase {
    void movePiece(MovePieceCommand movePieceCommand);
    void promotion(PromotionCommand promotionCommand);
}
