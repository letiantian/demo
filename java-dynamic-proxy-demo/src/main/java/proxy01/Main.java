package proxy01;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Main {
    @Test
    public void test() {
        HelloImpl helloImpl = new HelloImpl();
        ClassLoader classLoader = HelloImpl.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { IHello.class };
        CustomInvocationHandler handler = new CustomInvocationHandler(helloImpl);

        IHello hello = (IHello) Proxy.newProxyInstance(classLoader, interfaces, handler);
        hello.hello();
    }
}
