package com.github.andyshao.test.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title: Linked list interface<br>
 * Descript: Because of it is a linked list, you should use the {@link #clean()}
 * method when you
 * want to remove this list.<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 * @param <T> element type
 */
public interface Linked<D , T extends Linked.LinkedElmt<D , T>> extends Cleanable , Iterable<D> {

    public interface LinkedElmt<DATA , T extends Linked.LinkedElmt<DATA , T>> {

        public abstract DATA data();

        public default void free() {
            this.setData(null);
            this.setNext(null);
        }

        public abstract T next();

        public abstract void setData(DATA data);

        public abstract void setNext(T next);
    }

    public T head();

    public int size();

    public T tail();
}
