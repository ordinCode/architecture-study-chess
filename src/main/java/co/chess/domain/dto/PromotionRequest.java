package co.chess.domain.dto;

import co.chess.domain.piece.PieceType;

public class PromotionRequest {
    private final PieceType pieceType;

    public PromotionRequest(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
