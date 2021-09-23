package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ThreeRootFunctionTest {
    private MathFunction value = new ThreeRootFunction();
    public final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        assertEquals(value.apply(27), 3, DELTA);
        assertEquals(value.apply(9200), 20.9537, DELTA);
    }
}