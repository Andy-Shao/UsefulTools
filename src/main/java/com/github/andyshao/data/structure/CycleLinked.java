package com.github.andyshao.data.structure;

import java.util.Objects;

/**
 * 
 * Title: Cycle Linked interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 9, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface CycleLinked<D> extends Linked<D , CycleLinked.CycleLinkedElmt<D>>, SingleLinkedOperation<D , CycleLinked.CycleLinkedElmt<D>> {

    public interface CycleLinkedElmt<DATA> extends Linked.LinkedElmt<DATA , CycleLinked.CycleLinkedElmt<DATA>> {
        public static <DAT> CycleLinked.CycleLinkedElmt<DAT> DEFAULT_CYCLE_ELMT(DAT data) {
            CycleLinked.CycleLinkedElmt<DAT> result = new CycleLinked.CycleLinkedElmt<DAT>() {
                private DAT data;
                private CycleLinked.CycleLinkedElmt<DAT> next;

                @SuppressWarnings("unchecked")
                @Override
                public boolean equals(Object obj) {
                    CycleLinked.CycleLinkedElmt<DAT> that;
                    if (obj instanceof CycleLinked.CycleLinkedElmt) {
                        that = (CycleLinkedElmt<DAT>) obj;
                        if (this.next == this) return Objects.equals(this.data , that.getData());
                        else return Objects.equals(this.data , that.getData())
                            && Objects.equals(this.next , that.getNext());
                    } else return false;
                }

                @Override
                public DAT getData() {
                    return this.data;
                }

                @Override
                public CycleLinkedElmt<DAT> getNext() {
                    return this.next;
                }

                @Override
                public int hashCode() {
                    if (this.next == this) return this.data.hashCode();
                    else return Objects.hash(this.data , this.next);
                }

                @Override
                public void setData(DAT data) {
                    this.data = data;
                }

                @Override
                public void setNext(CycleLinkedElmt<DAT> next) {
                    this.next = next;
                }
            };
            result.setData(data);

            return result;
        }
    }

    public static <DATA> CycleLinked<DATA> DEFAULT_CYCLE_LINKED() {
        return new CycleLinked<DATA>() {
            private CycleLinked.CycleLinkedElmt<DATA> head;
            private int size;

            @Override
            public boolean clean() {
                do
                    if (this.size == 0) return true;
                    else if (this.size == 1) {
                        this.head.free();
                        this.head = null;
                        return true;
                    } else this.remNext(this.head);
                while (this.size != 0);

                return false;
            }

            @Override
            public CycleLinked.CycleLinkedElmt<DATA> head() {
                return this.head;
            }

            @Override
            public void insNext(CycleLinked.CycleLinkedElmt<DATA> element , final DATA data) {
                CycleLinked.CycleLinkedElmt<DATA> new_element =
                    CycleLinked.CycleLinkedElmt.<DATA> DEFAULT_CYCLE_ELMT(data);

                if (this.size == 0) {
                    //Handle insertion when the list is empty.
                    new_element.setNext(new_element);
                    this.head = new_element;
                } else {
                    //Handle insertion when the list is not empty.
                    new_element.setNext(element.getNext());
                    element.setNext(new_element);
                }

                //Adjust the size of the list to account for the inserted element.
                this.size++;
            }

            @Override
            public DATA remNext(CycleLinked.CycleLinkedElmt<DATA> element) {
                CycleLinked.CycleLinkedElmt<DATA> old_element =
                    CycleLinked.CycleLinkedElmt.<DATA> DEFAULT_CYCLE_ELMT(null);
                DATA data = null;

                //Do not allow removal from an empty list.
                if (this.size == 0) return null;

                //Remove the element from the list.
                data = element.getNext().getData();
                if (element.getNext() == element) {
                    //Handle removing the last element.
                    old_element = element.getNext();
                    this.head = null;
                } else {
                    //Handle removing other than the last element.
                    old_element = element.getNext();
                    element.setNext(element.getNext().getNext());
                    if (old_element == this.head) this.head = old_element.getNext();
                }

                //Free the storage allocated by the abstract datatype.
                old_element.free();

                //Adjust the size of the list to account for the removed element.
                this.size--;

                return data;
            }

            @Override
            public int size() {
                return this.size;
            }

            @Override
            public CycleLinked.CycleLinkedElmt<DATA> tail() {
                return this.head;
            }

        };
    }

    @Override
    public void insNext(CycleLinked.CycleLinkedElmt<D> element , final D data);

    @Override
    public D remNext(CycleLinked.CycleLinkedElmt<D> element);
}
