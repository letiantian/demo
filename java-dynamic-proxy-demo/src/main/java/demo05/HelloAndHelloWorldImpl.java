package demo05;

public class HelloAndHelloWorldImpl implements IHello, IHelloWorld {
    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void hi() {
        System.out.println("hi");
    }

    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }
}
