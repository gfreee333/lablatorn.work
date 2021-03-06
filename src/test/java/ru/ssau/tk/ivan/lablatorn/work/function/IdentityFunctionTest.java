package ru.ssau.tk.ivan.lablatorn.work.function;

import org.testng.annotations.Test;
import ru.ssau.tk.ivan.lablatorn.work.function.IdentityFunction;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final IdentityFunction value = new IdentityFunction();

    @Test
    public void testApply() {
        assertEquals(value.apply(1.2), 1.2);
        assertEquals(value.apply(24.2), 24.2);
        assertEquals(value.apply(22.2), 22.2);
    }
}