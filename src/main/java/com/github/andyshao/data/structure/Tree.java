package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.lang.Cleanable;

public interface Tree<D> extends Cleanable{

    public abstract void clean();

    public abstract BitreeNode<D> root();

    public abstract int size();

}