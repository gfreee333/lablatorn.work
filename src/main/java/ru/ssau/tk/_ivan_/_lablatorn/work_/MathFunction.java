package ru.ssau.tk._ivan_._lablatorn.work_;

public interface MathFunction {
    double apply(double x);
    default CompositeFunction andThen(MathFunction afterFunction) { return new CompositeFunction(this, afterFunction); }
}
