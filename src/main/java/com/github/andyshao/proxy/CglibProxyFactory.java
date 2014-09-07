package com.github.andyshao.proxy;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import com.github.andyshao.util.Reflects;

/**
 * 
 * Title:the proxy factory of cglib<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 17, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <T> the type of target
 */
public abstract class CglibProxyFactory<T> implements ProxyFactory<T> {

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
        public Object invoke(Object obj , Method method , Object[] args) throws Throwable {
            if (CglibProxyFactory.this.proxyMethods(this.proxied).contains(method)) {
                CglibProxyFactory.this.invoke(this.proxied , method , args);
            }
            return method.invoke(this.proxied , args);
        }

    }

    protected Class<?>[] getInterfaces(T target) {
        Set<Class<?>> set = new HashSet<>();
        Reflects.getInterfaces(target.getClass() , set);
        return set.toArray(new Class<?>[set.size()]);
    }

    @Override
    public T getProxy(T target) {
        return this.getProxy(target , new DefaultInvocationHandler(target));
    }

    @SuppressWarnings("unchecked")
    public T getProxy(T target , InvocationHandler invocationHandler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setInterfaces(this.getInterfaces(target));
        enhancer.setCallback(invocationHandler);
        return (T) enhancer.create();
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
     * @return the methods collection
     */
    protected abstract Collection<Method> proxyMethods(T target);
}
