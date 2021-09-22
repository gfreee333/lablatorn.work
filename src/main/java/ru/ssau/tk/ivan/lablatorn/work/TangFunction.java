package ru.ssau.tk.ivan.lablatorn.work;

public class TangFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return java.lang.Math.tan(x);
    }
}
