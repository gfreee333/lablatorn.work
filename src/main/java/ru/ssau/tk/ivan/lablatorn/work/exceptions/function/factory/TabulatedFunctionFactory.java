package ru.ssau.tk.ivan.lablatorn.work.exceptions.function.factory;

import ru.ssau.tk.ivan.lablatorn.work.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
