package ru.ssau.tk.ivan.lablatorn.work.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    private final MathFunction value = new ZeroFunction();

    @Test
    public void testApply() {
        assertEquals(value.apply(13), 0, 0.0001);
        assertEquals(value.apply(24.2), 0, 0.0001);
        assertEquals(value.apply(234), 0, 0.0001);
    }
}
