package ru.ssau.tk.ivan.lablatorn.work.operations;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {

        if(step <= 0 || step == Double.POSITIVE_INFINITY || step == Double.NaN) {
            throw new IllegalArgumentException();
        }
        this.step = step;

    }

    public double getStep() { return step; }

    public void setStep(double step) { this.step = step; }
}
