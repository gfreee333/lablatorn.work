package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.function.SqrFunction;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    private final MathFunction value = new SqrFunction();

    @Test
    public void testApply() {
        assertEquals(value.apply(3), 9, 0.0001);
        assertEquals(value.apply(4), 16, 0.0001);
    }
}