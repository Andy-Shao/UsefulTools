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
 * @param <D>
 */
public interface LinkedSet<D> extends Linked<D , SingleLinked.SingleLinkedElmt<D>> , Set<D> {

    public static <DATA> LinkedSet<DATA> DEFAULT_SET(final SingleLinked<DATA> linked) {
        return new LinkedSet<DATA>() {

            @Override
            public boolean clean() {
                return linked.clean();
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
                for (SingleLinked.SingleLinkedElmt<DATA> member = this.list_head() ; member != null ; member =
                    member.list_next())
                    result += member.hashCode();
                return result;
            }

            @Override
            public SingleLinked.SingleLinkedElmt<DATA> list_head() {
                return linked.list_head();
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
                SingleLinked.SingleLinkedElmt<DATA> member;

                //Determine if the data is a member of the set.
                for (member = this.list_head() ; member != null ; member = member.list_next())
                    if (member.list_Data().equals(data)) return true;

                return false;
            }

            @Override
            public boolean set_is_subset(LinkedSet<DATA> set1) {
                SingleLinked.SingleLinkedElmt<DATA> member;

                //Do a quick test to rule out some cases.
                if (this.size() > set1.size()) return false;

                //Determine if set1 is a subset of set2.
                for (member = this.list_head() ; member != null ; member = member.list_next())
                    if (!set1.set_is_member(member.list_Data())) return false;
                return true;
            }

            @Override
            public DATA set_remove(DATA data) {
                SingleLinked.SingleLinkedElmt<DATA> member , prev;

                //Find the member to remove.
                prev = null;

                for (member = linked.list_head() ; member != null ; member = member.list_next()) {
                    if (member.list_Data().equals(data)) break;
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
            public SingleLinked.SingleLinkedElmt<DATA> tail() {
                return linked.tail();
            }

            @Override
            public Iterator<DATA> iterator() {
                return linked.iterator();
            }
        };
    }

    @Override
    public default Set<D> set_difference(final Set<D> result , final LinkedSet<D> set1 , final Set<D> set2) {
        SingleLinked.SingleLinkedElmt<D> member;
        D data;

        //Insert the members from set1 not in set2.
        for (member = set1.list_head() ; member != null ; member = member.list_next())
            if (!set2.set_is_member(member.list_Data())) {
                data = member.list_Data();
                result.set_insert(data);
            }

        return result;
    }

    @Override
    public default Set<D> set_intersection(final Set<D> result , final LinkedSet<D> set1 , final Set<D> set2) {
        SingleLinked.SingleLinkedElmt<D> member;
        D data;

        //Insert the member present in both sets.
        for (member = set1.list_head() ; member != null ; member = member.list_next())
            if (set2.set_is_member(member.list_Data())) {
                data = member.list_Data();
                result.set_insert(data);
            }

        return result;
    }

    @Override
    public default Set<D> set_union(final Set<D> result , final LinkedSet<D> set1 , final LinkedSet<D> set2) {
        SingleLinked.SingleLinkedElmt<D> memeber;
        D data;

        //Insert the members of the first set.
        for (memeber = set1.list_head() ; memeber != null ; memeber = memeber.list_next()) {
            data = memeber.list_Data();
            result.set_insert(data);
        }

        //Insert the members of the second set.
        for (memeber = set2.list_head() ; memeber != null ; memeber = memeber.list_next()) {
            data = memeber.list_Data();
            result.set_insert(data);
        }
        return result;
    }
}
