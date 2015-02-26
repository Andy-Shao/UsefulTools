package com.github.andyshao.test.data.structure.util;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.andyshao.test.data.structure.SingleLinked;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 26, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public class SingleLinkedStream<D>{
    public class SinglLinkedSpliterator<DATA> implements Spliterator<DATA>{
        private final SingleLinked<DATA> linked;
        public SinglLinkedSpliterator(SingleLinked<DATA> linked) {
            this.linked = linked;
        }

        @Override
        public boolean tryAdvance(Consumer<? super DATA> action) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Spliterator<DATA> trySplit() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long estimateSize() {
            // TODO Auto-generated method stub
            this.linked.size();
            return 0;
        }

        @Override
        public int characteristics() {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }

    public Stream<D> stream(SingleLinked<D> in) {
        return StreamSupport.<D>stream(new SinglLinkedSpliterator<D>(in) , false);
    }
    
    
}
