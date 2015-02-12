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
public interface Bitree<D> extends Cleanable {

    public interface BitreeNode<DATA> {
        public DATA data();

        public void data(DATA data);

        public BitreeNode<DATA> left();

        public void left(BitreeNode<DATA> left);

        public BitreeNode<DATA> right();

        public void right(BitreeNode<DATA> right);

        public static <DAT> BitreeNode<DAT> DEFAULT_BIT_TREE_NODE() {
            return new BitreeNode<DAT>() {
                private DAT data;
                private BitreeNode<DAT> left;
                private BitreeNode<DAT> right;

                @Override
                public DAT data() {
                    return this.data;
                }

                @Override
                public void data(DAT data) {
                    this.data = data;
                }

                @Override
                public BitreeNode<DAT> left() {
                    return this.left;
                }

                @Override
                public void left(BitreeNode<DAT> left) {
                    this.left = left;
                }

                @Override
                public BitreeNode<DAT> right() {
                    return this.right;
                }

                @Override
                public void right(BitreeNode<DAT> right) {
                    this.right = right;
                }
            };
        }
    }

    public int size();

    /**
     * add a left for node<br/>
     * if node is null, input a new data for root of tree's.
     * 
     * @param node bit tree node
     * @param data data
     * @throws TreeChildNodeNotEmptyException if the left of node's is not
     *             empty.
     * @throws TreeOperationException others operation exception of the action's
     */
    public void bitree_ins_left(BitreeNode<D> node , D data) throws TreeOperationException;

    public void bitree_ins_right(BitreeNode<D> node , D data) throws TreeOperationException;

    public D bitree_rem_left(BitreeNode<D> node) throws TreeOperationException;

    public D bitree_rem_right(BitreeNode<D> node) throws TreeOperationException;

    public Bitree<D> bitree_merge(Bitree<D> result , Bitree<D> right , Bitree<D> left , D data);

    public BitreeNode<D> bitree_root();

    public void bitree_root(BitreeNode<D> node);

    public default boolean bitree_is_eob(BitreeNode<D> node) {
        return node == null;
    }

    public default boolean bitree_is_leaf(BitreeNode<D> node) {
        return node.left() == null && node.right() == null;
    }

    @Override
    public default void clean() {
        //Remove all the nodes from the tree.
        this.bitree_rem_left(null);
    }

    public static <DATA> Bitree<DATA> DEFAULT_BIT_TREE() {
        return new Bitree<DATA>() {
            private int size;
            private BitreeNode<DATA> root;

            @Override
            public int size() {
                return this.size;
            }

            @SuppressWarnings("unused")
            @Override
            public void bitree_ins_left(Bitree.BitreeNode<DATA> node , DATA data) throws TreeOperationException {
                BitreeNode<DATA> new_node;

                //Determine where to insert the node.
                //Allow insertion at the root only in an empty tree.
                if (node == null && this.size() > 0) throw new TreeOperationException(
                    "node is null & the size of tree bigger than 0.");
                //Normally allow insertion only at the end of a branch.
                else if (node.left() != null) throw new TreeChildNodeNotEmptyException(
                    "the left of node's is not empty");

                //Allocate storage for the node.
                new_node = BitreeNode.DEFAULT_BIT_TREE_NODE();

                //Insert the node into the tree.
                new_node.data(data);
                new_node.left(null);
                new_node.right(null);
                if(node == null) this.bitree_root(new_node);
                else node.left(new_node);

                //Adjust the size of the tree to account for the inserted node.
                this.size++;
            }

            @Override
            public void bitree_ins_right(Bitree.BitreeNode<DATA> node , DATA data) {
                // TODO Auto-generated method stub
            }

            @Override
            public DATA bitree_rem_left(Bitree.BitreeNode<DATA> node) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public DATA bitree_rem_right(Bitree.BitreeNode<DATA> node) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Bitree<DATA> bitree_merge(
                Bitree<DATA> result , Bitree<DATA> right , Bitree<DATA> left , DATA data) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Bitree.BitreeNode<DATA> bitree_root() {
                return this.root;
            }

            @Override
            public void bitree_root(Bitree.BitreeNode<DATA> node) {
                this.root = node;
            }

        };
    }
}
