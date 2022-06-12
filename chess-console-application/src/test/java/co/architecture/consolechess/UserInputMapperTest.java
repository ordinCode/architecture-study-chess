package co.architecture.consolechess;

import co.architecture.consolechess.ui.input.UserInputMapper;
import co.architecture.consolechess.ui.input.UserInputType;
import co.architecture.consolechess.ui.input.exception.InputException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class UserInputMapperTest {

    @DisplayName("유저가 입력한 값에 따라 UserInputType을 가져오는 기능 테스트")
    @ParameterizedTest
    @MethodSource("findUserInputTypeProvider")
    void findUserInputType(String userInput, UserInputType userInputType) {
        Assertions.assertThat(UserInputMapper.findUserInputType(userInput)).isEqualTo(userInputType);
    }

    static Stream<Arguments> findUserInputTypeProvider() {
        return Stream.of(
                arguments("start", UserInputType.START),
                arguments("end", UserInputType.END),
                arguments("move a1 a2", UserInputType.MOVE)
        );
    }

    @DisplayName("[예외] 잘못된 명령어를 입력하면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"moved"," start"})
    void findUserInputType(String wrongInput) {
        Assertions.assertThatThrownBy(() -> UserInputMapper.findUserInputType(wrongInput))
                .isInstanceOf(InputException.class);
    }
}