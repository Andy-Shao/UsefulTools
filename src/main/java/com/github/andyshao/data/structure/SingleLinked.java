package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public class SingleLinked<D> implements List<D> {
    private List.ListElmt<D> head = null;
    private int size = 0;
    private List.ListElmt<D> tail = null;

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        SingleLinked<D> that;
        if (obj instanceof SingleLinked) {
            that = (SingleLinked<D>) obj;
            return this.head.equals(that.head) && this.tail.equals(that.tail) && this.size == that.size;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.size , this.head , this.tail);
    }

    @Override
    public com.github.andyshao.data.structure.List.ListElmt<D> head() {
        return this.head;
    }

    @Override
    public void list_ins_next(com.github.andyshao.data.structure.List.ListElmt<D> element , D data) {
        List.ListElmt<D> new_element = new List.ListElmt<>();

        //Insert the element into the list.
        new_element.data = data;
        if (element == null) {
            //Handle insertion at the head of the list.
            if (List.list_size(this) == 0) {
                this.tail = new_element;
            }

            new_element.next = this.head;
            this.head = new_element;
        } else {
            //Handle insertion somewhere other than at the head.
            if (element.next == null) {
                this.tail = new_element;
            }

            new_element.next = element.next;
            element.next = new_element;
        }

        //Adjust the size of the list to account for the inserted element.
        this.size++;
    }

    @Override
    public D list_rem_next(com.github.andyshao.data.structure.List.ListElmt<D> element) {
        List.ListElmt<D> old_element = new List.ListElmt<>();
        D data = null;

        //Do not allow removal from an empty list.
        if (List.list_size(this) == 0) { return null; }

        //Remove the element from the list.
        if (element == null) {
            //Handle removal from the head of the list.
            data = this.head.data;
            old_element = this.head;
            this.head = this.head.next;

            if (List.list_size(this) == 1) {
                this.tail = null;
            }
        } else {
            if (element.next == null) { return null; }

            data = element.next.data;
            old_element = element.next;
            element.next = element.next.next;

            if (element.next == null) {
                this.tail = element;
            }
        }

        //Free the storage allocated by the abstract datatype.
        old_element.free();

        //Adjust the size of the list of account for the removed element.
        this.size--;

        return data;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public com.github.andyshao.data.structure.List.ListElmt<D> tail() {
        return this.tail;
    }
}
