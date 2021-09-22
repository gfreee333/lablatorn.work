package ru.ssau.tk._ivan_._lablatorn.work_;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ThreeRootFunctionTest {
    public final static double DELTE = 0.0001;;
    @Test
    public void testApply() {
        MathFunction value = new ThreeRootFunction();
        assertEquals(value.apply(27),3,DELTE);
        assertEquals(value.apply(9200),20.9537,DELTE);
    }
}