package demo04;


public class Main {
    public static void main(String[] args) {
        HelloImpl helloImpl = new HelloImpl();
        IHello hello = (IHello) CustomInvocationHandler.getProxy(helloImpl);
        hello.hello();
        hello.hi();
    }
}
