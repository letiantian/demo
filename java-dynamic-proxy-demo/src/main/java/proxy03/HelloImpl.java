package proxy03;

public class HelloImpl implements IHello {
    @Override
    public void hello() {
        System.out.println("hello");
        hi();
    }

    @Override
    public void hi() {
        System.out.println("hi");
    }
}
