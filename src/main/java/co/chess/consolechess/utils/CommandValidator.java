package co.chess.consolechess.utils;

import co.chess.consolechess.UserInputType;
import co.chess.consolechess.exception.InvalidMoveCommandException;
import co.chess.consolechess.exception.InvalidPromotionCommandException;
import co.chess.consolechess.ui.InputView;

import java.util.Arrays;

public class CommandValidator {
    public static void validateMoveCommand(String userInput) {
        String[] inputs = userInput.split(InputView.COMMAND_DELIMITER);
        if (inputs.length != 3) {
            throw new InvalidMoveCommandException();
        }
        if (!UserInputType.MOVE.getCommand().equals(inputs[0])) {
            throw new InvalidMoveCommandException();
        }
    }

    public static void validatePromotionCommand(String userInput) {
        String[] inputs = userInput.split(InputView.COMMAND_DELIMITER);
        if (inputs.length != 2
                || !UserInputType.PROMOTION.getCommand().equals(inputs[0])
                || !Arrays.asList("q", "r", "b", "n").contains(inputs[1])) {
            throw new InvalidPromotionCommandException();
        }
    }
}
