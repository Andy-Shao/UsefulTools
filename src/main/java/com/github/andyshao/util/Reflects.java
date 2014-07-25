package com.github.andyshao.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * 
 * Title:the tool of java.lang.reflect<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 7, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class Reflects {
    private Reflects() {
        throw new AssertionError("No support instance " + Reflects.class.getName());
    }

    /**
     * 
     * @param className the name of class
     * @return the type of class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> forName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz the type of class
     * @param parameterTypes the type of parameters of constructor of class
     * @return the constructor of class
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz , Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz the type of class
     * @param parameterTypes the typ of parameters of constructor of class
     * @return the constructor of class
     */
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz , Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param clazz the type of class
     * @param field_name the name of field
     * @return the field of class
     */
    public static Field getDeclaredField(Class<?> clazz , String field_name) {
        try {
            return clazz.getDeclaredField(field_name);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) return getDeclaredField(clazz.getSuperclass() , field_name);
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * if the clazz doesn't include the mtheod, it will check the super class.
     * @param clazz the type of class
     * @param method_name the name of method
     * @param parameterTypes the type of parameters
     * @return the method of class
     */
    public static Method getDeclaredMethod(Class<?> clazz , String method_name , Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(method_name , parameterTypes);
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null) return getDeclaredMethod(clazz.getSuperclass() , method_name ,
                parameterTypes);
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static Field getField(Class<?> clazz , String field_name) {
        try {
            return clazz.getField(field_name);
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object target , Field field) {
        try {
            return (T) field.get(target);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getInterfaces(Class<?> clazz , Set<Class<?>> set) {
        set.addAll(Arrays.asList(clazz.getInterfaces()));
        if (clazz.getSuperclass() != null) getInterfaces(clazz.getSuperclass() , set);
    }

    public static Method getMethod(Class<?> clazz , String method_name , Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(method_name , parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T invoked(Object target , Method method , Object... values) {
        try {
            return (T) method.invoke(target , values);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T newInstance(Constructor<T> constructor , Object... values) {
        try {
            return constructor.newInstance(values);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(Object target , Field field , Object value) {
        try {
            field.set(target , value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
