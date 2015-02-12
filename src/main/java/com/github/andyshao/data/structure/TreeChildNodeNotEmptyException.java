package com.github.andyshao.data.structure;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 12, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class TreeChildNodeNotEmptyException extends TreeOperationException{
    private static final long serialVersionUID = -3582916238481687068L;

    public TreeChildNodeNotEmptyException(String errorMsg) {
        super(errorMsg);
    }

    public TreeChildNodeNotEmptyException(Throwable cause) {
        super(cause);
    }

    public TreeChildNodeNotEmptyException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public TreeChildNodeNotEmptyException() {
        super();
    }
}
