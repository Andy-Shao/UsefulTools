package andy.shao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 7, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public final class Reflects {

    /**
     * 
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> forName(String className){
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz
     * @param method_name
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class<?> clazz, String method_name, Class<?>...parameterTypes){
        try {
            return clazz.getMethod(method_name , parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * @param clazz
     * @param field_name
     * @return
     */
    public static Field getField(Class<?> clazz, String field_name){
        try {
            return clazz.getField(field_name);
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
