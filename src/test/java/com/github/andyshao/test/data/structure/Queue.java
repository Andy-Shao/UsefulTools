package com.github.andyshao.test.data.structure;

import java.util.Iterator;

import com.github.andyshao.data.structure.Linked;
import com.github.andyshao.data.structure.SingleLinkedOperation;
import com.github.andyshao.lang.Cleanable;

@Deprecated
public interface Queue<D> extends Cleanable , Iterable<D> {

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Queue<DATA> DEFAULT_QUEUE(T linked) {
        return new Queue<DATA>() {

            @Override
            public void clear() {
                linked.clear();
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
            public Iterator<DATA> iterator() {
                return linked.iterator();
            }

            @Override
            public DATA peek() {
                return linked.head() == null ? null : linked.head().data();
            }

            @Override
            public int size() {
                return linked.size();
            }
        };
    }

    public D dequeue();

    public void enqueue(D data);

    public D peek();

    public int size();
}
