package co.architecture.consolechess.application.port.in;

import co.architecture.chess.piece.PieceType;
import co.architecture.consolechess.ui.input.InputView;
import co.architecture.consolechess.utils.CommandValidator;
import co.architecture.consolechess.utils.PieceSymbolConverter;

public class PromotionCommand {
    private final PieceType pieceType;

    public static PromotionCommand from(String userInput) {
        CommandValidator.validatePromotionCommand(userInput);
        String symbol = userInput.split(InputView.COMMAND_DELIMITER)[1];
        PieceType pieceType = PieceSymbolConverter.toPieceType(symbol);
        return new PromotionCommand(pieceType);
    }

    public PromotionCommand(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
