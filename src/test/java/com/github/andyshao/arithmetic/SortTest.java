package com.github.andyshao.arithmetic;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.github.andyshao.util.ArrayTools;

public class SortTest {

    @Test
    public void teatIssort() {
        Integer[] data = ArrayTools.pack_unpack(new int[] {
            3 , 4 , 7 , 9 , 1 , 6 , 2 , 0 , 5 , 8
        } , Integer[].class);

        Sort.<Integer> issort(data , (i1 , i2) -> {
            return Integer.compare(i1 , i2);
        });
        Assert.assertThat(Arrays.deepEquals(data , ArrayTools.pack_unpack(new int[] {
            0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
        } , Integer[].class)) , Matchers.is(true));
    }
}
