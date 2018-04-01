package cn.syberos.jnidemo;

/**
 * Created by BZB on 2018/3/23.
 */

public class TestJNI {
//    加载so库
    static {
        System.loadLibrary("JniLibrary");
    }
//    native方法
    public static native String sayHello();

}
