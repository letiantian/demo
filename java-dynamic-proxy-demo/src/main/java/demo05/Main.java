package demo05;

public class Main {
    public static void main(String[] args) {
        HelloAndHelloWorldImpl helloImpl = new HelloAndHelloWorldImpl();
        Object proxy = CustomInvocationHandler.getProxy(helloImpl);

        // 调用 IHello 接口的方法
        IHello hello = (IHello) proxy;
        hello.hello();

        // 调用 IHelloWorld 接口的方法
        IHelloWorld helloWorld = (IHelloWorld) proxy;
        helloWorld.helloWorld();
    }
}
