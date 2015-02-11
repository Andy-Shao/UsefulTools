package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

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
public interface Set<D> extends Cleanable , Iterable<D> , Cloneable {

    public Object clone() throws CloneNotSupportedException;

    @SuppressWarnings("unchecked")
    public default Set<D> set_difference(final Set<D> result , Set<D>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (D data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<D> temp = (Set<D>) result.clone();
            for (Set<D> set : sets)
                temp = this.set_difference((Set<D>) result.clone() , temp , set);
            this.set_difference(result , temp);
            break;
        }
        return result;
    }

    public default Set<D> set_difference(final Set<D> result , Set<D> set1 , Set<D> set2) {
        //Insert the members from set1 not in set2.
        for (D data : set1)
            if (!set2.set_is_member(data)) result.set_insert(data);

        return result;
    }

    public abstract void set_insert(D data);

    @SuppressWarnings("unchecked")
    public default Set<D> set_intersection(final Set<D> result , Set<D>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (D data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<D> temp = (Set<D>) result.clone();
            for (Set<D> set : sets)
                temp = this.set_intersection((Set<D>) result.clone() , temp , set);
            this.set_intersection(result , temp);
            break;
        }
        return result;
    }

    public default Set<D> set_intersection(final Set<D> result , Set<D> set1 , Set<D> set2) {
        //Insert the member present in both sets.
        for (D data : set1)
            if (set2.set_is_member(data)) result.set_insert(data);

        return result;
    }

    public abstract boolean set_is_member(D data);

    public abstract boolean set_is_subset(LinkedSet<D> set1);

    public abstract void set_remove(D data);

    @SuppressWarnings("unchecked")
    public default Set<D> set_union(Set<D> result , Set<D>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (D data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<D> temp = (Set<D>) result.clone();
            for (Set<D> set : sets)
                temp = this.set_union(result , temp , set);
            this.set_union(result , temp);
            break;
        }

        return result;
    }

    public default Set<D> set_union(Set<D> result , Set<D> set1 , Set<D> set2) {
        //Insert the members of the first set.
        for (D data : set1)
            result.set_insert(data);

        //Insert the members of the second set.
        for (D data : set2)
            result.set_insert(data);
        
        return result;
    }

    public abstract int size();
}