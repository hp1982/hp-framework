package com.huipeng1982.utils.reflect;

/**
 * 1> 按顺序获取默认ClassLoader
 * 2> 探测类是否存在classpath中
 */
public class ClassLoaderUtil {

    private ClassLoaderUtil() {
    }

    /**
     * Copy from Spring, 按顺序获取默认ClassLoader
     * <p>
     * 1. Thread.currentThread().getContextClassLoader()
     * <p>
     * 2. ClassLoaderUtil的加载ClassLoader
     * <p>
     * 3. SystemClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception ex) {
            /*
             * Cannot access thread context ClassLoader - falling back...
             */

        }
        if (cl == null) {
            /*
             * No thread context class loader -> use class loader of this class.
             */
            cl = ClassLoaderUtil.class.getClassLoader();
            if (cl == null) {
                /*
                 * getClassLoader() returning null indicates the bootstrap ClassLoader
                 */
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Exception ex) {
                    /*
                     * Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                     */
                }
            }
        }
        return cl;
    }

    /**
     * 探测类是否存在classpath中
     */
    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            classLoader.loadClass(className);
            return true;
        } catch (ClassNotFoundException ex) {
            /*
             * Class or one of its dependencies is not present...
             */
            return false;
        }
    }

}

