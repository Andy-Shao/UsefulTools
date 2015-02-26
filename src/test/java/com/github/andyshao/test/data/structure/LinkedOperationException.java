package com.github.andyshao.test.data.structure;

public class LinkedOperationException extends DataStructureException {

    private static final long serialVersionUID = 3324113947275253394L;

    public LinkedOperationException() {
        super();
    }

    public LinkedOperationException(String errorMsg) {
        super(errorMsg);
    }

    public LinkedOperationException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public LinkedOperationException(Throwable cause) {
        super(cause);
    }
}
