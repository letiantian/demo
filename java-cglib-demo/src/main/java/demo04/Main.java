package demo04;

public class Main {

    public static void main(String[] args) {
        Hello hello = (Hello) CustomInterceptor.createProxy(new Hello());
        hello.hello();
    }

}
