package demo02;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("proxy类名: %s", proxy.getClass().getCanonicalName()));
        System.out.println(String.format("调用类: %s, 调用方法: %s", target.getClass().getCanonicalName(), method.getName()));
        return method.invoke(target,args);
    }

}
