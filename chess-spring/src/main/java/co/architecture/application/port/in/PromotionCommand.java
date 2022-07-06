package co.architecture.application.port.in;

import co.architecture.chess.piece.PieceType;

public class PromotionCommand {
    private PieceType pieceType;

    public PromotionCommand() {
    }

    public PromotionCommand(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
