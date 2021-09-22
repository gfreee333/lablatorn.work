package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        MathFunction value = new SqrFunction();
        assertEquals(value.apply(3),9);
        assertEquals(value.apply(4),16);
    }

}