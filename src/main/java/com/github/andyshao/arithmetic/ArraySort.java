package com.github.andyshao.arithmetic;

import java.util.Comparator;
import java.util.Random;

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
    public static final <DATA> DATA[] issort(final DATA[] data , Comparator<DATA> comparator) {
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

    static final <DATA> int partition(DATA[] data , int begin , int end , Comparator<DATA> comparator) {
        Integer[] r = new Integer[3];
        final Random random = new Random();

        //Use the median-of-three method to find the partition value.
        r[0] = random.ints(begin , end).findFirst().getAsInt();
        r[1] = random.ints(begin , end).findFirst().getAsInt();
        r[2] = random.ints(begin , end).findFirst().getAsInt();
        ArraySort.<Integer> issort(r , (i1 , i2) -> {
            return Integer.compare(i1 , i2);
        });
        DATA pval = data[r[1]];

        //Create two partitions around the partition value.
        begin--;
        end++;

        while (true) {
            //Move left until an element is found in the wrong partition.
            do
                end--;
            while (comparator.compare(data[end] , pval) > 0);

            //Move right until an element is found in the wrong partition.
            do
                begin++;
            while (comparator.compare(data[begin] , pval) < 0);

            if (begin >= end) {
                //Stop partitioning when the left and right counters cross.
                break;
            } else {
                //Swap the elements now under the left and right counters.
                DATA temp = data[begin];
                data[begin] = data[end];
                data[end] = temp;
            }
        }

        return end;
    }

    /**
     * quick sort(快速排序)
     * 
     * @param data array
     * @param begin the begin of sort
     * @param end the end of sort
     * @param comparator {@link Comparator}
     * @return the array which is sorted.
     * @throws ArraySortException when the sort has error.
     */
    public static final <DATA> DATA[] qksort(final DATA[] data , int begin , int end , Comparator<DATA> comparator) {
        //Stop teh recursion when it is not possible to partition further.
        while (begin < end) {
            int index = 0;
            //Determine where to partition the elements.
            if ((index = partition(data , begin , end , comparator)) < 0) throw new ArraySortException();

            //Recursively sort the left partition.
            qksort(data , begin , index , comparator);

            //Iterate and sort the right partition.
            begin = index + 1;
        }
        return data;
    }

    private ArraySort() {
        throw new AssertionError("No com.github.andyshao.arithmetic.ArraySort instances for you!");
    }
}
