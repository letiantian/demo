package demo02;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        HelloImpl helloImpl = new HelloImpl();
        ClassLoader classLoader = HelloImpl.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { IHello.class };
        CustomInvocationHandler handler = new CustomInvocationHandler(helloImpl);

        // 生成字节码
        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, handler);
        addClassToDisk(proxy.getClass().getName(), HelloImpl.class,"/Users/letian/Proxy.class");
    }


    /**
     * 这个代码来自：https://www.jianshu.com/p/e2917b0b9614 ，作用是将类的字节码写入指定path对应的文件
     */
    private static void addClassToDisk(String className, Class<?> cl, String path) {
        //用于生产代理对象的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            //将代理对象的class字节码写到硬盘上
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out!=null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
