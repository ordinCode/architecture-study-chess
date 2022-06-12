package co.architecture.consolechess.adapter.in;

import co.architecture.consolechess.application.port.in.MovePieceCommand;
import co.architecture.consolechess.application.port.in.PromotionCommand;
import co.architecture.consolechess.ui.input.UserInputMapper;
import co.architecture.consolechess.ui.input.UserInputType;
import co.architecture.consolechess.ui.input.exception.InputException;
import co.architecture.chess.exception.move.MoveException;

public class ConsoleInputHandler {
    private final ConsoleChessController consoleChessController;

    public ConsoleInputHandler(ConsoleChessController consoleChessController) {
        this.consoleChessController = consoleChessController;
    }

    public void handleUserInput(String userInput) {
        try {
            UserInputType userInputType = UserInputMapper.findUserInputType(userInput);
            if (userInputType == UserInputType.MOVE) {
                MovePieceCommand movePieceCommand = MovePieceCommand.from(userInput);
                consoleChessController.moveChess(movePieceCommand);
                return;
            }
            if (userInputType == UserInputType.PROMOTION) {
                PromotionCommand promotionCommand = PromotionCommand.from(userInput);
                consoleChessController.promotion(promotionCommand);
            }
        } catch (MoveException | InputException e) {
            System.out.println(e.getMessage());
        }
    }
}
