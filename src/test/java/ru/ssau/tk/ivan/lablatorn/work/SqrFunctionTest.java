package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    private MathFunction value = new SqrFunction();

    @Test
    public void testApply() {
        assertEquals(value.apply(3), 9, 0.0001);
        assertEquals(value.apply(4), 16, 0.0001);
    }
}