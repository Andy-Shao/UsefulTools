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
public interface DoubleLinked<D> extends Linked<D , DoubleLinked.DoubleLinkedElmt<D>> {
    public interface DoubleLinkedElmt<DATA> extends Linked.LinkedElmt<DATA , DoubleLinkedElmt<DATA>> {
        public static <DATA> DoubleLinkedElmt<DATA> DEFAULT_ELMT(DATA data) {
            DoubleLinkedElmt<DATA> result = new DoubleLinkedElmt<DATA>() {
                private DATA data;
                private DoubleLinkedElmt<DATA> next;
                private DoubleLinkedElmt<DATA> prev;

                @SuppressWarnings("unchecked")
                @Override
                public boolean equals(Object obj) {
                    DoubleLinkedElmt<DATA> that;
                    if (obj instanceof DoubleLinkedElmt) {
                        that = (DoubleLinkedElmt<DATA>) obj;
                        return Objects.equals(this.getData() , that.getData())
                            && Objects.equals(this.getNext() , that.getNext())
                            && Objects.equals(this.getPrev() , that.getPrev());
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
            result.setData(data);

            return result;
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
                    this.remove(this.head);
                while (this.size != 0);

                return false;
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean equals(Object obj) {
                DoubleLinked<DATA> that;
                if (obj instanceof DoubleLinked) {
                    that = (DoubleLinked<DATA>) obj;
                    return this.size == that.size() && Objects.equals(this.head() , that.head())
                        && Objects.equals(this.tail() , that.tail());
                } else return false;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.head , this.size , this.tail);
            }

            @Override
            public DoubleLinked.DoubleLinkedElmt<DATA> head() {
                return this.head;
            }

            @Override
            public void insNext(DoubleLinked.DoubleLinkedElmt<DATA> element , final DATA data) {
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

                    element.setNext(new_element);
                }

                //Adjust the size of the list to account for the inserted element.
                this.size++;

            }

            @Override
            public void insPrev(DoubleLinked.DoubleLinkedElmt<DATA> element , final DATA data) {
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
            public DATA remove(DoubleLinked.DoubleLinkedElmt<DATA> element) {
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
            public int size() {
                return this.size;
            }

            @Override
            public DoubleLinked.DoubleLinkedElmt<DATA> tail() {
                return this.tail;
            }

        };
    }

    public void insNext(DoubleLinkedElmt<D> element , final D data);

    public void insPrev(DoubleLinkedElmt<D> element , final D data);

    public D remove(DoubleLinkedElmt<D> element);
}
