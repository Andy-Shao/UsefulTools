package andy.shao.util;

import java.util.Arrays;

import org.junit.Test;

public class ArrayToolsTest {

    @Test
    public void mergeArrays(){
        System.out.println(Arrays.toString(ArrayTools.mergeArrays(Integer[].class, new Integer[]{1,2,3}, new Integer[]{4,5,6})));
    }
}
