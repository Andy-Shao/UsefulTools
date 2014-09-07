package com.github.andyshao.proxy;

/**
 * 
 * Title:public proxy factory interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 17, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <T> the type of target which will be proxy
 */
@FunctionalInterface
public interface ProxyFactory<T> {

    /**
     * get the proxy
     * 
     * @param target the target which will be proxy
     * @return the proxy
     */
    T getProxy(T target);
}
