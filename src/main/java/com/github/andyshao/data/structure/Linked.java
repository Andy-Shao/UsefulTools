package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title: Linked list interface<br>
 * Descript: Because of it is a linked list, you should use the
 * {@link #clean()} method when you
 * want to remove this list.<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 * @param <T> element type
 */
public interface Linked<D , T extends Linked.LinkedElmt<D , T>> extends Cleanable, Iterable<D>{

    public interface LinkedElmt<DATA , T extends Linked.LinkedElmt<DATA , T>> {

        public default void free() {
            this.setData(null);
            this.setNext(null);
        }

        public abstract DATA list_Data();

        public abstract T list_next();

        public abstract void setData(DATA data);

        public abstract void setNext(T next);
    }

    public T list_head();

    public default boolean list_is_head(LinkedElmt<D , T> element) {
        return this.list_head().equals(element);
    }

    public default boolean list_is_tail(LinkedElmt<D , T> element) {
        return this.tail().equals(element);
    }

    public int size();

    public T tail();
}
