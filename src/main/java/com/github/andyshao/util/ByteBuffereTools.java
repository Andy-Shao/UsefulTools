package com.github.andyshao.util;

import java.nio.ByteBuffer;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 4, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class ByteBuffereTools {
    /**
     * Only return a space which great than buffer.position() and less than
     * buffer.limit()
     * 
     * @param buffer the buffer which has the info
     * @return return the byte array and the info in it
     */
    public static byte[] usedArray(ByteBuffer buffer) {
        return ArrayTools.splitArray(buffer.array() , buffer.position() , buffer.limit());
    }

    private ByteBuffereTools() {
        throw new AssertionError("No support instance " + ByteBuffereTools.class.getName());
    }
}
