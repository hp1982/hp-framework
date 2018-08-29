package com.huipeng1982.utils.base;

import com.huipeng1982.utils.base.type.DefaultValidator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueValidatorTest {
    @Test
    public void testValidator() {
        assertThat(ValueValidator.checkAndGet(-1, 1, DefaultValidator.INTEGER_GT_ZERO_VALIDATOR)).isEqualTo(1);
        assertThat(ValueValidator.checkAndGet("testUnEmpty", "isEmpty", DefaultValidator.STRING_EMPTY_VALUE_VALIDATOR))
            .isEqualTo("testUnEmpty");
        assertThat(ValueValidator.checkAndGet("flase", "true", DefaultValidator.STRICT_BOOL_VALUE_VALIDATOR))
            .isEqualTo("true");
    }
}

