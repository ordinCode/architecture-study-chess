package co.chess.consolechess.ui;

import co.chess.common.StringUtil;
import co.chess.consolechess.UserInputMapper;
import co.chess.consolechess.UserInputType;

import java.util.Scanner;

public class InputView {
    public static final String COMMAND_DELIMITER = " ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String userInput() {
        String input = SCANNER.nextLine();
        if (StringUtil.isBlank(input)) {
            throw new IllegalArgumentException("공란은 입력할 수 없습니다.");
        }
        return input;
    }
}
