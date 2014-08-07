package com.github.andyshao.util;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.github.andyshao.convert.Convert;
import com.github.andyshao.util.ArrayTools;

/**
 * 
 * @author Andy
 * 
 */
public class ArrayToolsTest {

    @Test
    public void convertToMap() {
        Map<String , String> target =
            ArrayTools.<String , String> convertToMap(Convert.OB_2_STR , Convert.OB_2_STR , new Object[] {
                "i" , "2"
            } , new Object[] {
                "I" , "3"
            });

        Map<String , String> answer = new HashMap<>();
        answer.put("i" , "2");
        answer.put("I" , "3");

        assertThat(answer, is(target));
    }

    @Test
    public void findFirstItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        assertThat(ArrayTools.findFirstItem(array, 4), is(3));
    }

    @Test
    public void findLastItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        assertThat(ArrayTools.findFirstItem(array, 4), is(3));
    }

    @Test
    public void mergeArray() {
    	{
    		int[] array = ArrayTools.mergeArray(int[].class , new int[] {
    			1 , 2 , 3 , 4
    		} , new int[] {
    			5 , 6 , 7 , 8 , 9
    		});
    		assertThat(array , is(new int[] {
    				1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
    		}));
    	}
    	{
    		char[] cs = new char[]{'b','c'};
			char[] array = ArrayTools.mergeArray(char[].class, cs, new char[0]);
    		assertThat(array, is(cs));
    		
    		array = ArrayTools.mergeArray(char[].class, new char[0], cs);
    		assertThat(array, is(cs));
    		
    		array = ArrayTools.mergeArray(char[].class, new char[0], new char[0]);
    		assertThat(array, is(new char[0]));
    	}
    }

    @Test
    public void pack_unpack(){
    	{
    		Integer[] integers = ArrayTools.pack_unpack(new int[]{1,2,3}, Integer[].class);
    		assertThat(integers, is(new Integer[]{1,2,3}));
    	}

    	{
    		int[] is = ArrayTools.pack_unpack(new Integer[]{1, 2, 3}, int[].class);
    		assertThat(is, is(new int[]{1, 2, 3}));
    	}

    	{
    		long[] ls = ArrayTools.pack_unpack(new int[]{1}, long[].class);
    		assertThat(ls, is(new long[]{1}));
    	}
    }

    @Test
    public void removeAllItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        assertThat(ArrayTools.removeAllItem(array , 3), is(new int[] {
                1 , 2 , 4 , 6 , 7 , 9
            }));
    }
    
    @Test
    public void removeItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
        };
        assertThat(ArrayTools.removeItem(array , 3), is(new int[] {
                1 , 2 , 3 , 5 , 6 , 7 , 8 , 9
            }));
    }
}
