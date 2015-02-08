package com.github.andyshao.data.structure;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D>
 */
public class DoubleLinked<D> implements Dlist<D> {
    private int size = 0;
    private Dlist.DlistElmt<D> head = null;
    private Dlist.DlistElmt<D> tail = null;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public com.github.andyshao.data.structure.Dlist.DlistElmt<D> head() {
        return this.head;
    }

    @Override
    public com.github.andyshao.data.structure.Dlist.DlistElmt<D> tail() {
        return this.tail;
    }

    @Override
    public void dlist_ins_next(com.github.andyshao.data.structure.Dlist.DlistElmt<D> element , D data) {
        //Do not allow a NULL element unless the list is empty.
        if(element == null && Dlist.dlist_size(this)!=0)
            return;
        
        Dlist.DlistElmt<D> new_element = new Dlist.DlistElmt<>();

        //Insert the new element into the list.
        new_element.data = data;
        
        if(Dlist.dlist_size(this) == 0){
            //Handle insertion when the list is empty.
            this.head = new_element;
            this.head.prev = null;
            this.head.next = null;
            this.tail = new_element;
        } else {
            //Handle insertion when the list is not empty.
            new_element.next = element.next;
            new_element.prev = element;
            
            if(element.next == null){
                this.tail = new_element;
            } else element.next.prev = new_element;
        }
        
        //Adjust the size of the list to account for the inserted element.
        this.size++;
    }

    @Override
    public void dlist_ins_prev(com.github.andyshao.data.structure.Dlist.DlistElmt<D> element , D data) {
        //Do not allowed a NULL element unless the list is empty.
        if(element == null && Dlist.dlist_size(this) != 0)
            return;
        
        Dlist.DlistElmt<D> new_element = new Dlist.DlistElmt<>();
        
        //Insert the new element into the list.
        new_element.data = data;
        if(Dlist.dlist_size(this) == 0){
            //Handle insertion when the list is empty.
            this.head = new_element;
            this.head.prev = null;
            this.head.next = null;
            this.tail = new_element;
        } else {
            //Handle insertion when the list is not empty.
            new_element.next = element;
            new_element.prev = element.prev;
            
            if(element.prev == null) this.head = new_element;
            else element.prev.next = new_element;
            
            element.prev = new_element;
        }
        
        //Adjust the size of list to accoutn for the element.
        this.size++;
    }

    @Override
    public D dlist_remove(com.github.andyshao.data.structure.Dlist.DlistElmt<D> element) {
        D data = null;
        
        //Do not allow a NULL element or removal from an empty list.
        if(element == null || Dlist.dlist_size(this)==0)
            return null;
        
        //Remove the element from the list.
        data = element.data;
        
        if(element.equals(this.head)){
            //Handle removal from the head of the list.
            this.head = element.next;
            
            if(this.head == null) this.tail = null;
            else element.next.prev = null;
        }
        
        //Free the storage allocated by the abstract datatype.
        element.free();
        
        //Adjust the size of the list to account for the removed element.
        this.size--;
        
        return data;
    }

}
