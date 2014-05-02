package andy.shao.util;

import java.lang.reflect.Array;

/**
 * 
 * Title: Some tools of Array<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 4, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class ArrayTools {
    private ArrayTools() {
		throw new AssertionError("No ArrayTools instances for you!");
	}

	/**
	 * Merge the Arrays.<br>
	 * 
	 * @param array_type
	 *            the class type of the finally array.
	 * @param arrays
	 *            which should be merged
	 * @return the finally array which contain all of information of arrays.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mergeArray(Class<T> array_type, T... arrays) {
		if(!array_type.isArray()) throw new IllegalArgumentException("The inputs must be a array");
		int length = 0;
		for (T array : arrays)
			length += Array.getLength(array);
		T result = (T) Array.newInstance(array_type.getComponentType(), length);
		for (int i = 0, point = 0; i < arrays.length; point += Array.getLength(arrays[i]), i++)
			System.arraycopy(arrays[i], 0, result, point, Array.getLength(arrays[i]));
		return result;
	}

	/**
	 * Get a small array from the array.
	 * 
	 * @param array
	 *            the array be processed.
	 * @param from
	 *            the start of location of array
	 * @param end
	 *            the end of location of array. NOTE: never include itself.
	 * @return a array which has been processed.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T splitArray(T array, int from, int end) {
		if(!array.getClass().isArray()) throw new IllegalArgumentException("The array must be a array");
		T result = (T) Array.newInstance(array.getClass().getComponentType(), end - from);
		System.arraycopy(array, from, result, 0, end - from);
		return result;
	}

	/**
	 * Remove the array[i] from the array.
	 * 
	 * @param array
	 *            the array be processed.
	 * @param index
	 *            the location which should be removed.
	 * @return a array which has been processed.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T removeItem(T array, int i) {
		if (i >= Array.getLength(array))
			return array;

		array = mergeArray((Class<T>) array.getClass(), splitArray(array, 0, i), splitArray(array, i + 1, Array.getLength(array)));
		return array;
	}

	/**
	 * Remove all of item which be included in array.
	 * 
	 * @param array
	 *            The array be processed.
	 * @param item
	 *            The item which should be remove
	 * @return a array which has been processed.
	 */
	public static <T,I> T removeAllItem(T array, I item) {
		ARRAY: for (int i = 0; i < Array.getLength(array); i++) {
			if (Array.get(array, i).equals(item)) {
				array = removeItem(array, i);
				continue ARRAY;
			}
		}
		return array;
	}

	/**
	 * Remove the first location which included the item in array.
	 * 
	 * @param array
	 *            the array which be processed.
	 * @param item
	 *            the item which should be remove.
	 * @return a array which has been processed.
	 */
	public static <T, I> T removeFirstItem(T array, I item) {
		ARRAY: for (int i = 0; i < Array.getLength(array); i++) {
			if (Array.get(array, i).equals(item)) {
				array = removeItem(array, i);
				break ARRAY;
			}
		}
		return array;
	}

	/**
	 * Remove the last location of item from array.
	 * 
	 * @param array
	 *            the array which be processed.
	 * @param item
	 *            the item which should be remove.
	 * @return a array which has been processed.
	 */
	public static <T,I> T removeLastItem(T array, I item) {
		ARRAY: for (int i = Array.getLength(array) - 1; i >= 0; i--) {
			if (Array.get(array, i).equals(item)) {
				array = removeItem(array, i);
				break ARRAY;
			}
		}
		return array;
	}
}
