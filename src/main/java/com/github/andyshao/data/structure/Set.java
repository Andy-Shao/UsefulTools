package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

public interface Set<D> extends Cleanable {

    public static <DATA> Set<DATA> DEFAULT_SET() {
        //TODO: create a default set.
        return null;
    }

    public default Set<D> difference(final @SuppressWarnings("unchecked") Set<D>... sets) {
        Set<D> set = Set.<D> DEFAULT_SET();
        for (int i = 0 ; i < sets.length ; i++)
            set = this.difference(set , sets[i]);
        return set;
    }

    public Set<D> difference(final Set<D> set1 , final Set<D> set2);

    public void insert(final D data);

    public default Set<D> intersection(final @SuppressWarnings("unchecked") Set<D>... sets) {
        Set<D> set = Set.<D> DEFAULT_SET();
        for (int i = 0 ; i < sets.length ; i++)
            set = this.intersection(set , sets[i]);
        return set;
    }

    public Set<D> intersection(final Set<D> set1 , final Set<D> set2);

    public boolean isMember(final D data);

    public boolean isSubset(final Set<D> set1);

    public D remove(final D data);

    public int size();

    public default Set<D> union(final @SuppressWarnings("unchecked") Set<D>... sets) {
        Set<D> set = Set.<D> DEFAULT_SET();
        for (int i = 0 ; i < sets.length ; i++)
            set = this.union(set , sets[i]);
        return set;
    }

    public Set<D> union(final Set<D> set1 , final Set<D> set2);
}
