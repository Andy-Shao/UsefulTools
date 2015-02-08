package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title:Single linked list interface<br>
 * Descript: Because of it is a linked list, you should use the {@link #list_destroy(List)} method when you 
 * want to remove this list.<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface List<D> {

    public class ListElmt<DATA> {
        public DATA data;
        public List.ListElmt<DATA> next;

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            List.ListElmt<DATA> that;
            if (obj instanceof List.ListElmt) {
                that = (ListElmt<DATA>) obj;
                return this.data.equals(that.data) && this.next.equals(that.next);
            } else {
                return false;
            }
        }

        public void free() {
            this.data = null;
            this.next = null;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.data , this.next);
        }

    }

    public static <DATA> DATA list_data(List.ListElmt<DATA> element) {
        return element.data;
    }

    public static <DATA> void list_destroy(List<DATA> list) {
        List.ListElmt<DATA> element = list.head();
        do {
            element.free();
            element = element.next;
        } while (!element.equals(list.tail()));
    }

    public static <DATA> boolean list_is_head(List<DATA> list , List.ListElmt<DATA> element) {
        return list.head().equals(element);
    }

    public static <DATA> boolean list_is_tail(List<DATA> list , List.ListElmt<DATA> element) {
        return list.tail().equals(element);
    }

    public static <DATA> List.ListElmt<DATA> list_next(List.ListElmt<DATA> element) {
        return element.next;
    }

    public static <DATA> int list_size(List<DATA> list) {
        return list.size();
    }

    public List.ListElmt<D> head();

    public void list_ins_next(List.ListElmt<D> element , D data);

    /**
     * remove the next element.<br/>
     * If the element is the tail, Then it won't remove anything.
     * @param element
     * @return if something is removed return data. if it doesn't return null.
     */
    public D list_rem_next(List.ListElmt<D> element);

    public int size();

    public List.ListElmt<D> tail();
}
