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
 * @param <D> data
 */
public interface Set<D> extends Cleanable , Iterable<D> , Cloneable {

    public Object clone() throws CloneNotSupportedException;

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_difference(final Set<DATA> result , Set<DATA>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (DATA data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<DATA> temp = (Set<DATA>) result.clone();
            for (Set<DATA> set : sets)
                temp = set_difference((Set<DATA>) result.clone() , temp , set);
            set_difference(result , temp);
            break;
        }
        return result;
    }

    public static <DATA> Set<DATA> set_difference(final Set<DATA> result , Set<DATA> set1 , Set<DATA> set2) {
        //Insert the members from set1 not in set2.
        for (DATA data : set1)
            if (!set2.set_is_member(data)) result.set_insert(data);

        return result;
    }

    public abstract void set_insert(D data);

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_intersection(final Set<DATA> result , Set<DATA>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (DATA data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<DATA> temp = (Set<DATA>) result.clone();
            for (Set<DATA> set : sets)
                temp = set_intersection((Set<DATA>) result.clone() , temp , set);
            set_intersection(result , temp);
            break;
        }
        return result;
    }

    public static <DATA> Set<DATA> set_intersection(final Set<DATA> result , Set<DATA> set1 , Set<DATA> set2) {
        //Insert the member present in both sets.
        for (DATA data : set1)
            if (set2.set_is_member(data)) result.set_insert(data);

        return result;
    }

    public abstract boolean set_is_member(D data);

    public abstract boolean set_is_subset(LinkedSet<D> set1);

    public abstract void set_remove(D data);

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_union(Set<DATA> result , Set<DATA>... sets) throws CloneNotSupportedException {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (DATA data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<DATA> temp = (Set<DATA>) result.clone();
            for (Set<DATA> set : sets)
                temp = set_union(result , temp , set);
            set_union(result , temp);
            break;
        }

        return result;
    }

    public static <DATA> Set<DATA> set_union(Set<DATA> result , Set<DATA> set1 , Set<DATA> set2) {
        //Insert the members of the first set.
        for (DATA data : set1)
            result.set_insert(data);

        //Insert the members of the second set.
        for (DATA data : set2)
            result.set_insert(data);
        
        return result;
    }

    public abstract int size();
}