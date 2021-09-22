package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction first = new ConstantFunction(5.0);
    MathFunction sqr = new SqrFunction();
    MathFunction tang = new TangFunction();
    MathFunction root = new ThreeRootFunction();
    private final MathFunction firstFunction = first.andThen(sqr);
    private final MathFunction secondFunction = sqr.andThen(first);

    @Test
    public void andThen() {
        assertEquals(firstFunction.apply(5.0), 25.0, 0.00001);
        assertEquals(secondFunction.apply(5.0), 5.0, 0.00001);
        assertEquals(firstFunction.apply(-4.0), 25.0, 0.00001);
        assertEquals(tang.andThen(sqr).andThen(root).apply(45.0), 1.3791, 0.0001);
    }
}