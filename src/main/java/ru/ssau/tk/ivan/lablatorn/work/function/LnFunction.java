package ru.ssau.tk.ivan.lablatorn.work.function;

public class LnFunction implements MathFunction{
    public double apply(double x) {
        x = Math.log(x);
        return x;
    }
}
