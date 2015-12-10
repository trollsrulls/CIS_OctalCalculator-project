package org.maxim.cis.lab2;

import org.maxim.cis.api.Server;
import org.maxim.cis.server.SocketServer;

public class RunnerSocketServer {

    private static Server server = new SocketServer();

    public static void main(String[] args) {
        server.run();
    }

}
