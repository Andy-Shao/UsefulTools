package com.github.andyshao.test.data.structure;

public class HeapOperationException extends DataStructureException {
    private static final long serialVersionUID = 6700152770758060898L;

    public HeapOperationException() {
        super();
    }

    public HeapOperationException(String errorMsg) {
        super(errorMsg);
    }

    public HeapOperationException(String errorMsg , Throwable cause) {
        super(errorMsg , cause);
    }

    public HeapOperationException(Throwable cause) {
        super(cause);
    }
}
