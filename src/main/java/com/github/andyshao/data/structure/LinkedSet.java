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
public interface LinkedSet<D> extends Linked<D , SingleLinked.SingleLinkedElmt<D>> , Set<D> {
    public class MyLinkedSet<DATA> implements LinkedSet<DATA>{
        private final SingleLinked<DATA> linked;
        
        public MyLinkedSet(SingleLinked<DATA> linked) {
            this.linked = linked;
        }
        
        public MyLinkedSet() {
            this.linked = SingleLinked.DEFAULT_SINGLE_LINKED();
        }
        
        @Override
        public void clean() {
            linked.clean();
        }
        
        @Override
        public Object clone() throws CloneNotSupportedException {
            return new LinkedSet.MyLinkedSet<>();
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
        public Iterator<DATA> iterator() {
            return linked.iterator();
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
        public void set_remove(DATA data) {
            SingleLinked.SingleLinkedElmt<DATA> member , prev;
            
            //Find the member to remove.
            prev = null;
            
            for (member = linked.list_head() ; member != null ; member = member.list_next()) {
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
        public SingleLinked.SingleLinkedElmt<DATA> tail() {
            return linked.tail();
        }
    }

    public static <DATA> LinkedSet<DATA> DEFAULT_SET(final SingleLinked<DATA> linked) {
        return new LinkedSet.MyLinkedSet<>(linked);
    }
}
