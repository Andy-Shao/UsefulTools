package andy.shao.util;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 4, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public final class HashCodes{
    
    /**
     * 
     * @param t
     * @return
     */
    public static <T> int hashCode(T t){
        return t.hashCode();
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(int t){
        return t;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(short t){
        return (int)t;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(byte t){
        return (int)t;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(char t){
        return (int)t;
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(long t){
        return (int)(t ^ (t >>> 32));
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(float t){
        return Float.floatToIntBits(t);
    }
    
    /**
     * 
     * @param t
     * @return
     */
    public static int hashCode(double t){
        long l = Double.doubleToLongBits(t);
        return hashCode(l);
    }
}
