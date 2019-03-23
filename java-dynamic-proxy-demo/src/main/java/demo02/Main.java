package demo02;

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
}
