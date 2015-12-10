package org.maxim.cis.calculator.service.impl;

import org.maxim.cis.calculator.service.AbstractOperation;

public class MultiplyOperation extends AbstractOperation {

    @Override
    protected int performOperation(int[] values) {
        int result = 1;
        for (int value : values) {
            result *= value;
        }
        return result;
    }

}
