package com.github.andyshao.data.structure;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 10, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> date
 * @param <T> element type
 */
public interface SingleLinkedOperation<D,T extends Linked.LinkedElmt<D , T>> {

    public void insNext(T element , final D data);

    /**
     * Remove the next element.<br>
     * If the element is the tail, Then it won't remove anything.
     * 
     * @param element the item of linked's
     * @return if something is removed return data. If it doesn't return null.
     */
    public D remNext(T element);
}
