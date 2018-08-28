package com.huipeng1982.utils.base;

import com.huipeng1982.utils.base.annotation.Nullable;

/**
 * 参数校验统一使用Apache Common Lange Validate, 补充一些缺少的.
 * <p>
 * 为什么不用Guava的{@code com.google.common.base.Preconditions} , 一是少打几个字而已, 二是Validate的方法多，比如noNullElements()判断多个元素都不为空
 * <p>
 * 目前主要参考 {@code com.google.common.math.MathPreconditions} , 补充数字为正数或非负数的校验
 */
public class MoreValidate {

    private static final String M_B_B_Z = ") must be > 0";
    private static final String M_B_BE_Z = ") must be >= 0";

    private MoreValidate() {
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static int positive(@Nullable String role, int x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_B_Z);
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Integer positive(@Nullable String role, Integer x) {
        if (x.intValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_B_Z);
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static long positive(@Nullable String role, long x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_B_Z);
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Long positive(@Nullable String role, Long x) {
        if (x.longValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_B_Z);
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static double positive(@Nullable String role, double x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static int nonNegative(@Nullable String role, int x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Integer nonNegative(@Nullable String role, Integer x) {
        if (x.intValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static long nonNegative(@Nullable String role, long x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Long nonNegative(@Nullable String role, Long x) {
        if (x.longValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static double nonNegative(@Nullable String role, double x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + M_B_BE_Z);
        }
        return x;
    }
}
