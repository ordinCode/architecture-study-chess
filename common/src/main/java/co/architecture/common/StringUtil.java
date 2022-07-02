package co.architecture.common;

public class StringUtil {
    public static boolean isBlank(String k) {
        if (k == null) {
            return true;
        }
        return k.trim().length() == 0;
    }

    public static boolean isUpperCase(String input) {
        return input.toUpperCase().equals(input);
    }
}
