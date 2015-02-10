package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedTest {

    private String[] data = new String[] {
        "Andy" , "Shao"
    };
    private DoubleLinked<String> doubleLinked;

    @Before
    public void before() {
        this.doubleLinked = DoubleLinked.<String> DEFAULT_DOUBLE_LINKED();
    }

    @Test
    public void testInsert() {
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(0));

        for (int i = 0 ; i < this.data.length ; i++)
            this.doubleLinked.dlist_ins_next(this.doubleLinked.list_head() , this.data[i]);

        Assert.assertThat(this.doubleLinked.size() , Matchers.is(2));
    }

    @Test
    public void testRemove() {
        this.testInsert();
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(2));

        this.doubleLinked.clean();
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(0));

        this.testInsert();
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(2));

        do
            this.doubleLinked.dlist_remove(this.doubleLinked.list_head());
        while (this.doubleLinked.size() != 0);
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(0));
    }

    @Test
    public void testSize() {
        Assert.assertThat(this.doubleLinked.size() , Matchers.is(0));
    }
}
