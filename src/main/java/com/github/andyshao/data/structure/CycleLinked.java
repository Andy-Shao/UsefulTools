package com.github.andyshao.data.structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 
 * Title: Cycle Linked interface<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 9, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface CycleLinked<D> extends Linked<D , CycleLinkedElmt<D>> , SingleLinkedOperation<D , CycleLinkedElmt<D>> {

    //    public interface CycleLinkedElmt<DATA> extends Linked.LinkedElmt<DATA , CycleLinked.CycleLinkedElmt<DATA>> {
    //        public static <DAT> CycleLinked.CycleLinkedElmt<DAT> DEFAULT_CYCLE_ELMT(DAT data) {
    //            CycleLinked.CycleLinkedElmt<DAT> result = new CycleLinked.CycleLinkedElmt<DAT>() {
    //                private DAT data;
    //                private CycleLinked.CycleLinkedElmt<DAT> next;
    //
    //                @SuppressWarnings("unchecked")
    //                @Override
    //                public boolean equals(Object obj) {
    //                    CycleLinked.CycleLinkedElmt<DAT> that;
    //                    if (obj instanceof CycleLinked.CycleLinkedElmt) {
    //                        that = (CycleLinkedElmt<DAT>) obj;
    //                        if (this.next == this) return Objects.equals(this.data , that.list_Data());
    //                        else return Objects.equals(this.data , that.list_Data())
    //                            && Objects.equals(this.next , that.list_next());
    //                    } else return false;
    //                }
    //
    //                @Override
    //                public int hashCode() {
    //                    if (this.next == this) return this.data.hashCode();
    //                    else return Objects.hash(this.data , this.next);
    //                }
    //
    //                @Override
    //                public DAT list_Data() {
    //                    return this.data;
    //                }
    //
    //                @Override
    //                public CycleLinkedElmt<DAT> list_next() {
    //                    return this.next;
    //                }
    //
    //                @Override
    //                public void setData(DAT data) {
    //                    this.data = data;
    //                }
    //
    //                @Override
    //                public void setNext(CycleLinkedElmt<DAT> next) {
    //                    this.next = next;
    //                }
    //            };
    //            result.setData(data);
    //
    //            return result;
    //        }
    //    }

    public static <DATA> CycleLinked<DATA> DEFAULT_CYCLE_LINKED() {
        return new CycleLinked<DATA>() {
            private long actionAccount = 0;
            private CycleLinkedElmt<DATA> head;
            private int size;

            @Override
            public void clean() {
                do
                    if (this.size == 0) return;
                    else if (this.size == 1) {
                        this.head.free();
                        this.head = null;
                        return;
                    } else this.list_rem_next(this.head);
                while (this.size != 0);
            }

            @Override
            public CycleLinkedElmt<DATA> head() {
                return this.head;
            }

            @Override
            public Iterator<DATA> iterator() {
                return new Iterator<DATA>() {
                    private volatile boolean asked = false;
                    private volatile CycleLinkedElmt<DATA> index;
                    private final long linkedActionAccount = actionAccount;

                    @Override
                    public boolean hasNext() {
                        if (this.linkedActionAccount != actionAccount) throw new ConcurrentModificationException();
                        else if (this.asked && this.index.equals(head)) return false;
                        else if (!this.asked) this.asked = true;
                        return this.index != null;
                    }

                    @Override
                    public DATA next() {
                        CycleLinkedElmt<DATA> result = this.index;
                        this.index = this.index.next();
                        return result.list_Data();
                    }
                };
            }

            @Override
            public void list_ins_next(CycleLinkedElmt<DATA> element , final DATA data) {
                CycleLinkedElmt<DATA> new_element = CycleLinkedElmt.<DATA> DEFAULT_ELMT(data);

                if (this.size == 0) {
                    //Handle insertion when the list is empty.
                    new_element.setNext(new_element);
                    this.head = new_element;
                } else if (element == null) {
                    new_element.setNext(this.head);
                    this.head = new_element;
                } else {
                    //Handle insertion when the list is not empty.
                    new_element.setNext(element.next());
                    element.setNext(new_element);
                }

                //Adjust the size of the list to account for the inserted element.
                this.size++;
                this.actionAccount++;
            }

            @Override
            public DATA list_rem_next(CycleLinkedElmt<DATA> element) {
                CycleLinkedElmt<DATA> old_element = CycleLinkedElmt.<DATA> DEFAULT_ELMT(null);
                DATA data = null;

                //Do not allow removal from an empty list.
                if (this.size == 0) throw new LinkedOperationException("Do not allow removal from an empty list.");
                if (element == null) element = this.head;

                //Remove the element from the list.
                data = element.next().list_Data();
                if (element.next() == element) {
                    //Handle removing the last element.
                    old_element = element.next();
                    this.head = null;
                } else {
                    //Handle removing other than the last element.
                    old_element = element.next();
                    element.setNext(element.next().next());
                    if (old_element == this.head) this.head = old_element.next();
                }

                //Free the storage allocated by the abstract datatype.
                old_element.free();

                //Adjust the size of the list to account for the removed element.
                this.size--;
                this.actionAccount++;

                return data;
            }

            @Override
            public int size() {
                return this.size;
            }

            @Override
            public CycleLinkedElmt<DATA> tail() {
                return this.head;
            }

        };
    }

    @Override
    public void list_ins_next(CycleLinkedElmt<D> element , final D data);

    @Override
    public D list_rem_next(CycleLinkedElmt<D> element);
}
