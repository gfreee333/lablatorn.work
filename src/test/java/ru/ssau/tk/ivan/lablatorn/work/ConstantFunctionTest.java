package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    private double pi = 3.14;
    private MathFunction value = new ConstantFunction(pi);

    @Test
    public void testApply() {
        double pi = 3.14;
        MathFunction value = new ConstantFunction(pi);
        assertEquals(value.apply(13), pi);
        assertEquals(value.apply(24.2), pi);
        assertEquals(value.apply(234), pi);
    }
}