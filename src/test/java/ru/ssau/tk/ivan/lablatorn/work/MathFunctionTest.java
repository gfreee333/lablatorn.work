package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private MathFunction first = new ConstantFunction(5.0);
     private MathFunction sqr = new SqrFunction();
     private MathFunction tang = new TangFunction();
     private MathFunction root = new ThreeRootFunction();
    private final MathFunction firstFunction = first.andThen(sqr);
    private final MathFunction secondFunction = sqr.andThen(first);

    @Test
    public void andThen() {
        assertEquals(firstFunction.apply(5.0), 25.0, 0.00001);
        assertEquals(secondFunction.apply(5.0), 5.0, 0.00001);
        assertEquals(firstFunction.apply(-4.0), 25.0, 0.00001);
        assertEquals(tang.andThen(sqr).andThen(root).apply(15.0), 0.9015, 0.0001);
        assertEquals(sqr.andThen(tang).apply(22),0.1972,0.0001);
        assertEquals(tang.andThen(sqr).apply(20),5.0048,0.0001);
        assertEquals(root.andThen(tang).apply(15),-0.8010,0.0001);
    }
}