package ru.ssau.tk.ivan.lablatorn.work.operations;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

<<<<<<< HEAD

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {
=======
public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator{
>>>>>>> origin/main
    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x) - function.apply(x - step)) / step;
    }
}
