package co.architecture.consolechess.application.port.in;

import co.architecture.consolechess.ui.input.InputView;
import co.architecture.consolechess.utils.CommandValidator;

public class MovePieceCommand {
    private final String source;
    private final String target;

    public static MovePieceCommand from(String userInput) {
        CommandValidator.validateMoveCommand(userInput);
        String[] inputs = userInput.split(InputView.COMMAND_DELIMITER);
        return new MovePieceCommand(inputs[1], inputs[2]);
    }

    public MovePieceCommand(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
