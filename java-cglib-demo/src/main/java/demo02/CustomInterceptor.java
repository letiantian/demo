package demo02;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CustomInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.printf("发起调用. obj: %s, method: %s, proxy: %s\n",
                obj.getClass().getCanonicalName(),
                method.getName(),
                proxy.getClass().getCanonicalName()
        );
        return proxy.invokeSuper(obj, args);
    }

    public static Object createProxy(Class cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new CustomInterceptor());
        return enhancer.create();
    }

}
