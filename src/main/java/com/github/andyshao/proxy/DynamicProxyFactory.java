package com.github.andyshao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * Title:the proxy of java core API<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 17, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <T> the type of target
 */
public abstract class DynamicProxyFactory<T> implements ProxyFactory<T> {

    /**
     * 
     * Title:<br>
     * Descript:<br>
     * Copyright: Copryright(c) Mar 17, 2014<br>
     * Encoding:UNIX UTF-8
     * 
     * @author Andy.Shao
     *
     */
    public class DefaultInvocationHandler implements InvocationHandler {
        private final T proxied;

        public DefaultInvocationHandler(T proxied) {
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object proxy , Method method , Object[] args) throws Throwable {
            if (DynamicProxyFactory.this.proxyMethods(this.proxied , method , args)) return DynamicProxyFactory.this
                .invoke(this.proxied , method , args);
            return method.invoke(this.proxied , args);
        }

    }

    @Override
    public T getProxy(T target) {
        return this.getProxy(target , new DefaultInvocationHandler(target));
    }

    @SuppressWarnings("unchecked")
    public T getProxy(T target , InvocationHandler invocationHandler) {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader() , ProxyFactory.<T> allInterfaces(target) ,
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
    protected abstract Object invoke(T target , Method method , Object[] args) throws Throwable;

    /**
     * the methods which will be proxied
     * 
     * @param target the target which will be proxy
     * @param method the method which will be invoke
     * @param args the args of method
     * @return the methods collection
     */
    protected abstract boolean proxyMethods(T target , Method method , Object[] args);

}
