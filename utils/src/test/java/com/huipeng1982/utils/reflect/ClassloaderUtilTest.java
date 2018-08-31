package com.huipeng1982.utils.reflect;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassloaderUtilTest {

    @Test
    public void test() {
        ClassLoader loader = ClassLoaderUtil.getDefaultClassLoader();
        boolean isPresent = ClassLoaderUtil.isPresent("com.huipeng1982.utils.reflect.ClassloaderUtilTest", loader);
        assertThat(isPresent).isTrue();
        System.out.println("com.huipeng1982.utils.reflect.ClassloaderUtilTest isPresent : " + isPresent);


    }
}
