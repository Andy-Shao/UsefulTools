package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.data.structure.Bitree.BitreeNode;

public class BitTreeTest {

    private volatile Bitree<String , BitreeNode<String>> bitree;

    @Before
    public void before() {
        this.bitree = Bitree.<String , BitreeNode<String>> DEFAULT_BIT_TREE(() -> {
            return BitreeNode.<String> DEFAULT_BITREE_NODE();
        });
    }

    @Test
    public void testSize() {
        Assert.assertThat(this.bitree.size() , Matchers.is(0));
    }

    @Test
    public void testInsertLeft() {
        Assert.assertThat(this.bitree.size() , Matchers.is(0));

        this.bitree.bitree_ins_left(null , "root");

        Assert.assertThat(this.bitree.size() , Matchers.is(1));
        Assert.assertThat(this.bitree.root().data() , Matchers.is("root"));

        this.bitree.bitree_ins_left(this.bitree.root() , "left");

        Assert.assertThat(this.bitree.size() , Matchers.is(2));
        Assert.assertThat(this.bitree.root().left().data() , Matchers.is("left"));
    }

    @Test
    public void testInsertRight() {
        Assert.assertThat(this.bitree.size() , Matchers.is(0));

        this.bitree.bitree_ins_right(null , "root");

        Assert.assertThat(this.bitree.size() , Matchers.is(1));
        Assert.assertThat(this.bitree.root().data() , Matchers.is("root"));

        this.bitree.bitree_ins_right(this.bitree.root() , "right");

        Assert.assertThat(this.bitree.size() , Matchers.is(2));
        Assert.assertThat(this.bitree.root().right().data() , Matchers.is("right"));
    }

    @Test
    public void testClean() {
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
    public void testMeger() {
        Bitree<String , BitreeNode<String>> left = Bitree.<String , BitreeNode<String>> DEFAULT_BIT_TREE(() -> {
            return BitreeNode.<String> DEFAULT_BITREE_NODE();
        });
        Bitree<String , BitreeNode<String>> right = Bitree.<String , BitreeNode<String>> DEFAULT_BIT_TREE(() -> {
            return BitreeNode.<String> DEFAULT_BITREE_NODE();
        });

        left.bitree_ins_left(null , "left");
        right.bitree_ins_left(null , "right");
        Bitree<String , BitreeNode<String>> bitree = Bitree.<String , BitreeNode<String>> BITREE_MERGE(() -> {
            return BitreeNode.<String>DEFAULT_BITREE_NODE();
        } , left , right , "root");

        Assert.assertThat(bitree.size() , Matchers.is(3));
        Assert.assertThat(bitree.root().data() , Matchers.is("root"));
        Assert.assertThat(bitree.root().left().data() , Matchers.is("left"));
        Assert.assertThat(bitree.root().right().data() , Matchers.is("right"));
    }
}