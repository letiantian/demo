package demo02;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        addClassToDisk(proxy.getClass().getName(), HelloImpl.class,"/Users/letian/Proxy.class");

        System.out.println(String.format("proxy类名: %s", proxy.getClass().getCanonicalName()));
        System.out.println(String.format("调用类: %s, 调用方法: %s", target.getClass().getCanonicalName(), method.getName()));
        return method.invoke(target,args);
    }

    /**
     * 这个代码来自：https://www.jianshu.com/p/e2917b0b9614 ，作用是将类的字节码写入指定path对应的文件
     */
    private void addClassToDisk(String className, Class<?> cl, String path) {
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
