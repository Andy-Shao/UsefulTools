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
public interface BitTree<D> extends Cleanable {

    public interface BitTreeNode<DATA> {
        public DATA data();

        public void data(DATA data);

        public BitTreeNode<DATA> left();

        public void left(BitTreeNode<DATA> left);

        public BitTreeNode<DATA> right();

        public void right(BitTreeNode<DATA> right);
    }

    public int size();

    public D bitree_ins_left(BitTreeNode<D> node , D data);

    public D bitree_ins_right(BitTreeNode<D> node , D data);

    public D bitree_rem_left(BitTreeNode<D> node);

    public D bitree_rem_right(BitTreeNode<D> node);

    public BitTree<D> bitree_merge(BitTree<D> result , BitTree<D> right , BitTree<D> left , D data);

    public BitTreeNode<D> bitree_root();

    public default boolean bitree_is_eob(BitTreeNode<D> node) {
        return node == null;
    }

    public default boolean bitree_is_leaf(BitTreeNode<D> node) {
        return node.left() == null && node.right() == null;
    }
    
    @Override
    public default void clean(){
    }
}
