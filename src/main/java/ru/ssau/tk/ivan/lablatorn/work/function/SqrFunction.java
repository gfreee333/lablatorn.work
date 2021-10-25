package ru.ssau.tk.ivan.lablatorn.work.function;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

public class SqrFunction implements MathFunction {
    public double apply(double x) {
        x = java.lang.Math.pow(x, 2);
        return x;
    }
}
