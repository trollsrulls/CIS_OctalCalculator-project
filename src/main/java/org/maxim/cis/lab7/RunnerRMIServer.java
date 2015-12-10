package org.maxim.cis.lab7;

import org.maxim.cis.calculator.OperationFactory;
import org.maxim.cis.calculator.service.Operation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RunnerRMIServer {

    private static final OperationFactory operationFactory = new OperationFactory();

    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        Operation operation = operationFactory.getOperation("+");
        System.out.println("Server started..");
        try {
            Operation stub = (Operation) UnicastRemoteObject.exportObject(operation, 0);

            Registry registry = LocateRegistry.createRegistry(9999);
            registry.bind("Server", stub);
        } catch (Exception e) {
            System.out.println("Error occured: " + e.getMessage());
        }
    }

}
