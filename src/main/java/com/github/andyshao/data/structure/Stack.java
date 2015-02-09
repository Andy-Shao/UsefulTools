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

    public D pop(D data);

    public D peek();

    public int size();

    public static
        <DATA , T extends Linked<DATA , ? extends Linked.LinkedElmt<DATA , ?>> & SingleLinkedOperation<DATA , ? extends Linked.LinkedElmt<DATA , ?>>>
        Stack<DATA> DEFAULT_STACK(T linked) {
        return new Stack<DATA>() {

            @Override
            public void push(DATA data) {
                linked.insNext(null , data);
            }

            @Override
            public DATA pop(DATA data) {
                return linked.remNext(null);
            }

            @Override
            public DATA peek() {
                return linked.head() == null ? null : linked.head().getData();
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
