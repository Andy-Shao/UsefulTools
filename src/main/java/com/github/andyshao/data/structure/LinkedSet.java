package com.github.andyshao.data.structure;

import java.util.Iterator;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 11, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface LinkedSet<D> extends Linked<D , CycleLinkedElmt<D>> , Set<D> {

    public static <DATA> LinkedSet<DATA> DEFAULT_SET(final SingleLinked<DATA> linked) {
        return new LinkedSet<DATA>() {
            @Override
            public void clean() {
                linked.clean();
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean equals(Object obj) {
                LinkedSet<DATA> that;
                if (obj instanceof LinkedSet) {
                    that = (LinkedSet<DATA>) obj;
                    if (this.size() != that.size()) return false;

                    //Sets of the same size are equal if they are subsets.
                    return this.set_is_subset(that);
                } else return false;
            }

            @Override
            public int hashCode() {
                int result = 0;
                for (CycleLinkedElmt<DATA> member = this.head() ; member != null ; member = member.next())
                    result += member.hashCode();
                return result;
            }

            @Override
            public CycleLinkedElmt<DATA> head() {
                return linked.head();
            }

            @Override
            public Iterator<DATA> iterator() {
                return linked.iterator();
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
                CycleLinkedElmt<DATA> member;

                //Determine if the data is a member of the set.
                for (member = this.head() ; member != null ; member = member.next())
                    if (member.list_Data().equals(data)) return true;

                return false;
            }

            @Override
            public boolean set_is_subset(Set<DATA> set1) {
                CycleLinkedElmt<DATA> member;

                //Do a quick test to rule out some cases.
                if (this.size() > set1.size()) return false;

                //Determine if set1 is a subset of set2.
                for (member = this.head() ; member != null ; member = member.next())
                    if (!set1.set_is_member(member.list_Data())) return false;
                return true;
            }

            @Override
            public void set_remove(DATA data) {
                CycleLinkedElmt<DATA> member , prev;

                //Find the member to remove.
                prev = null;

                for (member = linked.head() ; member != null ; member = member.next()) {
                    if (member.list_Data().equals(data)) break;
                    prev = member;
                }

                //Return if the member was not found.
                if (member == null) return;

                //Remove the member
                linked.list_rem_next(prev);
            }

            @Override
            public int size() {
                return linked.size();
            }

            @Override
            public CycleLinkedElmt<DATA> tail() {
                return linked.tail();
            }
        };
    }
}
