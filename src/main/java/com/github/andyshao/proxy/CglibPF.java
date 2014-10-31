package com.github.andyshao.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public interface CglibPF<T> {
	public abstract Class<?>[] implInterfaces(); 

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
    public abstract Object invoke(T target , Method method , Object[] args) throws Throwable;

    /**
     * the methods which will be proxied
     * 
     * @param target the target which will be proxy
     * @param method the method which will be invoke
     * @param args the args of method
     * @return the methods collection
     */
    public abstract boolean proxyMethods(T target , Method method , Object[] args);
    
    @SuppressWarnings("unchecked")
    public default T getProxy(T target , InvocationHandler invocationHandler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setInterfaces(ProxyFactory.<T>allInterfaces(target));
        enhancer.setCallback(invocationHandler);
        return (T) enhancer.create();
    }
    
    public default ProxyFactory<T> toProxyFactroy(){
    	return new ProxyFactory<T>() {
			@Override
			public T getProxy(T target) {
				return CglibPF.this.getProxy(target, new InvocationHandler() {
					private volatile T proxy;
					
					public InvocationHandler setProxy(T proxy) {
						this.proxy = proxy;
						return this;
					}

					@Override
					public Object invoke(Object target, Method method, Object[] args) throws Throwable {
						if(CglibPF.this.proxyMethods(this.proxy, method, args)){
							return CglibPF.this.invoke(this.proxy, method, args);
						}
						
						return method.invoke(this.proxy, args);
					}
				}.setProxy(target));
			}
		};
    }
}
