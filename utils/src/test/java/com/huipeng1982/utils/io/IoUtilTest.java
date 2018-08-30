package com.huipeng1982.utils.io;

import com.huipeng1982.utils.io.type.StringBuilderWriter;
import com.huipeng1982.utils.text.Charsets;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IoUtilTest {

    @Test
    public void read() throws IOException {
        assertThat(IoUtil.toString(ResourceUtil.asStream("test.txt"))).isEqualTo("ABCDEFG\nABC\n");
        assertThat(IoUtil.toLines(ResourceUtil.asStream("test.txt"))).hasSize(2).containsExactly("ABCDEFG", "ABC");
    }

    @Test
    public void write() throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        IoUtil.write("hahahaha", sw);
        assertThat(sw.toString()).isEqualTo("hahahaha");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IoUtil.write("hahahaha", out);
        assertThat(new String(out.toByteArray(), Charsets.UTF_8)).isEqualTo("hahahaha");

        IoUtil.closeQuietly(out);
        IoUtil.closeQuietly((Closeable) null);
    }
}
