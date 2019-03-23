package demo04;

import org.junit.Test;

public class Main {
    @Test
    public void test() {
        HelloImpl helloImpl = new HelloImpl();
        IHello hello = (IHello) CustomInvocationHandler.getProxy(helloImpl);
        hello.hello();
        hello.hi();
    }

}
