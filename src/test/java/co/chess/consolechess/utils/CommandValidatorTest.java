package co.chess.consolechess.utils;

import co.chess.consolechess.exception.InvalidMoveCommandException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandValidatorTest {
    @DisplayName("올바른 이동 명령어입력시 예외발생하지 않음")
    @ParameterizedTest
    @ValueSource(strings = {"move a1 a3", "move b4 b8"})
    void validMoveCommand(String command) {
        CommandValidator.validateMoveCommand(command);
    }

    @DisplayName("[예외] 잘못된 이동 명령어 입력시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {
            "start b4 b8",
            "move a1",
            "move b4 b8 c3",
            "move a1a2",
            "a1 a1 a2",
    })
    void invalidMoveCommand(String command) {
        Assertions.assertThatThrownBy(() -> CommandValidator.validateMoveCommand(command))
                .isInstanceOf(InvalidMoveCommandException.class);
    }
}