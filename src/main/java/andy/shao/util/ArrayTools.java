package andy.shao.util;

import java.lang.reflect.Array;
import java.util.Arrays;

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
        // TODO Auto-generated constructor stub
        throw new AssertionError("No support instance " + ArrayTools.class.getName());
    }

    /**
     * 
     * @param array_type
     * @param arrays
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] mergeArrays(Class<T[]> array_type , T[]... arrays) {
        int length = 0;
        for (T[] array : arrays)
            length += array.length;
        T[] result = (T[]) Array.newInstance(array_type.getComponentType() , length);
        for (int i = 0 , index = 0 ; i < arrays.length ; index += arrays[i].length , i++)
            System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }

    /**
     * 
     * @param array
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeItem(T[] array , int index) {
        if (index >= array.length) return array;

        return mergeArrays((Class<T[]>) array.getClass().getComponentType() , Arrays.copyOfRange(array , 0 , index) ,
            Arrays.copyOfRange(array , index + 1 , array.length));
    }

    /**
     * 
     * @param array
     * @param item
     * @return
     */
    public static <T> T[] removeAllItem(T[] array , T item) {
        for(int i=0; i<array.length; i++){
            if(array[i].equals(item)) array = removeItem(array , i);
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
        for(int i=0; i<array.length; i++){
            if(array[i].equals(item)){
                array = removeItem(array , i);
                break;
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
    public static <T> T[] removeLastItem(T[] array , T item) {
        for(int i=array.length-1; i<0; i++){
            if(array[i].equals(item)){
                array = removeItem(array , i);
                break;
            }
        }
        return array;
    }
}
