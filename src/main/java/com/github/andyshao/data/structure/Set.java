package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 11, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D>
 */
public interface Set<D> extends Cleanable, Iterable<D>{

    public abstract Set<D> set_difference(Set<D> result , LinkedSet<D> set1 , Set<D> set2);

    public abstract void set_insert(D data);

    public abstract Set<D> set_intersection(Set<D> result , LinkedSet<D> set1 , Set<D> set2);

    public abstract boolean set_is_member(D data);

    public abstract boolean set_is_subset(LinkedSet<D> set1);

    public abstract D set_remove(D data);

    public abstract Set<D> set_union(Set<D> result , LinkedSet<D> set1 , LinkedSet<D> set2);

    public abstract int size();

}