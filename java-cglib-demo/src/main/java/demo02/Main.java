package demo02;

public class Main {

    public static void main(String[] args) {
        Hello hello = (Hello) CustomInterceptor.createProxy(Hello.class);
        hello.hello();
        hello.hi();
    }

}
