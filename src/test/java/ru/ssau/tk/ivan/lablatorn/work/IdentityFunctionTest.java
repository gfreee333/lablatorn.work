package ru.ssau.tk.ivan.lablatorn.work;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction value = new IdentityFunction();
        double testValue = 1.2;
        double testValue2 = 22.2;
        assertEquals(value.apply(testValue),testValue);
        assertEquals(value.apply(24.2),24.2);
        assertEquals(value.apply(testValue2), testValue2);
    }
}