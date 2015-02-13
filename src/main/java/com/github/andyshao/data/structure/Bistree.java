package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title: 二叉搜索树<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 13, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface Bistree<D> extends Bitree<D>, Cleanable{
    public static final int AVL_LFT_HEAVY = 1;
    public static final int AVL_BALANCED = 0;
    public static final int AVL_RGT_HEVAY = -1;
    
    public interface AvlNode<DATA> extends Bitree.BitreeNode<DATA>{
        @Override
        public DATA data();
        @Override
        public void data(DATA data);
        public int hidden();
        public void hidden(int hidden);
        public int factor();
        public void factor(int factor);
    }
    
    public void bistree_insert(final D data);
    public void bistree_remove(final D data);
    public AvlNode<D> bistree_lookup(final D data);
    @Override
    public int size();
}
