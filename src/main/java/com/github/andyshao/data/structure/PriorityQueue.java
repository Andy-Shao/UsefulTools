package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Heap.MyHeap;
import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 18, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface PriorityQueue<D> extends Cleanable{

    public void pqueue_insert(D data);
    public D pqueue_extract();
    public D pqueue_peek();
    public int size();
    
    public class MyPriorityQueue<DATA> extends MyHeap<DATA> implements PriorityQueue<DATA>{

        @Override
        public void pqueue_insert(DATA data) {
            this.heap_insert(data);
        }

        @Override
        public DATA pqueue_extract() {
            return this.heap_extract();
        }

        @Override
        public DATA pqueue_peek() {
            return this.tree.size() == 0 ? null : this.tree.get(0);
        }

    }
}
