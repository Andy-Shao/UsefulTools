package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleLinkedTest {

    private final String[] data = new String[] {
        "Andy" , "Shao"
    };
    private volatile SingleLinked<String> singleLinked;

    @Before
    public void before() {
        this.singleLinked = new SingleLinked<>();
    }

    @Test
    public void testlist_ins_next() {
        Assert.assertThat(this.singleLinked.size() , Matchers.is(0));
        for (int i = 0 ; i < this.data.length ; i++) {
            this.singleLinked.list_ins_next(this.singleLinked.tail() , this.data[i]);
        }
        Assert.assertThat(this.singleLinked.size() , Matchers.is(this.data.length));
    }

    @Test
    public void testlist_rem_next() {
        this.testlist_ins_next();
        for (int i = 0 ; i < this.data.length ; i++) {
            this.singleLinked.list_rem_next(this.singleLinked.head());
        }
        Assert.assertThat(this.singleLinked.size() , Matchers.is(1));
    }

    @Test
    public void testSize() {
        Assert.assertThat(this.singleLinked.size() , Matchers.is(0));
    }
}
