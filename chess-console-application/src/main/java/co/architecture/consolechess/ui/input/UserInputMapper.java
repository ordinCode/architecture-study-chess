package co.architecture.consolechess.ui.input;

import static co.architecture.consolechess.ui.input.InputView.COMMAND_DELIMITER;

public class UserInputMapper {
    public static UserInputType findUserInputType(String userInput) {
        String command = userInput.split(COMMAND_DELIMITER)[0];
        return UserInputType.findByCommand(command);
    }
}
