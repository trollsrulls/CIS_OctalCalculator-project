package org.maxim.cis.client;

import org.maxim.cis.api.AbstractClient;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class SocketClient extends AbstractClient {

    @Override
    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            DatagramChannel channel = DatagramChannel.open();
            SocketAddress address = new InetSocketAddress(ADDRESS, PORT);

            DatagramSocket clientSocket = channel.socket();
            clientSocket.bind(address);

            SocketAddress serverAddress = new InetSocketAddress(ADDRESS, SERVER_PORT);

            System.out.println("Enter expression:");
            String expression = sc.next();

            channel.send(convertToByte(expression), serverAddress);

            ByteBuffer in = ByteBuffer.allocate(BUFFER_SIZE);
            channel.receive(in);

            printResult(in);
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
