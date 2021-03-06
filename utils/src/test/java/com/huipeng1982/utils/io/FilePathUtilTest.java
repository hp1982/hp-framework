package com.huipeng1982.utils.io;

import com.google.common.io.Files;
import com.huipeng1982.utils.base.Platforms;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilePathUtilTest {

    char sep = Platforms.FILE_PATH_SEPARATOR_CHAR;

    @Test
    public void pathName() {
        String filePath = FilePathUtil.concat(sep + "abc", "ef");
        assertThat(filePath).isEqualTo(FilePathUtil.normalizePath("/abc/ef"));

        String filePath2 = FilePathUtil.concat(sep + "stuv" + sep, "xy");
        assertThat(filePath2).isEqualTo(FilePathUtil.normalizePath("/stuv/xy"));

        assertThat(FilePathUtil.simplifyPath("../dd/../abc")).isEqualTo("../abc");
        assertThat(FilePathUtil.simplifyPath("../../dd/../abc")).isEqualTo("../../abc");
        assertThat(FilePathUtil.simplifyPath("./abc")).isEqualTo("abc");

        assertThat(FilePathUtil.getParentPath(FilePathUtil.normalizePath("/abc/dd/efg/")))
            .isEqualTo(FilePathUtil.normalizePath("/abc/dd/"));

        assertThat(FilePathUtil.getParentPath(FilePathUtil.normalizePath("/abc/dd/efg.txt")))
            .isEqualTo(FilePathUtil.normalizePath("/abc/dd/"));
    }

    @Test
    public void getJarPath() {
        System.out.println("the jar file contains Files.class" + FilePathUtil.getJarPath(Files.class));
        assertThat(FilePathUtil.getJarPath(Files.class)).endsWith("guava-23.6-jre.jar");
    }
}
