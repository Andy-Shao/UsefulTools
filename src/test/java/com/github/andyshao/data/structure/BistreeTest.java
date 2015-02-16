package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.util.ArrayTools;

public class BistreeTest {

    private volatile Bistree<Integer> bistree;
    private final Integer[] data = ArrayTools.pack_unpack(new int[] {
        4 , 5 , 9 , 0 , 1 , 6 , 7 , 8 , 2 , 3
    } , Integer[].class);

    @Before
    public void before() {
        this.bistree = Bistree.<Integer> DEFAULT_BISTREE(() -> {
            return Bitree.BitreeNode.DEFAULT_BITREE_NODE();
        } , () -> {
            return Bistree.AvlNode.DEFAULT_AVL_NODE();
        } , (int1 , int2) -> {
            return int1.compareTo(int2);
        });
    }

    @Test
    public void testInsert() {
        Assert.assertThat(this.bistree.size() , Matchers.is(0));

        for (Integer i : this.data)
            this.bistree.bistree_insert(i);

        Assert.assertThat(this.bistree.size() , Matchers.is(this.data.length));
    }
    
    @Test
    public void testRemove(){
        testInsert();
        
        this.bistree.bistree_remove(this.data[0]);
        
        Assert.assertThat(this.bistree.size() , Matchers.is(this.data.length));
        Assert.assertThat(this.bistree.bistree_lookup(this.data[0]) == null , Matchers.is(true));
    }
    
    @Test
    public void testLookup(){
        testInsert();
        
        Assert.assertThat(this.bistree.bistree_lookup(this.data[0]).data().data() , Matchers.is(this.data[0]));
    }
}
