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
    /**
     * 
     * @param array_type
     * @param arrays
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] mergeArrays(Class<T[]> array_type , T[]... arrays) {
        int length = 0;
        for (T[] array : arrays) {
            length += array.length;
        }
        T[] result = (T[]) Array.newInstance(array_type.getComponentType() , length);
        for (int i = 0 , index = 0 ; i < arrays.length ; index += arrays[i].length , i++) {
            System.arraycopy(arrays[i] , 0 , result , index , arrays[i].length);
        }
        return result;
    }

    /**
     * 
     * @param array
     * @param item
     * @return
     */
    public static <T> T[] removeAllItem(T[] array , T item) {
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i].equals(item)) {
                array = ArrayTools.removeItem(array , i);
            }
        }
        return array;
    }

    /**
     * 
     * @param array
     * @param item
     * @return
     */
    public static <T> T[] removeFirstItem(T[] array , T item) {
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i].equals(item)) {
                array = ArrayTools.removeItem(array , i);
                break;
            }
        }
        return array;
    }

    /**
     * 
     * @param array
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeItem(T[] array , int index) {
        if (index >= array.length) { return array; }

        return ArrayTools.mergeArrays((Class<T[]>) array.getClass().getComponentType() ,
            ArrayTools.splitArray(array , 0 , index) , ArrayTools.splitArray(array , index + 1 , array.length));
    }

    /**
     * 
     * @param array
     * @param item
     * @return
     */
    public static <T> T[] removeLastItem(T[] array , T item) {
        for (int i = array.length - 1 ; i < 0 ; i++) {
            if (array[i].equals(item)) {
                array = ArrayTools.removeItem(array , i);
                break;
            }
        }
        return array;
    }

    /**
     * 
     * @param array
     * @param from
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] splitArray(T[] array , int from , int end) {
        if (from < 0 || end < 0 || end < from || from > array.length || end > array.length) { throw new IllegalArgumentException(); }
        T[] result = (T[]) Array.newInstance(array.getClass().getComponentType() , end - from);
        System.arraycopy(array , from , result , 0 , end - from);
        return result;
    }
    
    /**
	 * 
	 * @param array
	 * @return
	 */
	public static <T> int getlength(T array){
		String arrayName = array.getClass().getName();
		if(arrayName.startsWith("[L")) return ((Object[])array).length;
		else if(arrayName.equals(int[].class.getName())) return ((int[])array).length;
		else if(arrayName.equals(char[].class.getName())) return ((char[])array).length;
		else if(arrayName.equals(short[].class.getName())) return ((short[])array).length;
		else if(arrayName.equals(byte[].class.getName())) return ((byte[])array).length;
		else if(arrayName.equals(float[].class.getName())) return ((float[])array).length;
		else if(arrayName.equals(long[].class.getName())) return ((long[])array).length;
		else if(arrayName.equals(double[].class.getName())) return ((double[])array).length;
		return -1;
	}
	
	/**
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	public static Object getValueByArray(Object array, int index){
		String arrayName = array.getClass().getName();
		if(arrayName.startsWith("[L")) return ((Object[])array)[index];
		else if(arrayName.equals(int[].class.getName())) return ((int[])array)[index];
		else if(arrayName.equals(char[].class.getName())) return ((char[])array)[index];
		else if(arrayName.equals(short[].class.getName())) return ((short[])array)[index];
		else if(arrayName.equals(byte[].class.getName())) return ((byte[])array)[index];
		else if(arrayName.equals(float[].class.getName())) return ((float[])array)[index];
		else if(arrayName.equals(long[].class.getName())) return ((long[])array)[index];
		else if(arrayName.equals(double[].class.getName())) return ((double[])array)[index];
		return null;
	}
	
	/**
	 * 
	 * @param array
	 * @param index
	 * @param value
	 * @return
	 */
	public static Object setValueByArray(Object array, int index, Object value){
		Object result = getValueByArray(array, index);
		
		String arrayName = array.getClass().getName();
		if(arrayName.startsWith("[L")) ((Object[])array)[index] = value;
		else if(arrayName.equals(int[].class.getName())) ((int[])array)[index] = (int) value;
		else if(arrayName.equals(char[].class.getName())) ((char[])array)[index] = (char) value;
		else if(arrayName.equals(short[].class.getName())) ((short[])array)[index] = (short) value;
		else if(arrayName.equals(byte[].class.getName())) ((byte[])array)[index] = (byte) value;
		else if(arrayName.equals(float[].class.getName())) ((float[])array)[index] = (float) value;
		else if(arrayName.equals(long[].class.getName())) ((long[])array)[index] = (long) value;
		else if(arrayName.equals(double[].class.getName())) ((double[])array)[index] = (double) value;
		
		return result;
	}

    private ArrayTools() {
        // TODO Auto-generated constructor stub
        throw new AssertionError("No support instance " + ArrayTools.class.getName());
    }
}
