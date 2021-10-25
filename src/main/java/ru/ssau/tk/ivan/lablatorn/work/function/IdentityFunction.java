package ru.ssau.tk.ivan.lablatorn.work.function;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
