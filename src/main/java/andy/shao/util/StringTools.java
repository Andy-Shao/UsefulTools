package andy.shao.util;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 28, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public final class StringTools {

    /**
     * more efficient
     * @param str
     * @param key
     * @param padding
     * @return
     */
    public static String replaceAll(String str, String key, String padding){
        while(str.indexOf(key)!=-1)
            str = replaceFirst(str , key , padding);
        return str;
    }
    
    /**
     * more efficient
     * @param str
     * @param key
     * @param padding
     * @return
     */
    public static String replaceFirst(String str, String key, String padding){
        int index = str.indexOf(key);
        return replace(str , key , padding , index);
    }
    
    /**
     * more efficient
     * @param str
     * @param key
     * @param padding
     * @return
     */
    public static String replaceLast(String str, String key, String padding){
        int index = str.lastIndexOf(key);
        return replace(str , key , padding , index);
    }

    /**
     * more efficient
     * @param str
     * @param key
     * @param padding
     * @param index
     * @return
     */
    static String replace(String str , String key , String padding , int index) {
        String result;
        
        if(index == -1) result = str;
        else if(index ==0) result = padding + str.substring(key.length());
        else if(index+1 >= str.length()) result = str.substring(0 , index) + padding;
        else result = str.substring(0 , index) + padding + str.substring(index+key.length());
        
        return result;
    }
}
