package co.chess.common;

public class StringUtil {
    public static boolean isBlank(String k) {
        if (k == null) {
            return true;
        }
        return k.trim().length() == 0;
    }
}
