package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TangFunctionTest {
    private MathFunction first = new TangFunction();

    @Test
    public void andThen() {
        assertEquals(first.apply(0), 0.0000, 0.0001);
        assertEquals(first.apply(30.0), -6.4053, 0.0001);
        assertEquals(first.apply(45.0), 1.6197, 0.0001);
    }
}