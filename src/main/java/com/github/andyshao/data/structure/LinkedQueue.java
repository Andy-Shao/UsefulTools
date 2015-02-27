package com.github.andyshao.data.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 27, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <E> data
 */
public class LinkedQueue<E> implements Queue<E>{
    private final SingleLinked<E> linked;
    
    public LinkedQueue(SingleLinked<E> linked) {
        this.linked = linked;
    }
    
    public LinkedQueue() {
        this(SingleLinked.DEFAULT_SINGLE_LINKED());
    }

    @Override
    public int size() {
        return this.linked.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0 ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        for(E item : this.linked)
            if(Objects.equals(o , item)) return true;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return this.linked.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        
        int i = 0;
        for(E item : this.linked){
            result[i] = item;
            i++;
        }
        
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        this.linked.clear();
    }

    @Override
    public boolean add(E e) {
        return this.offer(e);
    }

    @Override
    public boolean offer(E e) {
        this.linked.list_ins_next(this.linked.tail() , e);
        return true;
    }

    @Override
    public E remove() {
        return this.poll();
    }

    @Override
    public E poll() {
        if(this.size() == 0) return null;
        return this.linked.list_rem_next(null);
    }

    @Override
    public E element() {
        return this.peek();
    }

    @Override
    public E peek() {
        if(this.size() == 0) return null;
        return this.linked.head().data();
    }

}
