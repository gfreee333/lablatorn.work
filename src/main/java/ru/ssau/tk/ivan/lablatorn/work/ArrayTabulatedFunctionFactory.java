package ru.ssau.tk.ivan.lablatorn.work;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
