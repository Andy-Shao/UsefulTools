package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;

public interface Bistree<DATA> {
    public static final int AVL_LFT_HEAVY = 1;
    public static final int AVL_BALANCED = 0;
    public static final int AVL_RGT_HEAVY = -1;
    
    public interface AvlNode<D>{
        public D date();
        public void date(D data);
        public int hidden();
        public void hidden(int hidden);
        public int factor();
        public void factor(int factor);
    }
    
    public int size();
    public void bistree_insert(final DATA data);
    public void bistree_remove(final DATA data);
    public void bistree_lookup(final DATA data);
    
    public static <D> BitreeNode<AvlNode<D>> rotate_left(BitreeNode<AvlNode<D>> node){
        BitreeNode<AvlNode<D>> left, grandchild;
        
        left = node.left();
        if(left.data().factor() == AVL_LFT_HEAVY){
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
            
            switch(grandchild.data().factor()){
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
}
