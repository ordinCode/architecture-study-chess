package co.architecture.common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {
    @Test
    void isUpperCase() {
        Assertions.assertThat(StringUtil.isUpperCase("abc")).isFalse();
        Assertions.assertThat(StringUtil.isUpperCase("ABC")).isTrue();

        Assertions.assertThat(StringUtil.isUpperCase("abC")).isFalse();
    }
}