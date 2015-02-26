package com.github.andyshao.test.data.structure;

import com.github.andyshao.lang.Cleanable;
import com.github.andyshao.test.data.structure.Bitree.BitreeNode;

public interface Tree<D> extends Cleanable {

    @Override
    public abstract void clean();

    public abstract BitreeNode<D> root();

    public abstract int size();

}