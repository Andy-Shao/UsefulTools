package com.github.andyshao.lang;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 9, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@FunctionalInterface
public interface Cleanable {

    /**
     * clean the memory
     * 
     * @return if clean action is successful return true. Others return false
     */
    boolean clean();
}
