package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testApply() {
        MathFunction number1 = new ZeroFunction();
        assertEquals(number1.apply(13), 0);
        assertEquals(number1.apply(24.2), 0);
        assertEquals(number1.apply(234), 0);
    }
}
