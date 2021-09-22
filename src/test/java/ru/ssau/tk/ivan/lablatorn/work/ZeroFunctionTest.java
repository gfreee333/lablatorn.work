package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testApply() {
        MathFunction number1 = new ZeroFunction();
        assertEquals(number1.apply(13), 0,0.0001);
        assertEquals(number1.apply(24.2), 0,0.0001);
        assertEquals(number1.apply(234), 0,0.0001);
    }
}
