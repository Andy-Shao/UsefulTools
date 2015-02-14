package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.data.structure.Bitree.MyBitree;
import com.github.andyshao.lang.Cleanable;

public interface Bistree<DATA> extends Cleanable {
    public static final int AVL_LFT_HEAVY = 1;
    public static final int AVL_BALANCED = 0;
    public static final int AVL_RGT_HEAVY = -1;

    public interface AvlNode<D> {
        public D data();

        public void date(D data);

        public int hidden();

        public void hidden(int hidden);

        public int factor();

        public void factor(int factor);
    }

    public int size();

    /**
     * Insert a data
     * 
     * @param node which node i want to insert
     * @param data the date
     * @return return the balance. if the balance over absolute number 2 then it should take
     *         a rotation.
     */
    public int insert(final BitreeNode<AvlNode<DATA>> node , final DATA data);

    public AvlNode<DATA> bistree_remove(final DATA data);

    public void bistree_lookup(final DATA data);

    public void destroy_left(BitreeNode<AvlNode<DATA>> node);

    public void destroy_right(BitreeNode<AvlNode<DATA>> node);

    @FunctionalInterface
    public interface AvlNodeFactory<D , T extends AvlNode<D>> {
        public T build();
    }

    public class MyBistree<D> extends MyBitree<AvlNode<D>> implements Bistree<D> {
        private final AvlNodeFactory<D , AvlNode<D>> avlNodeFactory;

        public MyBistree(
            TreeNodeFactory<Bistree.AvlNode<D> , Bitree.BitreeNode<Bistree.AvlNode<D>>> treeNodeFactory ,
            AvlNodeFactory<D , AvlNode<D>> avlNodeFactory) {
            super(treeNodeFactory);
            this.avlNodeFactory = avlNodeFactory;
        }

        @Override
        public int insert(BitreeNode<AvlNode<D>> node , D data) {
            AvlNode<D> avl_data;
            int cmpval , balance;

            //Insert the data into the tree.
            if (Bitree.bitree_is_eob(node)) {
                //Handle insertion into an empty tree.
                avl_data = this.avlNodeFactory.build();

                avl_data.factor(AVL_BALANCED);
                avl_data.hidden(0);
                avl_data.date(data);

                this.bitree_ins_left(node , avl_data);
                return 1;
            } else {
                //Handle insertion into a tree that is not empty.
                cmpval = this.comparator.compare(data , node.data().data());

                if (cmpval < 0) {
                    //Move to the left.
                    if (Bitree.bitree_is_eob(node.left())) {
                        avl_data = this.avlNodeFactory.build();

                        avl_data.factor(AVL_BALANCED);
                        avl_data.hidden(0);
                        avl_data.date(data);

                        this.bitree_ins_left(node , avl_data);
                        
                        return 0;
                    } else {
                        balance = insert(node.left(), data);
                    }
                    
                    //Ensure that the tree remains balanced.
                    if(balance != 0){
                        
                    }
                }
            }

            return 0;
        }

        @Override
        public Bistree.AvlNode<D> bistree_remove(D data) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void bistree_lookup(D data) {
            // TODO Auto-generated method stub

        }

        @Override
        public void destroy_left(Bitree.BitreeNode<Bistree.AvlNode<D>> node) {
            this.bitree_rem_left(node);
        }

        @Override
        public void destroy_right(Bitree.BitreeNode<Bistree.AvlNode<D>> node) {
            this.bitree_rem_right(node);
        }

    }

    public static <D> BitreeNode<AvlNode<D>> rotate_left(BitreeNode<AvlNode<D>> node) {
        BitreeNode<AvlNode<D>> left , grandchild;

        left = node.left();
        if (left.data().factor() == AVL_LFT_HEAVY) {
            //Perform an LL rotation.
            node.left(left.right());
            left.right(node);
            node.data().factor(AVL_BALANCED);
            left.data().factor(AVL_BALANCED);
            node = left;
        } else {
            //Perform an LR rotation.
            grandchild = left.right();
            left.right(grandchild.left());
            grandchild.left(left);
            node.left(grandchild.right());
            grandchild.right(node);

            switch (grandchild.data().factor()) {
            case AVL_LFT_HEAVY:
                node.data().factor(AVL_RGT_HEAVY);
                left.data().factor(AVL_BALANCED);
                break;
            case AVL_BALANCED:
                node.data().factor(AVL_BALANCED);
                left.data().factor(AVL_BALANCED);
                break;
            case AVL_RGT_HEAVY:
                node.data().factor(AVL_BALANCED);
                left.data().factor(AVL_LFT_HEAVY);
            }

            grandchild.data().factor(AVL_BALANCED);
            node = grandchild;
        }

        return node;
    }

    public static <D> BitreeNode<AvlNode<D>> rotate_right(BitreeNode<AvlNode<D>> node) {
        BitreeNode<AvlNode<D>> right , grandchild;
        right = node.right();

        if (right.data().factor() == AVL_RGT_HEAVY) {
            //Perform an RR rotation
            node.right(right.left());
            right.left(node);
            node.data().factor(AVL_BALANCED);
            right.data().factor(AVL_BALANCED);
            node = right;
        } else {
            //Perform an RL rotation
            grandchild = right.left();
            right.left(grandchild.right());
            grandchild.right(right);
            node.right(grandchild.left());
            grandchild.left(node);

            switch (grandchild.data().factor()) {
            case AVL_LFT_HEAVY:
                node.data().factor(AVL_BALANCED);
                right.data().factor(AVL_RGT_HEAVY);
                break;
            case AVL_BALANCED:
                node.data().factor(AVL_BALANCED);
                right.data().factor(AVL_BALANCED);
                break;
            case AVL_RGT_HEAVY:
                node.data().factor(AVL_LFT_HEAVY);
                right.data().factor(AVL_BALANCED);
                break;
            }
            grandchild.data().factor(AVL_BALANCED);
            node = grandchild;
        }

        return node;
    }
}
