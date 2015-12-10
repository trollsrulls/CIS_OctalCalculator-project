package org.maxim.cis.calculator.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OperationBundle implements Serializable {

    private String[] numbers;
    private String[] operations;

    public OperationBundle() { }

    public OperationBundle(String data) {
        this.numbers = data.split("[^0-9\\.,]");
        String[] rawOperations = data.split("[0-9\\.,]");
        List<String> clearOperations = new ArrayList<>();
        for (String rawOperation : rawOperations) {
            if (!rawOperation.isEmpty()) {
                clearOperations.add(rawOperation);
            }
        }
        this.operations = new String[clearOperations.size()];
        for (int i = 0; i < clearOperations.size(); i++) {
            this.operations[i] = clearOperations.get(i);
        }
    }

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }

    public String[] getOperations() {
        return operations;
    }

    public void setOperations(String[] operations) {
        this.operations = operations;
    }

}
