package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    @Test
    public void testApply() {
        MathFunction number1 = new UnitFunction();
        assertEquals(number1.apply(13),1.0);
        assertEquals(number1.apply(24.2),1.0);
        assertEquals(number1.apply(234), 1.0);
    }
}