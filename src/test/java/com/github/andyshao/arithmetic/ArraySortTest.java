package com.github.andyshao.arithmetic;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.util.ArrayTools;

public class ArraySortTest {
    private final Integer[] answer = ArrayTools.pack_unpack(new int[] {
        0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
    } , Integer[].class);
    private volatile Integer[] data;

    @Before
    public void before() {
        this.data = ArrayTools.pack_unpack(new int[] {
            3 , 4 , 7 , 9 , 1 , 6 , 2 , 0 , 5 , 8
        } , Integer[].class);

    }

    @Test
    public void teatIssort() {
        ArraySort.<Integer> issort(this.data , (i1 , i2) -> {
            return Integer.compare(i1 , i2);
        });

        Assert.assertThat(Arrays.deepEquals(this.data , this.answer) , Matchers.is(true));
    }
}
