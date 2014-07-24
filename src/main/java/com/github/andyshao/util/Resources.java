package com.github.andyshao.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 18, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 * 
 */
public class Resources {

    public static Reader getReader(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

    public static Writer getWriter(OutputStream outputStream) {
        return new OutputStreamWriter(outputStream);
    }

    public static ReadableByteChannel getReadableByteChannel(InputStream inputStream) {
        return Channels.newChannel(inputStream);
    }

    public static WritableByteChannel getWritableByteChannel(OutputStream outputStream) {
        return Channels.newChannel(outputStream);
    }

    public static InputStream getInputStream(AsynchronousByteChannel channel) {
        return Channels.newInputStream(channel);
    }

    public static InputStream getInputStream(ReadableByteChannel channel) {
        return Channels.newInputStream(channel);
    }

    public static OutputStream getOutputStream(WritableByteChannel channel) {
        return Channels.newOutputStream(channel);
    }

    public static OutputStream getOutputStream(AsynchronousByteChannel channel) {
        return Channels.newOutputStream(channel);
    }

    private Resources() {
        // TODO Auto-generated constructor stub
        throw new AssertionError("No support instance " + Reflects.class.getName());
    }
}
