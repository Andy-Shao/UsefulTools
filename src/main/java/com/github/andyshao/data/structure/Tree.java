package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.lang.Cleanable;

public interface Tree<D, N extends Tree.TreeNode<D , N>> extends Cleanable{

    @Override
    public abstract void clean();

    public abstract N root();

    public abstract int size();

    public interface TreeNode<DATA, N extends TreeNode<DATA , N>> {
        public DATA data();

        public void data(DATA data);

        public BitreeNode<DATA> left();

        public void left(N left);

        public N right();

        public void right(N right);
    }
}