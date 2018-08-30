package com.huipeng1982.utils.base;

import org.junit.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertiesUtilTest {

    @Test
    public void loadProperties() {
        Properties p1 = PropertiesUtil.loadFromFile("classpath:application.properties");
        assertThat(p1.get("hp-framework.min")).isEqualTo("1");
        assertThat(p1.get("hp-framework.max")).isEqualTo("10");

        Properties p2 = PropertiesUtil.loadFromString("hp-framework.min=1\nhp-framework.max=10\nisOpen=true");
        assertThat(PropertiesUtil.getInt(p2, "hp-framework.min", 0)).isEqualTo(1);
        assertThat(PropertiesUtil.getInt(p2, "hp-framework.max", 0)).isEqualTo(10);
        assertThat(PropertiesUtil.getInt(p2, "hp-framework.maxA", 0)).isEqualTo(0);

        assertThat(PropertiesUtil.getLong(p2, "hp-framework.min", 0L)).isEqualTo(1);
        assertThat(PropertiesUtil.getLong(p2, "hp-framework.max", 0L)).isEqualTo(10);
        assertThat(PropertiesUtil.getLong(p2, "hp-framework.maxA", 0L)).isEqualTo(0);

        assertThat(PropertiesUtil.getDouble(p2, "hp-framework.min", 0d)).isEqualTo(1);
        assertThat(PropertiesUtil.getDouble(p2, "hp-framework.max", 0d)).isEqualTo(10);
        assertThat(PropertiesUtil.getDouble(p2, "hp-framework.maxA", 0d)).isEqualTo(0);

        assertThat(PropertiesUtil.getString(p2, "hp-framework.min", "")).isEqualTo("1");
        assertThat(PropertiesUtil.getString(p2, "hp-framework.max", "")).isEqualTo("10");
        assertThat(PropertiesUtil.getString(p2, "hp-framework.maxA", "")).isEqualTo("");

        assertThat(PropertiesUtil.getBoolean(p2, "isOpen", false)).isTrue();
    }
}
