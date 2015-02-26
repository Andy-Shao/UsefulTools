package com.github.andyshao.data.structure;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 12, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class TreeOperationException extends DataStructureException {
    private static final long serialVersionUID = 8831040862742000185L;

    public TreeOperationException() {
        super();
    }

    public TreeOperationException(String errorMsg) {
        super(errorMsg);
    }

    public TreeOperationException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public TreeOperationException(Throwable cause) {
        super(cause);
    }
}
