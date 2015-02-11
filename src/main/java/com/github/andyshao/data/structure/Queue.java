package com.github.andyshao.data.structure;

import java.util.Iterator;

import com.github.andyshao.lang.Cleanable;

public interface Queue<D> extends Cleanable, Iterable<D> {

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Queue<DATA> DEFAULT_QUEUE(T linked) {
        return new Queue<DATA>() {

            @Override
            public boolean clean() {
                return linked.clean();
            }

            @Override
            public DATA dequeue() {
                return linked.list_rem_next(null);
            }

            @Override
            public void enqueue(DATA data) {
                linked.list_ins_next(linked.tail() , data);
            }

            @Override
            public DATA peek() {
                return linked.list_head() == null ? null : linked.list_head().list_Data();
            }

            @Override
            public int size() {
                return linked.size();
            }

            @Override
            public Iterator<DATA> iterator() {
                return linked.iterator();
            }
        };
    }

    public D dequeue();

    public void enqueue(D data);

    public D peek();

    public int size();
}
