package ru.ssau.tk.ivan.lablatorn.work.function;

public class ConstantFunction implements MathFunction {
    private final double invariable;

    public ConstantFunction(double invariable) {
        this.invariable = invariable;
    }

    public double apply(double x) {
        return invariable;
    }
}
