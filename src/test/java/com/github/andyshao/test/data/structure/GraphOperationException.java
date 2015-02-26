package com.github.andyshao.test.data.structure;

public class GraphOperationException extends DataStructureException {

    private static final long serialVersionUID = -6437250465369070393L;

    public GraphOperationException() {
        super();
    }

    public GraphOperationException(String errorMsg) {
        super(errorMsg);
    }

    public GraphOperationException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public GraphOperationException(Throwable cause) {
        super(cause);
    }
}
