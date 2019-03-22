package demo04;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CustomInterceptor implements InvocationHandler {

    private Object target;

    public CustomInterceptor(Object target) {
        this.target = target;
    }

    public static Object createProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CustomInterceptor(target));
        return enhancer.create();
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.printf("发起调用. method: %s, proxy: %s\n",
                method.getName(),
                proxy.getClass().getCanonicalName()
        );

        return method.invoke(target, args);
    }
}
