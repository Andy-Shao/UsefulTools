package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title: Linked list interface<br>
 * Descript: Because of it is a linked list, you should use the
 * {@link #list_destroy(List)} method when you
 * want to remove this list.<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 * @param <T> element type
 */
public interface List<D , T extends List.ListElmt<D , T>> extends Cleanable {

    public interface ListElmt<DATA , T extends List.ListElmt<DATA , T>> {

        public default void free() {
            this.setData(null);
            this.setNext(null);
        }

        public abstract DATA getData();

        public abstract T getNext();

        public abstract void setData(DATA data);

        public abstract void setNext(T next);
    }

    public T head();

    public default boolean list_is_head(ListElmt<D , T> element) {
        return this.head().equals(element);
    }

    public default boolean list_is_tail(ListElmt<D , T> element) {
        return this.tail().equals(element);
    }

    public int size();

    public T tail();
}
