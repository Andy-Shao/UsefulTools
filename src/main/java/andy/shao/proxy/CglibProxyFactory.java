package andy.shao.proxy;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import andy.shao.util.Reflects;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 17, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <T>
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
            // TODO Auto-generated constructor stub
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object obj , Method method , Object[] args) throws Throwable {
            // TODO Auto-generated method stub
            if (CglibProxyFactory.this.proxyMethods(this.proxied).contains(method)) CglibProxyFactory.this.invoke(
                this.proxied , method , args);
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
        // TODO Auto-generated method stub
        return getProxy(target , new DefaultInvocationHandler(target));
    }

    /**
     * 
     * @param target
     * @param invocationHandler
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getProxy(T target , InvocationHandler invocationHandler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setInterfaces(this.getInterfaces(target));
        enhancer.setCallback(invocationHandler);
        return (T) enhancer.create();
    }

    /**
     * 
     * @param target
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    protected abstract Object invoke(T target , Method method , Object[] args) throws Throwable;

    /**
     * 
     * @param target
     * @return
     */
    protected abstract Collection<Method> proxyMethods(T target);
}
