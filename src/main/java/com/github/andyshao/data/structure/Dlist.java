package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title:Double linked list interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface Dlist<D> {

    public class DlistElmt<DATA>{
        public DATA data;
        public Dlist.DlistElmt<DATA> prev;
        public Dlist.DlistElmt<DATA> next;
        
        public void free(){
            this.data = null;
            this.prev = null;
            this.next = null;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.data, this.prev, this.next);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            Dlist.DlistElmt<DATA> that;
            if(obj instanceof Dlist.DlistElmt){
                that = (DlistElmt<DATA>) obj;
                return this.data.equals(that.data) && this.prev.equals(that.prev) && this.next.equals(that.next);
            } else return false;
        }
    }
    
    public int size();
    public Dlist.DlistElmt<D> head();
    public Dlist.DlistElmt<D> tail();
    
    public void dlist_ins_next(Dlist.DlistElmt<D> element, final D data);
    public void dlist_ins_prev(Dlist.DlistElmt<D> element, final D data);
    public D dlist_remove(Dlist.DlistElmt<D> element);
    
    public static <DATA> int dlist_size(Dlist<DATA> dlist){
        return dlist.size();
    }
    public static <DATA> Dlist.DlistElmt<DATA> dlist_head(Dlist<DATA> dlist){
        return dlist.head();
    }
    public static <DATA> Dlist.DlistElmt<DATA> dlist_tail(Dlist<DATA> dlist){
        return dlist.tail();
    }
    public static <DATA> boolean dlist_is_head(Dlist.DlistElmt<DATA> element){
        return element.prev == null ? true : false;
    }
    public static <DATA> boolean dlist_is_tail(Dlist.DlistElmt<DATA> element){
        return element.next == null ? true : false;
    }
    public static <DATA> DATA dlist_data(Dlist.DlistElmt<DATA> element){
        return element.data;
    }
    public static <DATA> Dlist.DlistElmt<DATA> dlist_next(Dlist.DlistElmt<DATA> element){
        return element.next;
    }
    public static <DATA> Dlist.DlistElmt<DATA> dlist_prev(Dlist.DlistElmt<DATA> element){
        return element.prev;
    }
    public static <DATA> void dlist_distroy(Dlist<DATA> dlist){
        Dlist.DlistElmt<DATA> element = dlist.head();
        do{
            element.free();
            element = element.next;
        }while(!element.equals(dlist.tail()));
    }
}
