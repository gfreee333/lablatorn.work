package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.function.UnitFunction;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    private final MathFunction value = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(value.apply(13), 1.0);
        assertEquals(value.apply(24.2), 1.0);
        assertEquals(value.apply(234), 1.0);
    }
}