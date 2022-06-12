package co.chess.consolechess.ui.input;

import co.chess.consolechess.ui.input.exception.InputException;

import java.util.Arrays;

public enum UserInputType {
    START("start"),
    END("end"),
    MOVE("move"),
    PROMOTION("promotion");

    private final String command;

    UserInputType(String command) {
        this.command = command;
    }

    public static UserInputType findByCommand(String command) {
        return Arrays.stream(UserInputType.values())
                .filter(userInputType -> userInputType.command.equals(command))
                .findAny()
                .orElseThrow(() -> new InputException("Input Type을 찾을 수 없습니다.."));
    }

    public String getCommand() {
        return command;
    }
}
