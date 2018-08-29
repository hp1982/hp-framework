package com.huipeng1982.utils.base.type;

import com.huipeng1982.utils.base.ValueValidator;
import org.apache.commons.lang3.StringUtils;

public class DefaultValidator {

    /**
     * 校验器: 数值配置不为null, 且大于0较验
     */
    public static final ValueValidator.Validator<Integer> INTEGER_GT_ZERO_VALIDATOR = value -> (value != null && value > 0);
    /**
     * 校验器: 字符串不为空串较验
     */
    public static final ValueValidator.Validator<String> STRING_EMPTY_VALUE_VALIDATOR = value -> StringUtils.isNotEmpty(value);
    /**
     * 校验器: BOOL字符串较验
     */
    public static final ValueValidator.Validator<String> STRICT_BOOL_VALUE_VALIDATOR = value -> "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);

    private DefaultValidator() {
    }

}
