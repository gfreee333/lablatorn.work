package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {

    public double[] xValues = {12, 22, 32, 42};
    public double[] yValues = {22, 44, 60, 66};

    LinkedListTabulatedFunctionFactory array = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        assertTrue(array.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
        assertFalse(array.create(xValues, yValues) instanceof LinkedListTabulatedFunctionFactory);
    }
}