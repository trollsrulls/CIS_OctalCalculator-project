package org.maxim.cis.lab2;

import org.maxim.cis.api.Client;
import org.maxim.cis.client.SocketClient;

public class RunnerSocketClient {

    private static Client client = new SocketClient();

    public static void main(String[] args) {
        client.run();
    }

}
