package demo03;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomInvocationHandler implements InvocationHandler {

    private Object target;
    public CustomInvocationHandler(Object target) {
        this.target=target;
    }

    public static Object getProxy(Object target) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        CustomInvocationHandler handler = new CustomInvocationHandler(target);
        return Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("proxy类名: %s", proxy.getClass().getCanonicalName()));
        System.out.println(String.format("调用类: %s, 调用方法: %s", target.getClass().getCanonicalName(), method.getName()));
        return method.invoke(target,args);
    }
}
