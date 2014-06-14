package andy.shao.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 7, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class Reflects {
    private Reflects() {
        // TODO Auto-generated constructor stub
        throw new AssertionError("No support instance " + Reflects.class.getName());
    }

    /**
     * 
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> forName(String className) {
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
     * @param parameterTypes
     * @return
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz , Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz
     * @param parameterTypes
     * @return
     */
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz , Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
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
    public static Field getDeclaredField(Class<?> clazz , String field_name) {
        try {
            return clazz.getDeclaredField(field_name);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            if (clazz.getSuperclass() != null) return getDeclaredField(clazz.getSuperclass() , field_name);
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            // TODO: handle exception
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
    public static Method getDeclaredMethod(Class<?> clazz , String method_name , Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(method_name , parameterTypes);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            if (clazz.getSuperclass() != null) return getDeclaredMethod(clazz.getSuperclass() , method_name ,
                parameterTypes);
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz
     * @param field_name
     * @return
     */
    public static Field getField(Class<?> clazz , String field_name) {
        try {
            return clazz.getField(field_name);
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param target
     * @param field
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object target , Field field) {
        try {
            return (T) field.get(target);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz
     * @param set
     */
    public static void getInterfaces(Class<?> clazz , Set<Class<?>> set) {
        set.addAll(Arrays.asList(clazz.getInterfaces()));
        if (clazz.getSuperclass() != null) getInterfaces(clazz.getSuperclass() , set);
    }

    /**
     * 
     * @param clazz
     * @param method_name
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class<?> clazz , String method_name , Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(method_name , parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param target
     * @param method
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T invoked(Object target , Method method , Object... values) {
        try {
            return (T) method.invoke(target , values);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param constructor
     * @param values
     * @return
     */
    public static <T> T newInstance(Constructor<T> constructor , Object... values) {
        try {
            return constructor.newInstance(values);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param target
     * @param field
     * @param value
     */
    public static void setFieldValue(Object target , Field field , Object value) {
        try {
            field.set(target , value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
