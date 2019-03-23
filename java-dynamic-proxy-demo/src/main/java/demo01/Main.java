package demo01;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        HelloImpl helloImpl = new HelloImpl();
        ClassLoader classLoader = HelloImpl.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { IHello.class };
        CustomInvocationHandler handler = new CustomInvocationHandler(helloImpl);

        IHello hello = (IHello) Proxy.newProxyInstance(classLoader, interfaces, handler);
        hello.hello();
    }


    @Test
    public void test() {
        HelloImpl helloImpl = new HelloImpl();
        ClassLoader classLoader = HelloImpl.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { IHello.class };
        CustomInvocationHandler handler = new CustomInvocationHandler(helloImpl);

        // java 动态代理是基于接口的，所以下面这种方式生成代理是错误的。
        HelloImpl helloProxy = (HelloImpl) Proxy.newProxyInstance(classLoader, interfaces, handler);
        helloProxy.hello();
    }
}
