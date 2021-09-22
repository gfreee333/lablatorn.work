package ru.ssau.tk.ivan.lablatorn.work;

public class ThreeRootFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x,1.0/3.0);
    }
}
