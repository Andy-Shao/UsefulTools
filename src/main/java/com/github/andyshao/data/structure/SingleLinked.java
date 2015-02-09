package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title: Single Linked interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 8, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface SingleLinked<D> extends List<D , SingleLinked.SingleLinkedElmt<D>> {
    public interface SingleLinkedElmt<D> extends List.ListElmt<D , SingleLinkedElmt<D>> {
        public static <DATA> SingleLinkedElmt<DATA> DEFAULT_ELMT(DATA data) {
            return new SingleLinkedElmt<DATA>() {
                private DATA data;
                private SingleLinkedElmt<DATA> next;

                @SuppressWarnings("unchecked")
                @Override
                public boolean equals(Object obj) {
                    SingleLinkedElmt<DATA> that;
                    if (obj instanceof SingleLinkedElmt) {
                        that = (SingleLinkedElmt<DATA>) obj;
                        return this.getData().equals(that.getData()) && this.getNext().equals(that.getNext());
                    } else return false;
                }

                @Override
                public DATA getData() {
                    return this.data;
                }

                @Override
                public SingleLinkedElmt<DATA> getNext() {
                    return this.next;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(this.getData() , this.getNext());
                }

                @Override
                public void setData(DATA data) {
                    this.data = data;
                }

                @Override
                public void setNext(SingleLinkedElmt<DATA> next) {
                    this.next = next;
                }
            };
        }
    }

    public static <DATA> SingleLinked<DATA> DEFAULT_SINGLE_LINKED() {
        return new SingleLinked<DATA>() {
            private SingleLinkedElmt<DATA> head;
            private int size = 0;
            private SingleLinkedElmt<DATA> tail;

            @Override
            public boolean clean() {
                do
                    if (this.size == 0) return true;
                    else if (this.size == 1) {
                        this.head.free();
                        this.head = null;
                        this.tail = null;
                        return true;
                    } else this.list_rem_next(this.head);
                while (this.size != 0);
                
                return false;
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean equals(Object obj) {
                SingleLinked<DATA> that;
                if (obj instanceof SingleLinked) {
                    that = (SingleLinked<DATA>) obj;
                    return this.size() == that.size() && this.head().equals(that.head())
                        && this.tail().equals(that.tail());
                } else return false;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.size() , this.head() , this.tail());
            }

            @Override
            public SingleLinked.SingleLinkedElmt<DATA> head() {
                return this.head;
            }

            @Override
            public void list_ins_next(SingleLinked.SingleLinkedElmt<DATA> element , DATA data) {
                SingleLinked.SingleLinkedElmt<DATA> new_element =
                    SingleLinked.SingleLinkedElmt.<DATA> DEFAULT_ELMT(data);

                if (element == null) {
                    //Handle insertion at the head of the list.
                    if (this.size() == 0) this.tail = new_element;

                    new_element.setNext(this.head);
                    this.head = new_element;
                } else {
                    //Handle insertion somewhere other than at the head.
                    if (element.getNext() == null) this.tail = new_element;

                    new_element.setNext(element.getNext());
                    element.setNext(new_element);
                }

                //Adjust the size of the list to account for the inserted element.
                this.size++;
            }

            @Override
            public DATA list_rem_next(SingleLinked.SingleLinkedElmt<DATA> element) {
                SingleLinked.SingleLinkedElmt<DATA> old_element =
                    SingleLinked.SingleLinkedElmt.<DATA> DEFAULT_ELMT(null);
                DATA data = null;

                //Do not allow removal from an empty list.
                if (this.size() == 0) return null;

                //Remove the element from the list.
                if (element == null) {
                    //Handle removal from the head of the list.
                    data = this.head.getData();
                    old_element = this.head;
                    this.head = this.head.getNext();

                    if (this.size() == 1) this.tail = null;
                } else {
                    if (element.getNext() == null) return null;

                    data = element.getNext().getData();
                    old_element = element.getNext();
                    element.setNext(element.getNext().getNext());

                    if (element.getNext() == null) this.tail = element;
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
            public SingleLinked.SingleLinkedElmt<DATA> tail() {
                return this.tail;
            }
        };
    }

    public void list_ins_next(SingleLinkedElmt<D> element , D data);

    /**
     * Remove the next element.<br>
     * If the element is the tail, Then it won't remove anything.
     * 
     * @param element the item of linked's
     * @return if something is removed return data. If it doesn't return null.
     */
    public D list_rem_next(SingleLinkedElmt<D> element);
}
