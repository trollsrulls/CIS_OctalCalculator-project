package org.maxim.cis.calculator;

import org.maxim.cis.calculator.service.Operation;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public final class OperationFactory {

    public static final String CONFIG = "operation_definition";

    private Map<String, Operation> operations = new HashMap<>();

    public OperationFactory() {
        ResourceBundle bundle = ResourceBundle.getBundle(CONFIG);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String operationClassName = bundle.getString(key);
            try {
                Class<?> clazz = Class.forName(operationClassName);
                Operation operation = (Operation) clazz.newInstance();
                operations.put(key, operation);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new IllegalArgumentException("Config is not valid.", e);
            }
        }
    }

    public Operation getOperation(String operation) {
        return operations.get(operation);
    }

}
