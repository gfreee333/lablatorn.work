package ru.ssau.tk._ivan_._lablatorn.work_;

public class ConstantFunction implements MathFunction {
    private final double invariable;

    public ConstantFunction(double invariable) {
        this.invariable = invariable;
    }

    public double apply(double x) {
        return invariable;
    }
}
