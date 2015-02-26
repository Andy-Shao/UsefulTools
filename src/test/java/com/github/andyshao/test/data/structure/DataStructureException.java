package com.github.andyshao.test.data.structure;

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
public class DataStructureException extends RuntimeException {

    private static final long serialVersionUID = 5831612224020640233L;

    public DataStructureException() {
        super();
    }

    public DataStructureException(String errorMsg) {
        super(errorMsg);
    }

    public DataStructureException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public DataStructureException(Throwable cause) {
        super(cause);
    }
}
