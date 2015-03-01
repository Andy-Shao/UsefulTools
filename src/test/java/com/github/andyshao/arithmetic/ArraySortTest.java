package com.github.andyshao.arithmetic;

import java.util.Arrays;
import java.util.Comparator;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArraySortTest {
    private final Integer[] answer = new Integer[] {
        0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
    };
    private volatile Integer[] data;
    private final Comparator<Integer> comparator = (i1 , i2) -> {
        return Integer.compare(i1 , i2);
    };

    @Before
    public void before() {
        this.data = new Integer[] {
            3 , 4 , 7 , 9 , 1 , 6 , 2 , 0 , 5 , 8
        };
    }

    @Test
    public void teatIssort() {
        ArraySort.<Integer> issort(this.data , this.comparator);

        Assert.assertThat(Arrays.deepEquals(this.data , this.answer) , Matchers.is(true));
    }

    @Test
    public void testQksort(){
        ArraySort.qksort(this.data , 0 , this.data.length-1 , this.comparator);
        
        Assert.assertThat(Arrays.deepEquals(this.data , this.answer) , Matchers.is(true));
    }
}
