package co.chess.consolechess;

import static co.chess.consolechess.ui.InputView.COMMAND_DELIMITER;

public class UserInputMapper {
    public static UserInputType findUserInputType(String userInput) {
        String command = userInput.split(COMMAND_DELIMITER)[0];
        return UserInputType.findByCommand(command);
    }
}
