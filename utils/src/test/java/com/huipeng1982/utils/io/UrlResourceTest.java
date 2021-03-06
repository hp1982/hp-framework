package com.huipeng1982.utils.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class UrlResourceTest {

    @Test
    public void resource() throws IOException {
        File file = UrlResourceUtil.asFile("classpath:application.properties");
        assertThat(FileUtil.toString(file)).isEqualTo("hp-framework.min=1\nhp-framework.max=10\n");

        InputStream is = UrlResourceUtil.asStream("classpath:application.properties");
        assertThat(IoUtil.toString(is)).isEqualTo("hp-framework.min=1\nhp-framework.max=10\n");
        IoUtil.closeQuietly(is);

        try {
            UrlResourceUtil.asFile("classpath:notexist.properties");
            fail("should fail");
        } catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalArgumentException.class);
        }

        try {
            UrlResourceUtil.asStream("classpath:notexist.properties");
            fail("should fail");
        } catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalArgumentException.class);
        }

    }

    @Test
    public void file() throws IOException {
        File file = FileUtil.createTempFile().toFile();
        FileUtil.write("haha", file);

        try {
            File file2 = UrlResourceUtil.asFile("file://" + file.getAbsolutePath());
            assertThat(FileUtil.toString(file2)).isEqualTo("haha");

            File file2NotExist = UrlResourceUtil.asFile("file://" + file.getAbsolutePath() + ".noexist");
            assertThat(file2NotExist.exists()).isFalse();

            File file3 = UrlResourceUtil.asFile(file.getAbsolutePath());
            assertThat(FileUtil.toString(file3)).isEqualTo("haha");
            File file3NotExist = UrlResourceUtil.asFile(file.getAbsolutePath() + ".noexist");
            assertThat(file3NotExist.exists()).isFalse();
        } finally {
            FileUtil.deleteFile(file);
        }

    }

}
