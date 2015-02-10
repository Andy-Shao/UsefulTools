package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SetTest {
    private final String[] data = new String[] {
        "Andy" , "shao"
    };
    private volatile Set<String> set;

    @Before
    public void before() {
        this.set = Set.DEFAULT_SET(SingleLinked.DEFAULT_SINGLE_LINKED());
    }

    @Test
    public void testInsert() {
        Assert.assertThat(this.set.size() , Matchers.is(0));

        for (int i = 0 ; i < this.data.length ; i++)
            this.set.set_insert(this.data[i]);

        Assert.assertThat(this.set.size() , Matchers.is(this.data.length));
        
        for (int i = 0 ; i < this.data.length ; i++)
            this.set.set_insert(this.data[i]);
        
        Assert.assertThat(this.set.size() , Matchers.is(this.data.length));
    }
    
    @Test
    public void testRemove(){
        Assert.assertThat(this.set.size() , Matchers.is(0));
        
        for (int i = 0 ; i < this.data.length ; i++)
            this.set.set_insert(this.data[i]);
        
        Assert.assertThat(this.set.size() , Matchers.is(this.data.length));
        
        this.set.clean();
        
        Assert.assertThat(this.set.size() , Matchers.is(0));
    }

    @Test
    public void testSize() {
        Assert.assertThat(this.set.size() , Matchers.is(0));
    }
}
