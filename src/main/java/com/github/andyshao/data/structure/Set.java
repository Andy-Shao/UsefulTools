package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

public interface Set<D, T extends Linked.LinkedElmt<D , T>> extends Cleanable, Linked<D , T>{

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Set<DATA, E> DEFAULT_SET(final T linked) {
        return new Set<DATA, E>() {

            @Override
            public boolean clean() {
                return linked.clean();
            }

            @Override
            public void set_insert(DATA data) {
                //Do not allow the insertion of duplicates.
                if (this.set_is_member(data)) return;

                //Insert the data.
                linked.list_ins_next(linked.tail() , data);
            }

            @Override
            public boolean set_is_member(DATA data) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean set_is_subset(Set<DATA, E> set1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public DATA set_remove(DATA data) {
                E member , prev;

                //Find the member to remove.
                prev = null;

                for (member = linked.head() ; member != null ; member = member.getNext()) {
                    if (member.getData().equals(data)) break;
                    prev = member;
                }

                //Return if the member was not found.
                if (member == null) return null;

                //Remove the member
                return linked.list_rem_next(prev);
            }

            @Override
            public int size() {
                return linked.size();
            }

            @Override
            public Set<DATA, E> set_union(Set<DATA, E> result , Set<DATA, E> set1 , Set<DATA , E> set2) {
                E memeber;
                DATA data;
                
                result.clean();
                
                //Insert the members of the first set.
                for(memeber = set1.head(); memeber != null; memeber = memeber.getNext()){
                    data = memeber.getData();
                    result.set_insert(data);
                }
                
                //Insert the members of the second set.
                for(memeber = set2.head(); memeber != null; memeber = memeber.getNext()){
                    data = memeber.getData();
                    result.set_insert(data);
                }
                return result;
            }

            @Override
            public E head() {
                return linked.head();
            }

            @Override
            public E tail() {
                return linked.tail();
            }

            @Override
            public Set<DATA , E> set_difference(Set<DATA , E> result , Set<DATA , E> set1 , Set<DATA , E> set2) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Set<DATA , E> set_intersection(Set<DATA , E> result , Set<DATA , E> set1 , Set<DATA , E> set2) {
                // TODO Auto-generated method stub
                return null;
            }

        };
    }

    public Set<D, T> set_difference(final Set<D, T> result, final Set<D, T> set1 , final Set<D, T> set2);

    public void set_insert(final D data);

    public Set<D, T> set_intersection(final Set<D, T> result, final Set<D, T> set1 , final Set<D, T> set2);

    public boolean set_is_member(final D data);

    public boolean set_is_subset(final Set<D, T> set1);

    public D set_remove(final D data);

    public Set<D, T> set_union(final Set<D, T> result, final Set<D, T> set1 , final Set<D, T> set2);
}
