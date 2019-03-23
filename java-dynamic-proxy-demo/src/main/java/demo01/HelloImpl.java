package demo01;

public class HelloImpl implements IHello {
    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void hi() {
        System.out.println("hi");
    }
}
