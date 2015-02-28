package com.github.andyshao.arithmetic;

import java.util.Comparator;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 28, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class ArraySort {

    /**
     * insert sort(插入排序)
     * 
     * @param data array
     * @param comparator {@link Comparator}
     * @param <DATA> data
     * @return if the data is null then return it.
     */
    public static final <DATA> DATA[] issort(DATA[] data , Comparator<DATA> comparator) {
        if (data == null) return data;
        //Repeatedly insert a key element among the sorted elements.
        for (int i = 1 ; i < data.length ; i++) {
            DATA temp = data[i];
            int j = i - 1;

            //Determine the position at which to insert the key element.
            for ( ; j >= 0 && comparator.compare(data[j] , temp) > 0 ; j--)
                data[j + 1] = data[j];
            data[j + 1] = temp;
        }
        return data;
    }

    private ArraySort() {
        throw new AssertionError("No com.github.andyshao.arithmetic.ArraySort instances for you!");
    }
}
