package proxy04;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Main {
    @Test
    public void test() {
        HelloAndHelloWorldImpl helloImpl = new HelloAndHelloWorldImpl();
        Object proxy = CustomInvocationHandler.getProxy(helloImpl);

        IHello hello = (IHello) proxy;
        hello.hello();

        IHelloWorld helloWorld = (IHelloWorld) proxy;
        helloWorld.helloWorld();
    }
}
