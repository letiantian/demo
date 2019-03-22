package proxy03;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Main {
    @Test
    public void test() {
        HelloImpl helloImpl = new HelloImpl();
        IHello hello = (IHello) CustomInvocationHandler.getProxy(helloImpl);
        hello.hello();
        hello.hi();
    }

}
