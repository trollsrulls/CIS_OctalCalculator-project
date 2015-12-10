package org.maxim.cis.lab7;

import org.maxim.cis.calculator.service.Operation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunnerRMIClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9999);
        Operation operation = (Operation) registry.lookup("Server");

        System.out.println("Client started");

        System.out.println(operation.execute(new String[] {"3", "5", "7"}));
        System.out.println(operation.execute(new String[] {"1", "1", "1"}));
        System.out.println(operation.execute(new String[] {"0", "0", "0"}));
        System.out.println(operation.execute(new String[] {"2", "4", "6"}));
        System.out.println(operation.execute(new String[] {"7", "3", "1"}));
    }

}
