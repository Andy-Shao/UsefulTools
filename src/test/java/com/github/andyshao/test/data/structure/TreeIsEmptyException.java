package com.github.andyshao.test.data.structure;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 13, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class TreeIsEmptyException extends TreeOperationException {
    private static final long serialVersionUID = 7029818648399686287L;

    public TreeIsEmptyException() {
        super();
    }

    public TreeIsEmptyException(String errorMsg) {
        super(errorMsg);
    }

    public TreeIsEmptyException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public TreeIsEmptyException(Throwable cause) {
        super(cause);
    }
}
