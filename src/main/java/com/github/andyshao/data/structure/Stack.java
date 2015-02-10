package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 10, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface Stack<D> extends Cleanable {

    public void push(final D data);

    public D pop();

    public D peek();

    public int size();

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E>, T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Stack<DATA> DEFAULT_STACK(T linked) {
        return new Stack<DATA>() {

            @Override
            public void push(DATA data) {
                linked.list_ins_next(null , data);
            }

            @Override
            public DATA pop() {
                return linked.list_rem_next(null);
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
            public boolean clean() {
                return linked.clean();
            }
        };
    }

}
