package ru.ssau.tk.ivan.lablatorn.work.function;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

public class TangFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.tan(x);
    }
}
