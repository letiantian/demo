package demo05;

import org.junit.Test;

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
