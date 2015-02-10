package com.github.andyshao.data.structure;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleLinkedTest {

    private final String[] data = new String[] {
        "Andy" , "Shao"
    };
    private SingleLinked<String> linked;

    @Before
    public void before() {
        this.linked = SingleLinked.<String> DEFAULT_SINGLE_LINKED();
    }

    @Test
    public void testDestroy() {
        this.testInsert();

        Assert.assertThat(this.linked.size() , Matchers.is(2));

    }

    @Test
    public void testInsert() {
        Assert.assertThat(this.linked.size() , Matchers.is(0));

        for (int i = 0 ; i < this.data.length ; i++)
            this.linked.list_ins_next(this.linked.tail() , this.data[i]);

        Assert.assertThat(this.linked.size() , Matchers.is(2));
    }

    @Test
    public void testSize() {
        Assert.assertThat(this.linked.size() , Matchers.is(0));
        
        testInsert();
        
        Assert.assertThat(this.linked.size() , Matchers.is(2));
    }
    
    @Test
    public void testRemove(){
        testInsert();
        Assert.assertThat(this.linked.size() , Matchers.is(2));
        
        this.linked.clean();
        Assert.assertThat(this.linked.size() , Matchers.is(0));
        
        testInsert();
        Assert.assertThat(this.linked.size() , Matchers.is(2));
        
        this.linked.list_rem_next(this.linked.head());
        Assert.assertThat(this.linked.size() , Matchers.is(1));
    }
}
