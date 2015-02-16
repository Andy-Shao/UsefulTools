package com.github.andyshao.data.structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
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
public interface SingleLinked<D> extends Linked<D , SingleLinked.SingleLinkedElmt<D>> ,
    SingleLinkedOperation<D , SingleLinked.SingleLinkedElmt<D>> {
    class MySingleLinked<D> implements SingleLinked<D> {
        private class MyIterator implements Iterator<D> {
            private final long actionCount = MySingleLinked.this.actionCount;
            private volatile SingleLinked.SingleLinkedElmt<D> index = MySingleLinked.this.list_head();

            @Override
            public boolean hasNext() {
                if (this.actionCount != MySingleLinked.this.actionCount) throw new ConcurrentModificationException();
                return this.index != null;
            }

            @Override
            public D next() {
                SingleLinked.SingleLinkedElmt<D> result = this.index;
                this.index = this.index.list_next();
                return result.list_Data();
            }

        }

        private long actionCount = 0;
        private SingleLinkedElmt<D> head;
        private int size = 0;

        private SingleLinkedElmt<D> tail;

        @Override
        public void clean() {
            do
                if (this.size == 0) return;
                else if (this.size == 1) {
                    this.head.free();
                    this.head = null;
                    this.tail = null;
                    this.size = 0;
                    return;
                } else this.list_rem_next(this.head);
            while (this.size != 0);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            SingleLinked<D> that;
            if (obj instanceof SingleLinked) {
                that = (SingleLinked<D>) obj;
                return this.size() == that.size() && Objects.equals(this.list_head() , that.list_head())
                    && Objects.equals(this.tail() , that.tail());
            } else return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.size() , this.list_head() , this.tail());
        }

        @Override
        public Iterator<D> iterator() {
            return this.new MyIterator();
        }

        @Override
        public SingleLinked.SingleLinkedElmt<D> list_head() {
            return this.head;
        }

        @Override
        public void list_ins_next(SingleLinked.SingleLinkedElmt<D> element , final D data) {
            SingleLinked.SingleLinkedElmt<D> new_element = SingleLinked.SingleLinkedElmt.<D> DEFAULT_ELMT(data);

            if (element == null) {
                //Handle insertion at the head of the list.
                if (this.size() == 0) this.tail = new_element;

                new_element.setNext(this.head);
                this.head = new_element;
            } else {
                //Handle insertion somewhere other than at the head.
                if (element.list_next() == null) this.tail = new_element;

                new_element.setNext(element.list_next());
                element.setNext(new_element);
            }

            //Adjust the size of the list to account for the inserted element.
            this.size++;
            this.actionCount++;
        }

        @Override
        public D list_rem_next(SingleLinked.SingleLinkedElmt<D> element) {
            SingleLinked.SingleLinkedElmt<D> old_element = SingleLinked.SingleLinkedElmt.<D> DEFAULT_ELMT(null);
            D data = null;

            //Do not allow removal from an empty list.
            if (this.size() == 0) return null;

            //Remove the element from the list.
            if (element == null) {
                //Handle removal from the head of the list.
                data = this.head.list_Data();
                old_element = this.head;
                this.head = this.head.list_next();

                if (this.size() == 1) this.tail = null;
            } else {
                if (element.list_next() == null) return null;

                data = element.list_next().list_Data();
                old_element = element.list_next();
                element.setNext(element.list_next().list_next());

                if (element.list_next() == null) this.tail = element;
            }

            //Free the storage allocated by the abstract datatype.
            old_element.free();

            //Adjust the size of the list of account for the removed element.
            this.size--;
            this.actionCount++;

            return data;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public SingleLinked.SingleLinkedElmt<D> tail() {
            return this.tail;
        }
    }

    public interface SingleLinkedElmt<D> extends Linked.LinkedElmt<D , SingleLinkedElmt<D>> {
        public static <DATA> SingleLinkedElmt<DATA> DEFAULT_ELMT(DATA data) {
            SingleLinkedElmt<DATA> result = new SingleLinkedElmt<DATA>() {
                private DATA data;
                private SingleLinkedElmt<DATA> next;

                @SuppressWarnings("unchecked")
                @Override
                public boolean equals(Object obj) {
                    SingleLinkedElmt<DATA> that;
                    if (obj instanceof SingleLinkedElmt) {
                        that = (SingleLinkedElmt<DATA>) obj;
                        return Objects.equals(this.list_Data() , that.list_Data())
                            && Objects.equals(this.list_next() , that.list_next());
                    } else return false;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(this.list_Data() , this.list_next());
                }

                @Override
                public DATA list_Data() {
                    return this.data;
                }

                @Override
                public SingleLinkedElmt<DATA> list_next() {
                    return this.next;
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
            result.setData(data);

            return result;
        }
    }

    public static <DATA> SingleLinked<DATA> DEFAULT_SINGLE_LINKED() {
        return new MySingleLinked<DATA>();
    }

    @Override
    public void list_ins_next(SingleLinkedElmt<D> element , final D data);

    @Override
    public D list_rem_next(SingleLinkedElmt<D> element);
}
