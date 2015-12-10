package org.maxim.cis.server;

import org.maxim.cis.api.AbstractServer;
import org.maxim.cis.calculator.OperationFactory;
import org.maxim.cis.calculator.beans.OperationBundle;
import org.maxim.cis.calculator.service.Operation;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class SocketServer extends AbstractServer {

    private final static OperationFactory operationFactory = new OperationFactory();

    @Override
    public void run() {
        ByteBuffer in = ByteBuffer.allocate(BUFFER_SIZE);
        SocketAddress socketAddress = new InetSocketAddress(PORT);

        try (DatagramChannel channel = DatagramChannel.open();
             DatagramSocket serverSocket = channel.socket()) {
            serverSocket.bind(socketAddress);

            System.out.println("Server started.");
            while (true) {
                in.clear();

                SocketAddress clientAddress = channel.receive(in);

                String request = new String(in.array()).trim();
                System.out.println("Received: " + request);
                OperationBundle operationBundle = new OperationBundle(request);

                String[] operations = operationBundle.getOperations();
                String[] numbers = operationBundle.getNumbers();

                for (int i = 0; i < operations.length; i++) {
                    String strOperation = operations[i];
                    Operation operation = operationFactory.getOperation(strOperation);

                    String result = operation.execute(Arrays.copyOfRange(numbers, i, i + 2));
                    numbers[i + 1] = result;
                }

                String answer = numbers[numbers.length - 1];
                System.out.println("Answer to client: " + answer);

                channel.send(convertToByte(answer), clientAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
