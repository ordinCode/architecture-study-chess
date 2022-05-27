package co.chess.consolechess.service;

import co.chess.consolechess.exception.InputException;
import co.chess.consolechess.ui.InputView;
import co.chess.consolechess.ui.OutputView;
import co.chess.consolechess.utils.CommandValidator;
import co.chess.consolechess.utils.PieceSymbolConverter;
import co.chess.domain.ChessGame;
import co.chess.domain.dto.MoveRequest;
import co.chess.domain.dto.PromotionRequest;
import co.chess.domain.exception.move.MoveException;
import co.chess.domain.piece.PieceType;

public class ConsoleChessService {
    public static void end(ChessGame chessGame, String userInput) {
        OutputView.printGameOver(chessGame.findWinner());
        System.exit(0);
    }

    public static void move(ChessGame chessGame, String userInput) throws MoveException, InputException {
        MoveRequest moveRequest = toMoveRequest(userInput);
        chessGame.move(moveRequest);
    }

    private static MoveRequest toMoveRequest(String userInput) {
        CommandValidator.validateMoveCommand(userInput);
        String[] inputs = userInput.split(InputView.COMMAND_DELIMITER);
        return new MoveRequest(inputs[1], inputs[2]);
    }

    public static void promotion(ChessGame chessGame, String userInput) throws InputException {
        CommandValidator.validatePromotionCommand(userInput);
        PromotionRequest promotionRequest = toPromotionRequest(userInput);
        chessGame.promotion(promotionRequest);
    }

    private static PromotionRequest toPromotionRequest(String userInput) {
        CommandValidator.validatePromotionCommand(userInput);
        String symbol = userInput.split(InputView.COMMAND_DELIMITER)[1];
        PieceType pieceType = PieceSymbolConverter.toPieceType(symbol);
        return new PromotionRequest(pieceType);
    }
}
