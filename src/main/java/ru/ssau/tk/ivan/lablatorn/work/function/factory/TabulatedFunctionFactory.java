package ru.ssau.tk.ivan.lablatorn.work.function.factory;

import ru.ssau.tk.ivan.lablatorn.work.function.MathFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
