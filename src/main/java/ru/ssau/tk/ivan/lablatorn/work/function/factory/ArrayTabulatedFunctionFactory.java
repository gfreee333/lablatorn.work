package ru.ssau.tk.ivan.lablatorn.work.function.factory;

import ru.ssau.tk.ivan.lablatorn.work.function.ArrayTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.MathFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count) {
        return new ArrayTabulatedFunction(source, xFrom, xTo, count);
    }
}
