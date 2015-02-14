package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title: 二叉搜索树<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 13, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface Bistree<D> extends Tree<D , Bistree.AvlNode<D>> , Cleanable {
    public interface AvlNode<DATA> extends Tree.TreeNode<DATA , AvlNode<DATA>> {
        public static <DAT> AvlNode<DAT> DEFAULT_AVLNODE() {
            return new AvlNode<DAT>() {
                DAT data;
                int factor;
                int hidden;
                AvlNode<DAT> left;
                AvlNode<DAT> right;

                @Override
                public DAT data() {
                    return this.data;
                }

                @Override
                public void data(DAT data) {
                    this.data = data;
                }

                @Override
                public int factor() {
                    return this.factor;
                }

                @Override
                public void factor(int factor) {
                    this.factor = factor;
                }

                @Override
                public int hidden() {
                    return this.hidden;
                }

                @Override
                public void hidden(int hidden) {
                    this.hidden = hidden;
                }

                @Override
                public AvlNode<DAT> left() {
                    return this.left;
                }

                @Override
                public void left(AvlNode<DAT> left) {
                    this.left = left;
                }

                @Override
                public AvlNode<DAT> right() {
                    return this.right;
                }

                @Override
                public void right(AvlNode<DAT> right) {
                    this.right = right;
                }
            };
        }

        @Override
        public DATA data();

        @Override
        public void data(DATA data);

        public int factor();

        public void factor(int factor);

        public int hidden();

        public void hidden(int hidden);
    }

    public static final int AVL_BALANCED = 0;
    public static final int AVL_LFT_HEAVY = 1;
    public static final int AVL_RGT_HEVAY = -1;
    
    public static <DATA> Bistree<DATA> DEFAULT_BISTREE() {
        return new Bistree<DATA>(){
            private AvlNode<DATA> root;
            private int size;

            @Override
            public void bistree_insert(AvlNode<DATA> node , DATA data , int balanced) {
                AvlNode<DATA> avl_data;
                int cmpval , retval;

                //Insert the data into the tree.
                if (Tree.bitree_is_eob(node)) {
                    //Handle insertion into an empty tree.
                    avl_data = AvlNode.DEFAULT_AVLNODE();
                    
                    avl_data.factor(AVL_BALANCED);
                    avl_data.hidden(0);
                    avl_data.data(data);
                    
                    
                }
            }

            @Override
            public Bistree.AvlNode<DATA> bistree_lookup(DATA data) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void bistree_remove(DATA data) {
                // TODO Auto-generated method stub

            }

            @Override
            public void clean() {
                this.size = 0;
                this.root = null;
            }

            @Override
            public void destroy_left(Bistree.AvlNode<DATA> node) {
                AvlNode<DATA> position;
                if (this.size() == 0) throw new TreeIsEmptyException("Do not allow destruction of an empty tree.");

                //Determine where to destroy nodes.
                if (node == null) position = this.root;
                else position = node.left();

                //Destroy the nodes.
                if (position != null) {
                    this.destroy_left(position);
                    this.destroy_right(position);
                    if (node == null) this.root = null;
                    else node.left(null);
                }

                //Adjust the size of the tree to account for the destroyed node.
                this.size--;
            }

            @Override
            public void destroy_right(Bistree.AvlNode<DATA> node) {
                AvlNode<DATA> position;

                if (this.size == 0) throw new TreeIsEmptyException("Do not allow destruction of an empty tree.");

                //Determine where to destroy nodes.
                if (node == null) position = this.root;
                else position = node.right();

                //Destroy the nodes.
                if (position != null) {
                    this.destroy_left(position);
                    this.destroy_left(position);
                    if (node == null) this.root = null;
                    else node.right(null);
                }

                //Adjust the size of the tree to account for the destroyed node.
            }

            @Override
            public Bistree.AvlNode<DATA> root() {
                return this.root;
            }

            @Override
            public int size() {
                return this.size;
            }

        };
    }

    public static <DATA> AvlNode<DATA> rotate_left(AvlNode<DATA> node) {
        AvlNode<DATA> left , grandchild , result = null;

        left = node.left();
        if (left.factor() == Bistree.AVL_LFT_HEAVY) {
            //Perform an LL rotation.
            node.left(left.right());
            left.right(node);
            node.factor(Bistree.AVL_BALANCED);
            left.factor(Bistree.AVL_BALANCED);
            result = left;
        } else {
            //Perform an LR rotation
            grandchild = left.right();
            left.right(grandchild.left());
            grandchild.left(left);
            node.left(grandchild.right());
            grandchild.right(node);

            switch (grandchild.factor()) {
            case AVL_LFT_HEAVY:
                node.factor(Bistree.AVL_RGT_HEVAY);
                left.factor(Bistree.AVL_BALANCED);
                break;
            case AVL_BALANCED:
                node.factor(Bistree.AVL_BALANCED);
                node.factor(Bistree.AVL_BALANCED);
                break;
            case AVL_RGT_HEVAY:
                node.factor(Bistree.AVL_BALANCED);
                left.factor(Bistree.AVL_LFT_HEAVY);
                break;
            }

            grandchild.factor(Bistree.AVL_BALANCED);
            result = grandchild;
        }
        return result;
    }

    public static <DATA> AvlNode<DATA> rotate_right(AvlNode<DATA> node) {
        AvlNode<DATA> right , grandchild , result = null;
        right = node.right();

        if (right.factor() == Bistree.AVL_RGT_HEVAY) {
            //Perform an RR rotation.
            node.right(right.left());
            right.left(node);
            node.factor(Bistree.AVL_BALANCED);
            right.factor(Bistree.AVL_BALANCED);
            result = right;
        } else {
            //Perform an RL rotation.
            grandchild = right.left();
            right.left(grandchild.right());
            grandchild.right(right);
            node.right(grandchild.left());
            grandchild.left(node);

            switch (grandchild.factor()) {
            case AVL_LFT_HEAVY:
                node.factor(Bistree.AVL_BALANCED);
                right.factor(Bistree.AVL_RGT_HEVAY);
                break;
            case AVL_BALANCED:
                node.factor(Bistree.AVL_BALANCED);
                right.factor(Bistree.AVL_BALANCED);
                break;
            case AVL_RGT_HEVAY:
                node.factor(Bistree.AVL_LFT_HEAVY);
                node.factor(Bistree.AVL_BALANCED);
                break;
            }
            grandchild.factor(Bistree.AVL_BALANCED);
            result = grandchild;
        }

        return result;
    }

    public void bistree_insert(AvlNode<D> node , final D data , int balanced);

    public AvlNode<D> bistree_lookup(final D data);

    public void bistree_remove(final D data);

    public void destroy_left(AvlNode<D> node);

    public void destroy_right(AvlNode<D> node);

    @Override
    public int size();
}
