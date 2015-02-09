package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title:Double Linked interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 9, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface DoubleLinked<D> extends List<D , DoubleLinked.DoubleLinkedElmt<D>> {
    public interface DoubleLinkedElmt<DATA> extends List.ListElmt<DATA , DoubleLinkedElmt<DATA>> {
        public static <DATA> DoubleLinkedElmt<DATA> DEFAULT_ELMT(DATA data) {
            return new DoubleLinkedElmt<DATA>() {
                private DATA data;
                private DoubleLinkedElmt<DATA> next;
                private DoubleLinkedElmt<DATA> prev;

                @SuppressWarnings("unchecked")
                @Override
                public boolean equals(Object obj) {
                    DoubleLinkedElmt<DATA> that;
                    if (obj instanceof DoubleLinkedElmt) {
                        that = (DoubleLinkedElmt<DATA>) obj;
                        return this.getData().equals(that.getData()) && this.getNext().equals(that.getNext())
                            && this.getPrev().equals(that.getPrev());
                    } else return false;
                }

                @Override
                public DATA getData() {
                    return this.data;
                }

                @Override
                public DoubleLinkedElmt<DATA> getNext() {
                    return this.next;
                }

                @Override
                public DoubleLinkedElmt<DATA> getPrev() {
                    return this.prev;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(this.getData() , this.getPrev() , this.getNext());
                }

                @Override
                public void setData(DATA data) {
                    this.data = data;
                }

                @Override
                public void setNext(DoubleLinkedElmt<DATA> next) {
                    this.next = next;
                }

                @Override
                public void setPrev(DoubleLinkedElmt<DATA> prev) {
                    this.prev = prev;
                }
            };
        }

        public DoubleLinkedElmt<DATA> getPrev();

        public void setPrev(DoubleLinkedElmt<DATA> prev);
    }

    public static <DATA> DoubleLinked<DATA> DEFAULT_DOUBLE_LINKED() {
        return new DoubleLinked<DATA>() {
            private DoubleLinked.DoubleLinkedElmt<DATA> head;
            private int size;
            private DoubleLinked.DoubleLinkedElmt<DATA> tail;

            @Override
            public boolean clean() {
                do
                    if (this.size == 0) return true;
                    else if (this.size == 1) {
                        this.head.free();
                        this.head = null;
                        this.tail = null;
                    } else this.dlist_remove(this.head);
                while (this.size != 0);

                return false;
            }

            @Override
            public void dlist_ins_next(DoubleLinked.DoubleLinkedElmt<DATA> element , DATA data) {
                //Do not allow a NULL element unless the list is empty.
                if (element == null && this.size() != 0) return;

                DoubleLinked.DoubleLinkedElmt<DATA> new_element =
                    DoubleLinked.DoubleLinkedElmt.<DATA> DEFAULT_ELMT(data);

                if (this.size() == 0) {
                    //Handle insertion when the list is empty.
                    this.head = new_element;
                    this.head.setPrev(null);
                    this.head.setNext(null);
                    this.tail = new_element;
                } else {
                    //Handle insertion when the list is not empty.
                    new_element.setNext(element.getNext());
                    new_element.setPrev(element);

                    if (element.getNext() == null) this.tail = new_element;
                    else element.getNext().setPrev(new_element);
                }

                //Adjust the size of the list to account for the inserted element.
                this.size++;

            }

            @Override
            public void dlist_ins_prev(DoubleLinked.DoubleLinkedElmt<DATA> element , DATA data) {
                //Do not allowed a NULL element unless the list is empty.
                if (element == null && this.size() != 0) return;

                DoubleLinked.DoubleLinkedElmt<DATA> new_element =
                    DoubleLinked.DoubleLinkedElmt.<DATA> DEFAULT_ELMT(data);

                if (this.size() == 0) {
                    //Handle insertion when the list is empty.
                    this.head = new_element;
                    this.head.setPrev(null);
                    this.head.setNext(null);
                    this.tail = new_element;
                } else {
                    //Handle insertion when the list is not empty.
                    new_element.setNext(element);
                    new_element.setPrev(element.getPrev());

                    if (element.getPrev() == null) this.head = new_element;
                    else element.getPrev().setNext(new_element);

                    element.setPrev(new_element);
                }

                //Adjust the size of list to accoutn for the element.
                this.size++;
            }

            @Override
            public DATA dlist_remove(DoubleLinked.DoubleLinkedElmt<DATA> element) {
                DATA data = null;

                //Do not allow a NULL element or removal from an empty list.
                if (element == null || this.size() == 0) return null;

                //Remove the element from the list.
                data = element.getData();

                if (element.equals(this.head)) {
                    //Handle removal from the head of the list.
                    this.head = element.getNext();

                    if (this.head == null) this.tail = null;
                    else element.getNext().setPrev(null);
                }

                //Free the storage allocated by the abstract datatype.
                element.free();

                //Adjust the size of the list to account for the removed element.
                this.size--;

                return data;
            }

            @Override
            public DoubleLinked.DoubleLinkedElmt<DATA> head() {
                return this.head;
            }

            @Override
            public int size() {
                return this.size;
            }

            @Override
            public DoubleLinked.DoubleLinkedElmt<DATA> tail() {
                return this.tail;
            }

        };
    }

    public void dlist_ins_next(DoubleLinkedElmt<D> element , D data);

    public void dlist_ins_prev(DoubleLinkedElmt<D> element , D data);

    public D dlist_remove(DoubleLinkedElmt<D> element);
}
