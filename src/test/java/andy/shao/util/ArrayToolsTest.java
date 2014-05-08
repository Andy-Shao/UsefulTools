package andy.shao.util;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author Andy
 * 
 */
public class ArrayToolsTest {

	@Test
	public void removeItem() {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Assert.assertArrayEquals(new int[] { 1, 2, 3, 5, 6, 7, 8, 9 }, ArrayTools.removeItem(array, 3));
	}

	@Test
	public void mergeArray() {
		int[] array = ArrayTools.mergeArray(int[].class, new int[] { 1, 2, 3, 4 }, new int[] { 5, 6, 7, 8, 9 });
		Assert.assertArrayEquals(array, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
	}
	
	@Test
	public void removeAllItem(){
		int[] array = new int[] { 1, 2, 3, 4, 3, 6, 7, 3, 9 };
		Assert.assertArrayEquals(new int[] { 1, 2, 4, 6, 7, 9 }, ArrayTools.removeAllItem(array, 3));
	}
	
	@Test
	public void findFirstItem(){
		int[] array = new int[] { 1, 2, 3, 4, 3, 6, 7, 3, 9 };
		Assert.assertEquals(3, ArrayTools.findFirstItem(array, 4));
	}
	
	@Test
	public void findLastItem(){
		int[] array = new int[] { 1, 2, 3, 4, 3, 6, 7, 3, 9 };
		Assert.assertEquals(3, ArrayTools.findFirstItem(array, 4));
	}
}
