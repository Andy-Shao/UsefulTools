package com.github.andyshao.data.structure.convert;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.andyshao.convert.Convert;
import com.github.andyshao.data.structure.LinkedSet;

public class SetConvert<D> implements Convert<Set<D> , com.github.andyshao.data.structure.Set<D>> {

    private static class MySet<D> implements com.github.andyshao.data.structure.Set<D> {
        private final Set<D> set;

        public MySet() {
            this(new HashSet<D>());
        }

        public MySet(Set<D> set) {
            this.set = set;
        }

        @Override
        public boolean clean() {
            this.set.clear();
            return true;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return new MySet<D>();
        }

        @Override
        public Iterator<D> iterator() {
            return this.set.iterator();
        }

        @Override
        public void set_insert(D data) {
            this.set.add(data);
        }

        @Override
        public boolean set_is_member(D data) {
            return this.set.contains(data);
        }

        @Override
        public boolean set_is_subset(LinkedSet<D> set1) {
            for (D data : this.set)
                if (!set1.set_is_member(data)) return false;

            return true;
        }

        @Override
        public void set_remove(D data) {
            this.set.remove(data);
        }

        @Override
        public int size() {
            return this.set.size();
        }

    }

    @Override
    public com.github.andyshao.data.structure.Set<D> convert(final Set<D> in) {
        return new MySet<D>(in);
    }
}
