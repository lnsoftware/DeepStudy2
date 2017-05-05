package com.zhiyin.gateway.filter.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletWrapperOutputStream extends ServletOutputStream {

    private final ByteArrayOutputStream baos;

    public ServletWrapperOutputStream(ServletOutputStream outputStream) {
        this.baos = new ByteArrayOutputStream();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {

    }

    public void write(int b) throws IOException {
        baos.write(b);
    }

    public int size() {
        return baos.size();
    }

    public byte[] toByteArray() {
        return baos.toByteArray();
    }

    public void flush() throws IOException {
        baos.flush();
    }

    public void close() throws IOException {
        baos.close();
    }
}
