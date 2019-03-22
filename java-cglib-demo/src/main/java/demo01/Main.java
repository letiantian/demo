package demo01;

import net.sf.cglib.proxy.Enhancer;

public class Main {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new CustomInterceptor());

        Hello hello = (Hello) enhancer.create();
        hello.hello();
        hello.hi();
    }

}
