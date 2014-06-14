package andy.shao.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import andy.shao.convert.Convert;

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

        Assert.assertEquals(answer , target);
    }

    @Test
    public void findFirstItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        Assert.assertEquals(3 , ArrayTools.findFirstItem(array , 4));
    }

    @Test
    public void findLastItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        Assert.assertEquals(3 , ArrayTools.findFirstItem(array , 4));
    }

    @Test
    public void mergeArray() {
        int[] array = ArrayTools.mergeArray(int[].class , new int[] {
            1 , 2 , 3 , 4
        } , new int[] {
            5 , 6 , 7 , 8 , 9
        });
        Assert.assertArrayEquals(array , new int[] {
            1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
        });
    }

    @Test
    public void removeAllItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 3 , 6 , 7 , 3 , 9
        };
        Assert.assertArrayEquals(new int[] {
            1 , 2 , 4 , 6 , 7 , 9
        } , ArrayTools.removeAllItem(array , 3));
    }

    @Test
    public void removeItem() {
        int[] array = new int[] {
            1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9
        };
        Assert.assertArrayEquals(new int[] {
            1 , 2 , 3 , 5 , 6 , 7 , 8 , 9
        } , ArrayTools.removeItem(array , 3));
    }
}
