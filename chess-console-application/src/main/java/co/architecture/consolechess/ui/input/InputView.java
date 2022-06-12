package co.architecture.consolechess.ui.input;

import co.architecture.common.StringUtil;
import co.architecture.consolechess.ui.input.exception.InputException;

import java.util.Scanner;

public class InputView {
    public static final String COMMAND_DELIMITER = " ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String userInput() {
        String input = SCANNER.nextLine();
        if (StringUtil.isBlank(input)) {
            throw new InputException("공란은 입력할 수 없습니다.");
        }
        return input;
    }
}
