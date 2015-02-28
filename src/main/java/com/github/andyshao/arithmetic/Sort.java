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
public final class Sort {
    public static final <DATA> DATA[] issort(DATA[] data , Comparator<DATA> comparator) {
        //Repeatedly insert a key element among the sorted elements.
        for (int j = 1 ; j < data.length ; j++) {
            DATA key = data[j];
            int i = j - 1;

            //Determine the position at which to insert the key element.
            for ( ; i >= 0 && comparator.compare(data[i] , key) > 0 ; i--)
                data[i + 1] = data[i];
            data[i + 1] = key;
        }
        return data;
    }

    private Sort() {
        throw new AssertionError("No com.github.andyshao.arithmetic.Sort instances for you!");
    }
}
