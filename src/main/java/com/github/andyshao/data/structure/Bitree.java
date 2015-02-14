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
public interface Bitree<D> extends Cleanable, Tree<D, Bitree.BitreeNode<D>> {

    public interface BitreeNode<DATA> extends Tree.TreeNode<DATA, BitreeNode<DATA>>{
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

        public DATA data();

        public void data(DATA data);

        public BitreeNode<DATA> left();

        public void left(BitreeNode<DATA> left);

        public BitreeNode<DATA> right();

        public void right(BitreeNode<DATA> right);
    }

    public class MyBitree<DATA> implements Bitree<DATA> {
        private Bitree.BitreeNode<DATA> root;
        private int size;

        public MyBitree() {
        }

        public MyBitree(Tree<DATA, BitreeNode<DATA>> left , Tree<DATA, BitreeNode<DATA>> right , DATA data) {
            this.bitree_ins_left(null , data);

            //Merge the two binary trees into a single binary tree.
            this.root.left(left.root());
            this.root.right(right.root());

            //Adjust the size of the new binary tree.
            this.size = this.size + left.size() + right.size();
        }

        @Override
        public Bitree.BitreeNode<DATA> bitree_ins_left(Bitree.BitreeNode<DATA> node , DATA data)
            throws TreeOperationException {
            BitreeNode<DATA> new_node;

            //Determine where to insert the node.
            if (node == null) {
                //Allow insertion at the root only in an empty tree.
                if (this.size > 0) throw new TreeOperationException("node is null & the size of tree bigger than 0.");
            } else {
                //Normally allow insertion only at the end of a branch.
                if (node.left() != null) throw new TreeChildNodeNotEmptyException("the left of node's is not empty");
            }

            //Allocate storage for the node.
            new_node = BitreeNode.DEFAULT_BIT_TREE_NODE();

            //Insert the node into the tree.
            new_node.data(data);
            new_node.left(null);
            new_node.right(null);
            if (node == null) this.root = new_node;
            else node.left(new_node);

            //Adjust the size of the tree to account for the inserted node.
            this.size++;

            return new_node;
        }

        @Override
        public Bitree.BitreeNode<DATA> bitree_ins_right(Bitree.BitreeNode<DATA> node , DATA data) {
            Bitree.BitreeNode<DATA> new_node;

            if (node == null) {
                if (this.size > 0) throw new TreeOperationException("node is null & the size of tree bigger than 0.");
            } else {
                if (node.right() != null) throw new TreeChildNodeNotEmptyException(
                    "the right child of node's is not null");
            }

            //Allocate storage for the node.
            new_node = Bitree.BitreeNode.<DATA> DEFAULT_BIT_TREE_NODE();
            new_node.data(data);
            new_node.left(null);
            new_node.right(null);
            if (node == null) this.root = new_node;
            else node.right(new_node);
            
            //Adjust the size of the tree to account for the inserted node.
            this.size++;

            return new_node;
        }

        @Override
        public void bitree_rem_left(Bitree.BitreeNode<DATA> node) {
            Bitree.BitreeNode<DATA> position;

            //Do not allow removal from an empty tree.
            if (this.size == 0) throw new TreeIsEmptyException();

            //Determine where to remove nodes.
            if (node == null) position = this.root;
            else position = node.left();

            if (position != null) {
                this.bitree_rem_left(position);
                this.bitree_rem_right(position);
                if (node == null) this.root = null;
                else node.left(null);
            }

            //Adjust the size of the tree to account for the removed node.
            this.size--;
        }

        @Override
        public void bitree_rem_right(Bitree.BitreeNode<DATA> node) {
            Bitree.BitreeNode<DATA> position;

            //Do not allow removal from an empty tree.
            if (this.size == 0) throw new TreeIsEmptyException();

            //Determine where to remove nodes.
            if (node == null) position = this.root;
            else position = node.right();

            //Remove the nodes.
            if (position != null) {
                this.bitree_rem_left(position.left());
                this.bitree_rem_right(position.right());
                if (node == null) this.root = null;
                else node.right(null);
            }

            //Adjust the size of the tree to account for the removed node.
            this.size--;
        }

        @Override
        public void clean() {
            this.root = null;
            this.size = 0;
        }

        @Override
        public Bitree.BitreeNode<DATA> root() {
            return this.root;
        }

        @Override
        public int size() {
            return this.size;
        }

    }

    public static <DATA> Bitree<DATA> BITREE_MERGE(Tree<DATA, BitreeNode<DATA>> left , Tree<DATA, BitreeNode<DATA>> right , DATA data) {
        return new Bitree.MyBitree<>(left , right , data);
    }

    public static <DATA> Bitree<DATA> DEFAULT_BIT_TREE() {
        return new Bitree.MyBitree<>();
    }

    /**
     * add a left for node<br>
     * if node is null, input a new data for root of tree's.
     * 
     * @param node bit tree node
     * @param data data
     * @return the left child of node's
     * @throws TreeChildNodeNotEmptyException if the left of node's is not
     *             empty.
     * @throws TreeOperationException others operation exception of the action's
     */
    public BitreeNode<D> bitree_ins_left(BitreeNode<D> node , D data) throws TreeOperationException;

    /**
     * add a right child for node<br>
     * if node is null, input a new data for root of tree's.
     * 
     * @param node bit tree node
     * @param data data
     * @return the right child of nod's
     * @throws TreeChildNodeNotEmptyException if the right child of node's is
     *             not empty.
     * @throws TreeOperationException other operation exception of the action's
     */
    public BitreeNode<D> bitree_ins_right(BitreeNode<D> node , D data) throws TreeOperationException;

    public static <DATA> boolean bitree_is_eob(BitreeNode<DATA> node) {
        return node == null;
    }

    public static <DATA> boolean bitree_is_leaf(BitreeNode<DATA> node) {
        return node.left() == null && node.right() == null;
    }

    /**
     * remove the left child of node's.
     * 
     * @param node bit tree node
     * @throws TreeIsEmptyException if the tree is empty
     * @throws TreeOperationException others exception of action'
     */
    public void bitree_rem_left(BitreeNode<D> node) throws TreeOperationException;

    /**
     * remove the right child of node's.
     * 
     * @param node bit tree node
     * @throws TreeIsEmptyException if the tree is empty
     * @throws TreeOperationException other exception of action'
     */
    public void bitree_rem_right(BitreeNode<D> node) throws TreeOperationException;

    @Override
    public default void clean() {
        //Remove all the nodes from the tree.
        this.bitree_rem_left(null);
    }
}
