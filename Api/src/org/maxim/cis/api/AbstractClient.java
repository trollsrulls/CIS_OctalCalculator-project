package org.maxim.cis.api;

import java.nio.ByteBuffer;

public abstract class AbstractClient implements Client {

    protected final static int PORT = 8801;
    protected final static int SERVER_PORT = 8802;
    protected final static int BUFFER_SIZE = 512;

    protected final static String ADDRESS = "localhost";

    protected static void printResult(ByteBuffer in) {
        System.out.println("Received: " + new String(in.array()));
    }

    protected ByteBuffer convertToByte(String line) {
        return ByteBuffer.wrap(line.getBytes());
    }

}
