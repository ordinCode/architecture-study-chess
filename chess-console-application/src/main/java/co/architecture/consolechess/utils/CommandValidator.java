package co.architecture.consolechess.utils;

import co.architecture.consolechess.ui.input.UserInputType;
import co.architecture.consolechess.ui.input.exception.InvalidMoveCommandException;
import co.architecture.consolechess.ui.input.exception.InvalidPromotionCommandException;
import co.architecture.consolechess.ui.input.InputView;

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
