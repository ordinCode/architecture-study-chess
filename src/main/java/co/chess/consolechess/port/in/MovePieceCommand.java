package co.chess.consolechess.port.in;

import co.chess.consolechess.ui.InputView;
import co.chess.consolechess.utils.CommandValidator;

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
