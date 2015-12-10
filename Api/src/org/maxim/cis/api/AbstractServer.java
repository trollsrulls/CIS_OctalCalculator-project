package org.maxim.cis.api;

import java.nio.ByteBuffer;

public abstract class AbstractServer implements Server {

    protected final static int PORT = 8802;
    protected final static int BUFFER_SIZE = 512;

    protected ByteBuffer convertToByte(String line) {
        return ByteBuffer.wrap(line.getBytes());
    }

}
