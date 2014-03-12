package andy.shao.util;

import java.lang.reflect.Array;

/**
 * 
 * Title: Some tools of Array<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 4, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public final class ArrayTools{
    
    /**
     * 
     * @param array_type
     * @param arrays
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] mergeArrays(Class<T[]> array_type, T[]...arrays){
        int length = 0;
        for(T[] array : arrays) length += array.length;
        T[] result = (T[]) Array.newInstance(array_type.getComponentType() , length);
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
}
