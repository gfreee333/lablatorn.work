package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {
    public double[] xValues = {15, 25, 35, 45};
    public double[] yValues = {25, 45, 65, 65};

    ArrayTabulatedFunctionFactory array = new ArrayTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        assertTrue(array.create(xValues, yValues) instanceof ArrayTabulatedFunction);
        assertFalse(array.create(xValues, yValues) instanceof ArrayTabulatedFunctionFactory);
    }
}