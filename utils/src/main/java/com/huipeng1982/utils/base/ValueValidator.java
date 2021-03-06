package com.huipeng1982.utils.base;

/**
 * 配值较验器
 * <p>
 * 提供对值进行较验的api，并根据较验结果取值且返回
 */
public class ValueValidator {

    /**
     * 对目标值进行校验，并根据校验结果取值
     * <p>
     * 使用示例(校验目标值是否大于0, 如果小于 0 则取值为 1)
     * <p>
     * ValueValidator.checkAndGet(-1, 1, Validator.INTEGER_GT_ZERO_VALIDATOR)
     *
     * @param value        校验值
     * @param defaultValue 校验失败默认值
     * @param v            校验器
     * @return 经Validator校验后的返回值，校验成功返回 value, 校验失败返回 defaultValue
     */
    public static <T> T checkAndGet(T value, T defaultValue, Validator<T> v) {
        if (v.validate(value)) {
            return value;
        }

        return defaultValue;
    }

    /**
     * 对Properties值进行规则匹配的验证器
     */
    public interface Validator<T> {
        /**
         * 校验值是否匹配
         *
         * @param value
         * @return
         */
        boolean validate(T value);
    }
}
