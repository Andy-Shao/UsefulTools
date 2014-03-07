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
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static int[] mergerArrays(int[]...arrays){
        int length = 0;
        for(int[] array : arrays) length += array.length;
        int[] result = new int[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static byte[] mergerArrays(byte[]...arrays){
        int length = 0;
        for(byte[] array : arrays) length += array.length;
        byte[] result = new byte[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static char[] mergerArrays(char[]...arrays){
        int length = 0;
        for(char[] array : arrays) length += array.length;
        char[] result = new char[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static short[] mergerArrays(short[]...arrays){
        int length = 0;
        for(short[] array : arrays) length += array.length;
        short[] result = new short[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static float[] mergerArrays(float[]...arrays){
        int length = 0;
        for(float[] array : arrays) length += array.length;
        float[] result = new float[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static long[] mergerArrays(long[]...arrays){
        int length = 0;
        for(long[] array : arrays) length += array.length;
        long[] result = new long[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
    
    /**
     * 
     * @param arrays
     * @return
     */
    public static double[] mergerArrays(double[]...arrays){
        int length = 0;
        for(double[] array : arrays) length += array.length;
        double[] result = new double[length];
        for(int i=0, index=0; i<arrays.length; index+=arrays[i].length, i++) System.arraycopy(arrays[i].length , 0 , result , index , arrays[i].length);
        return result;
    }
}
