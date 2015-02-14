package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BitTreeTest {

    private volatile Bitree<String> bitree;
    
    @Before
    public void before(){
        this.bitree = Bitree.<String>DEFAULT_BIT_TREE();
    }
    
    @Test
    public void testSize(){
        Assert.assertThat(this.bitree.size() , Matchers.is(0));
    }
    
    @Test
    public void testInsertLeft(){
        Assert.assertThat(this.bitree.size() , Matchers.is(0));

        this.bitree.bitree_ins_left(null , "root");
        
        Assert.assertThat(this.bitree.size() , Matchers.is(1));
        Assert.assertThat(this.bitree.root().data() , Matchers.is("root"));
        
        this.bitree.bitree_ins_left(this.bitree.root() , "left");
        
        Assert.assertThat(this.bitree.size() , Matchers.is(2));
        Assert.assertThat(this.bitree.root().left().data() , Matchers.is("left"));
    }
    
    @Test
    public void testInsertRight(){
        Assert.assertThat(this.bitree.size() , Matchers.is(0));

        this.bitree.bitree_ins_right(null , "root");
        
        Assert.assertThat(this.bitree.size() , Matchers.is(1));
        Assert.assertThat(this.bitree.root().data(), Matchers.is("root"));
        
        this.bitree.bitree_ins_right(this.bitree.root() , "right");
        
        Assert.assertThat(this.bitree.size() , Matchers.is(2));
        Assert.assertThat(this.bitree.root().right().data() , Matchers.is("right"));
    }
    
    @Test
    public void testClean(){
        Assert.assertThat(this.bitree.size() , Matchers.is(0));
        
        this.bitree.bitree_ins_right(null , "root");
        this.bitree.bitree_ins_left(this.bitree.root() , "left");
        this.bitree.bitree_ins_right(this.bitree.root() , "right");
        
        Assert.assertThat(this.bitree.size() , Matchers.is(3));
        Assert.assertThat(this.bitree.root().data() , Matchers.is("root"));
        Assert.assertThat(this.bitree.root().left().data() , Matchers.is("left"));
        Assert.assertThat(this.bitree.root().right().data() , Matchers.is("right"));
        
        this.bitree.clean();
        
        Assert.assertThat(this.bitree.size() , Matchers.is(0));
    }
    
    @Test
    public void testMeger(){
        Bitree<String> left = Bitree.DEFAULT_BIT_TREE();
        Bitree<String> right = Bitree.DEFAULT_BIT_TREE();
        
        left.bitree_ins_left(null , "left");
        right.bitree_ins_left(null , "right");
        Bitree<String> bitree = Bitree.BITREE_MERGE(left , right , "root");

        Assert.assertThat(bitree.size() , Matchers.is(3));
        Assert.assertThat(bitree.root().data() , Matchers.is("root"));
        Assert.assertThat(bitree.root().left().data() , Matchers.is("left"));
        Assert.assertThat(bitree.root().right().data() , Matchers.is("right"));
    }
}
