package com.huipeng1982.utils.base.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TripleTest {

    @Test
    public void tripleTest() {
        Triple<String, String, Integer> triple = Triple.of("haha", "hehe", 1);
        Triple<String, String, Integer> triple2 = Triple.of("haha", "hehe", 2);
        Triple<String, String, Integer> triple3 = Triple.of("haha", "lala", 2);
        Triple<String, String, Integer> triple4 = Triple.of("kaka", "lala", 2);


        Pair<String, Integer> pair = Pair.of("haha", 1);
        assertThat(triple.equals(triple2)).isFalse();
        assertThat(triple.equals(triple3)).isFalse();
        assertThat(triple.equals(triple4)).isFalse();
        assertThat(triple.equals(pair)).isFalse();
        assertThat(triple.hashCode() != triple2.hashCode()).isTrue();
        assertThat(triple.toString()).isEqualTo("Triple [left=haha, middle=hehe, right=1]");

        assertThat(triple.getLeft()).isEqualTo("haha");
        assertThat(triple.getMiddle()).isEqualTo("hehe");
        assertThat(triple.getRight()).isEqualTo(1);
    }
}
