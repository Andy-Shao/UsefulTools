package com.github.andyshao.data.structure;

import java.util.HashSet;

import com.github.andyshao.data.structure.convert.SetConvert;
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
public interface Set<D> extends Cleanable , Iterable<D> {

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_difference(final Set<DATA> result , Set<DATA>... sets) {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (DATA data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<DATA> temp = new SetConvert<DATA>().convert(new HashSet<DATA>());
            for (Set<DATA> set : sets)
                temp = Set.set_difference(new SetConvert<DATA>().convert(new HashSet<DATA>()) , temp , set);
            Set.set_difference(result , temp);
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

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_intersection(final Set<DATA> result , Set<DATA>... sets) {
        switch (sets.length) {
        case 0:
            break;
        case 1:
            for (DATA data : sets[0])
                result.set_insert(data);
            break;

        default:
            Set<DATA> temp = new SetConvert<DATA>().convert(new HashSet<DATA>());
            for (Set<DATA> set : sets)
                temp = Set.set_intersection(new SetConvert<DATA>().convert(new HashSet<DATA>()) , temp , set);
            Set.set_intersection(result , temp);
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

    @SuppressWarnings("unchecked")
    public static <DATA> Set<DATA> set_union(Set<DATA> result , Set<DATA>... sets) {
        Set<DATA> temp = new SetConvert<DATA>().convert(new HashSet<DATA>());
        for (Set<DATA> set : sets)
            Set.set_union(result , temp , set);
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

    public abstract void set_insert(D data);

    public abstract boolean set_is_member(D data);

    public abstract boolean set_is_subset(Set<D> set1);

    public abstract void set_remove(D data);

    public abstract int size();
    
    public static <DATA> Set<DATA> DEFALUT_SET(){
        return new SetConvert<DATA>().convert(new HashSet<DATA>());
    }
}