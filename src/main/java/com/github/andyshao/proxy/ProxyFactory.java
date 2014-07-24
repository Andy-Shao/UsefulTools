package com.github.andyshao.proxy;

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
public interface ProxyFactory<T> {

    /**
     * 
     * @param target
     * @return
     */
    T getProxy(T target);
}
