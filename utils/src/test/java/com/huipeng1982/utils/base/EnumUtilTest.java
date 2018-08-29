package com.huipeng1982.utils.base;

import com.huipeng1982.utils.collection.ListUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumUtilTest {
    @Test
    public void testBits() {
        assertThat(EnumUtil.generateBits(Options.class, Options.A)).isEqualTo(1);
        assertThat(EnumUtil.generateBits(Options.class, Options.A, Options.B)).isEqualTo(3);

        assertThat(EnumUtil.generateBits(Options.class, ListUtil.newArrayList(Options.A))).isEqualTo(1);
        assertThat(EnumUtil.generateBits(Options.class, ListUtil.newArrayList(Options.A, Options.B))).isEqualTo(3);

        assertThat(EnumUtil.processBits(Options.class, 3)).hasSize(2).containsExactly(Options.A, Options.B);
        assertThat(EnumUtil.processBits(Options.class,
            EnumUtil.generateBits(Options.class, Options.A, Options.C, Options.D))).hasSize(3)
            .containsExactly(Options.A, Options.C, Options.D);

    }

    @Test
    public void testString() {
        assertThat(EnumUtil.toString(Options.A)).isEqualTo("A");

        assertThat(EnumUtil.fromString(Options.class, "B")).isEqualTo(Options.B);
    }

    public enum Options {
        A, B, C, D;
    }
}
