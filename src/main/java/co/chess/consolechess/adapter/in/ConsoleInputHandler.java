package co.chess.consolechess.adapter.in;

import co.chess.chess.ChessGame;
import co.chess.chess.exception.move.MoveException;
import co.chess.consolechess.UserInputMapper;
import co.chess.consolechess.UserInputType;
import co.chess.consolechess.exception.InputException;
import co.chess.consolechess.gamefacory.ConsoleChessGameFactory;
import co.chess.consolechess.port.in.MovePieceCommand;
import co.chess.consolechess.port.in.PromotionCommand;
import co.chess.consolechess.ui.OutputView;

public class ConsoleInputHandler {
    private final ConsoleChessController consoleChessController;

    public ConsoleInputHandler(ConsoleChessController consoleChessController) {
        this.consoleChessController = consoleChessController;
    }

    public void handleUserInput(String userInput) {
        try {
            UserInputType userInputType = UserInputMapper.findUserInputType(userInput);
            if (userInputType == UserInputType.END) {
                OutputView.printGameOver();
                System.exit(0);
            }
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
