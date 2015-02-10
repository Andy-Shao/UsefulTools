package com.github.andyshao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface DynamicPF<T> {
    @SuppressWarnings("unchecked")
    public default T getProxy(T target , InvocationHandler invocationHandler) {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader() , this.proxyInterfaces(target) ,
            invocationHandler);
    }

    /**
     * when the method which will be invoke should be proxy.
     * this method will be run.
     * 
     * @param target the target which will be proxy
     * @param method the method which will be invoke
     * @param args the args of method
     * @return the answer of method
     * @throws Throwable andy exception when run this method
     */
    Object invoke(T target , Method method , Object[] args) throws Throwable;

    Class<?>[] proxyInterfaces(T target);

    /**
     * the methods which will be proxied
     * 
     * @param target the target which will be proxy
     * @param method the method which will be invoke
     * @param args the args of method
     * @return the methods collection
     */
    boolean proxyMethods(T target , Method method , Object[] args);

    public default ProxyFactory<T> toProxyFactory() {
        return new ProxyFactory<T>() {

            @Override
            public T getProxy(T target) {
                return DynamicPF.this.getProxy(target , new InvocationHandler() {
                    private volatile T proxy;

                    @Override
                    public Object invoke(Object proxy , Method method , Object[] args) throws Throwable {
                        if (DynamicPF.this.proxyMethods(this.proxy , method , args)) return DynamicPF.this.invoke(
                            this.proxy , method , args);

                        return method.invoke(this.proxy , args);
                    }

                    public InvocationHandler setProxy(T proxy) {
                        this.proxy = proxy;
                        return this;
                    }
                }.setProxy(target));
            }
        };
    }
}
