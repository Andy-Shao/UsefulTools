package com.github.andyshao.arithmetic;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 1, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ArraySortException extends ArithmeticException {
    private static final long serialVersionUID = -6634240428324974480L;

    public ArraySortException() {
        super();
    }

    public ArraySortException(String message) {
        super(message);
    }

    public ArraySortException(String message , Throwable exception) {
        super(message , exception);
    }

    public ArraySortException(Throwable exceptoin) {
        super(exceptoin);
    }
}
